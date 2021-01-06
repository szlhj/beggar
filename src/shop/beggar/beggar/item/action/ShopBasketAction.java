package shop.beggar.beggar.item.action;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;

/**
 * @PackageName : shop.beggar.beggar.item.action
 * @FileName : DetailAction.java
 * @Since : 2020. 12. 3.
 * @Author : HJLee
 * @Description : 상품 상세 화면 경로 지정
 *              =====================================================================================
 *              Modification History
 *              =====================================================================================
 *              Date Author Note
 *              -------------------------------------------------------------------------------------
 *              2020. 12. 3. HJLee 최초 작성
 *
 */
public class ShopBasketAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		String item_sq_string = request.getParameter("item_sq");

		if (id != null) { // 로그인 상태엔 장바구니 테이블에 저장
			String item_sq = request.getParameter("item_sq");
			MemberVo vo = (MemberVo) session.getAttribute("vo");
			ItemVo ivo = new ItemVo();
			ivo.setItem_sq(Integer.parseInt(item_sq));
			ivo.setMber_sq(vo.getMber_sq());
			ItemService svc = new ItemService();
			if(svc.checkCartItem(ivo)) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('id장바구니에 중복된 상품이 있습니다.');history.back();</script>");
				out.close();
				return null;
			}
			if (!(svc.registerCart(ivo))) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('id장바구니에 상품을 저장하지 못했습니다.');history.back();</script>");
				out.close();
				return null;
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('id장바구니에 상품을 저장하였습니다.');history.back();</script>");
				out.close();
				return null;
			}
		}

		if (id == null) { // 비회원일때
			String item_sq = request.getParameter("item_sq");
			Cookie[] cookies = request.getCookies();

			for (int i = 0; i < cookies.length; i++) {
				Cookie cj = cookies[i];
				String cjValue = cj.getValue();
				if (cjValue.equals(item_sq)) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println(
							"<script>alert('쿠키장바구니 상품이 중복된 상품입니다.');history.back();</script>");
					out.close();
					return null;
				}
			}
			for (int k = 0; k < cookies.length; k++) {
					Cookie cj = cookies[k];
					String cjName = cj.getName();
					if (cjName.equals("item_sq")) {
						session.setAttribute("cart_sq", item_sq);
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println(
								"<script>alert('장바구니 상품이 2가지 입니다 로그인해 주세요.');location.href='/member/loginAction';</script>");
						out.close();
						return null;
					}
				}
			
			Cookie c = new Cookie("item_sq", item_sq);
			c.setPath("/");  //쿠키 모든경로에서 사용가능하게 설정
			c.setMaxAge(60*60*2); //쿠키 2시간 사용가능하게 설정
			response.addCookie(c);
			
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('쿠키장바구니에 상품을 저장하였습니다.');history.back();</script>");
			out.close();
			return null;
	
		}
		ActionForward forward = new ActionForward();
		forward.setPath("/item/detail?item_sq=" + item_sq_string);
		return forward;
	}
}
