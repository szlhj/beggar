package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: AdminAction.java
 * @Since			: 2020. 12. 7.
 * @Author			: HJLee
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 7.		HJLee				최초 작성
 *
 */
public class AdminAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminService svc = new AdminService();
		int count = svc.supperAdminInfo();
		
		HttpSession session = request.getSession();
		session.setAttribute("adminSupperCount", count);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/adminMain.jsp");
		return forward;
	}
}
