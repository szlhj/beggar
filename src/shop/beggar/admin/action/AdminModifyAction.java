package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ModifyAction.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 관리자 회원 가입 경로
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class AdminModifyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		AdminVo vo = (AdminVo) session.getAttribute("adminVo");
		
		AdminService svc = new AdminService();
		AdminVo infoVo = svc.adminInfoAll(vo);
		
		request.setAttribute("vo", infoVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/modify.jsp");
		return forward;
	}
}
