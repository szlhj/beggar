package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: LogoutAdminAction.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 로그아웃 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class LogoutAdminAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/");
		forward.setRedirect(true);
		return forward;
	}
}
