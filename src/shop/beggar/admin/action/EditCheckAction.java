package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: EditCheckAction.java
 * @Since			: 2020. 12. 20.
 * @Author			: HJLee
 * @Description		: 에디터 체크를 위한 페이지
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 20.		HJLee				최초 작성
 *
 */
public class EditCheckAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/a.jsp");
		return forward;
	}
}
