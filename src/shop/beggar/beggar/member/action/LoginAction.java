package shop.beggar.beggar.member.action;

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
 * @FileName		: LoginAction.java
 * @Since			: 2020. 12. 9.
 * @Author			: HJLee
 * @Description		: login 경로설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 9.		HJLee				최초 작성
 * 2020. 12. 10.	HJLee				로그인 메니져를 통해 session 에서 ID받아오기
 * 										
 *
 */
public class LoginAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		
		if (id != null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		
		String searchId = request.getParameter("searchId");
		
		MemberVo vo = new MemberVo();
		vo.setId(searchId);
		
		MemberService svc = new MemberService();
		MemberVo shVo = svc.getMemberLoginInfo(vo);
		
		request.setAttribute("shVo", shVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/loginForm.jsp");
		return forward;
	}
}
