package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ModifyProcAction.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 관리자 회원 수정 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class AdminModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String adminId = request.getParameter("adminId");
		String adminPwd = request.getParameter("adminPwd");
		String adminPwdc = request.getParameter("adminPwdc");
		String adminEmail = request.getParameter("adminEmail");
		String adminPhone = request.getParameter("adminPhone");
		String adminMemo = request.getParameter("adminMemo");
		
		if (adminId == null || !adminPwd.equals(adminPwdc)) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/admin/';</script>");
			out.close();
			return null;
		}
		
		AdminService svc = new AdminService();
		AdminVo vo = new AdminVo();
		vo.setAdminId(adminId);
		if (adminPwd != null) {
			vo.setAdminPwd(BCrypt.hashpw(adminPwdc, BCrypt.gensalt(12)));
		}
		vo.setAdminEmail(adminEmail);
		vo.setAdminPhone(adminPhone);
		vo.setAdminMemo(adminMemo);
		
		
		
		if (!svc.adminModify(vo)) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자 정보 수정을 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/");
		return forward;
	}
}
