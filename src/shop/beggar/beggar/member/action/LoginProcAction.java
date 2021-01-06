package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.IpTraceUtils;
import shop.beggar.common.LoginManager;
import shop.beggar.common.RegExp;

/**
 * @PackageName : shop.beggar.beggar.member.action
 * @FileName : LoginProcAction.java
 * @Since : 2020. 12. 10.
 * @Author : HJLee
 * @Description : login 로직
 *              =====================================================================================
 *              Modification History
 *              =====================================================================================
 *              Date Author Note
 *              -------------------------------------------------------------------------------------
 *              2020. 12. 10. HJLee 최초 작성
 *
 */
public class LoginProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		if (RegExp.isEmpty(id) || RegExp.isEmpty(pwd)) { //로그인 정규표현식이 맞지않을때
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}

		MemberService svc = new MemberService();

		MemberVo vo = new MemberVo();
		vo.setId(id);
		MemberVo voInfo = svc.getMemberLoginInfo(vo);
		if (voInfo == null || !BCrypt.checkpw(pwd, voInfo.getPwd())) {  //로그인 정보를 DB와 비교해서 비밀번호가 다른지 확인
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인 하세요.');history.back();</script>");
			out.close();
			return null;
		}

		// int_mber_lgn_hist_tb에 저장 작업
		String ipAddr = IpTraceUtils.getRemoteAddr(request);

//		System.out.println(ipAddr);

		if (!svc.loginHisRegister(voInfo, ipAddr)) {  //로그인 히스토리 정보를 저장하지 못했을때
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 히스토리 저장을 하지 못하였습니다.');history.back();</script>");
			out.close();
			return null;
		}

		LoginManager lm = LoginManager.getInstance();
		lm.setSession(request.getSession(), voInfo.getId());

//		request.setAttribute("mber_sq", voInfo.getMber_sq());// 세션에 mber_sq 넣기
		HttpSession session = request.getSession();
		String idc = lm.getMemberId(session);
		
		session.setAttribute("vo", voInfo);
		session.setMaxInactiveInterval(60 * 60);
		
		ItemVo ivo = new ItemVo();
		ivo.setMber_sq(voInfo.getMber_sq()); // 쿠키에 있는 상품을 카트 테이블에 넣을 ivo
		
		
		//쿠키를 장바구니 테이블에 저장
		Cookie[] cookies = request.getCookies();
		String item_sq = null;
		
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			String cName = c.getName();
			if (cName.equals("item_sq")) {
				item_sq = c.getValue();
				ItemService isvc = new ItemService();
				ivo.setItem_sq(Integer.parseInt(item_sq));
				
				if (!(isvc.checkCartItem(ivo))){
					if (!(isvc.registerCart(ivo))) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('쿠키 상품을 id장바구니에 상품을 저장하지 못했습니다.');history.back();</script>");
					out.close();
					return null;
					}
				}
			}
		}

		String item_sq_string = (String) session.getAttribute("cart_sq");
			if (item_sq_string != null) {
				ItemVo itvo = new ItemVo(); // 쿠키에 1개의 상품이 있는 상태에서 장바구니 눌렀을때의 누른 상품
				itvo.setMber_sq(voInfo.getMber_sq());
				itvo.setItem_sq(Integer.parseInt(item_sq_string));
				ItemService isvc = new ItemService();
				
				if (!(isvc.checkCartItem(itvo))){
					if (!(isvc.registerCart(itvo))) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('디테일상품을 id장바구니에 상품을 저장하지 못했습니다.');history.back();</script>");
					out.close();
					return null;
					}
				}
				
				Cookie cookie = new Cookie("item_sq",null); //쿠키 상품 장바구니에 넣고 null로 변경
				cookie.setMaxAge(0); //쿠키 상품 유효시간 0 지우기
				response.addCookie(cookie);
				
				
				ActionForward forward = new ActionForward();
				forward.setPath("/item/detail?item_sq="+ item_sq_string );
				return forward;
			}
		
		

		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
