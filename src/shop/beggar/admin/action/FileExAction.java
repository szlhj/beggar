package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: FileExAction.java
 * @Since			: 2020. 12. 21.
 * @Author			: HJLee
 * @Description		: 파일 업로드 및 다운로드 예제
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 21.		HJLee				최초 작성
 *
 */
public class FileExAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/file/fileExample.jsp");
		return forward;
	}
}
