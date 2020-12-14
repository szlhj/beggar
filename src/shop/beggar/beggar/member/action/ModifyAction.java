package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: ModifyAction.java
 * @Since			: 2020. 12. 3.
 * @Author			: HJLee
 * @Description		: 회원변경 화면 경로 지정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 3.		HJLee				최초 작성
 *
 */
public class ModifyAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		
		if (id == null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberVo vo = (MemberVo) session.getAttribute("vo");
		MemberService svc = new MemberService();
		MemberVo mvo = svc.modifyMemberInfo(vo);
		if(mvo == null) {
			response.setContentType("text/html;charset=UTF-8"); //답변줄 페이지 속성
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 불러오는데 실패하였습니다.');"
					+ "history.back();</script>");
			out.close();
			return null;
		}
		
		request.setAttribute("mvo", mvo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/modifyForm.jsp");
		return forward;
	}
}
