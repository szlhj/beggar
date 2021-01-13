package shop.beggar.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: AdminListAction.java
 * @Since			: 2020. 12. 30.
 * @Author			: HJLee
 * @Description		: 관리자 계정 리스트 화면으로 이동 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 30.		HJLee				최초 작성
 *
 */
public class AdminListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pn = request.getParameter("pn");

		if (pn == null) {
			pn = "1";
		}
		int page = Integer.parseInt(pn);
		
		AdminService svc = new AdminService();
		
		Pagenation pagenation = new Pagenation(page, svc.getAdminListCount());
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/adminList?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}
		
		ArrayList<AdminVo> list = svc.getListAdminVo();
		
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("adminList", list);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/adminList.jsp");
		return forward;
	}
}
