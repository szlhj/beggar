package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: AdminDetailAction.java
 * @Since			: 2020. 12. 30.
 * @Author			: HJLee
 * @Description		: 관리자 상세 보기 화면 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 30.		HJLee				최초 작성
 *
 */
public class AdminDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String admin_sq = request.getParameter("admin_sq");
		String pn = request.getParameter("pn");
		
		AdminVo sqVo = new AdminVo();
		sqVo.setAdmin_sq(Integer.parseInt(admin_sq));
		
		AdminService svc = new AdminService();
		AdminVo vo = svc.getAdminDetail(sqVo);
		
		request.setAttribute("adminVo", vo);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/adminDetail.jsp");
		return forward;
	}
}
