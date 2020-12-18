package shop.beggar.beggar.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: LogoutAction.java
 * @Since			: 2020. 12. 7.
 * @Author			: HJLee
 * @Description		: logout 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 7.		HJLee				최초 작성
 *
 */
public class LogoutAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		MemberVo vo = (MemberVo)session.getAttribute("vo");
		
		
		if (id != null || vo != null) {
			session.invalidate();
//			lm.removeSession(id);
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
