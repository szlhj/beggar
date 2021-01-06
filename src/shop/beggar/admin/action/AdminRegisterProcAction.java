package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.RegExp;

import static shop.beggar.common.RegExp.*;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: memberAdminRegisterProc.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class AdminRegisterProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String adminId = request.getParameter("adminId");
		String adminPwd = request.getParameter("adminPwd");
		String adminPwdc = request.getParameter("adminPwdc");
		String adminName = request.getParameter("adminName");
		String adminPhone = request.getParameter("adminPhone");
		String adminEmail = request.getParameter("adminEmail");
		String adminMemo = request.getParameter("adminMemo");
		String adminSupper = request.getParameter("adminSupper");
		boolean isAdminSupper = false;		
		
		if (adminId == null || adminId.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/admin';/script>");
			out.close();
			return null;
		}
		
		if (!RegExp.isValidExp(adminId, REGEXP_ID)
				|| !RegExp.isValidExp(adminPwd, REGEXP_PWD)
				|| !RegExp.isValidExp(adminName, REGEXP_NAME)
				|| !RegExp.isValidExp(adminPhone, REGEXP_NUMBER)
				|| !RegExp.isValidExp(adminEmail, REGEXP_EMAIL)
				|| !adminPwd.equals(adminPwdc)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입 정보를 확인하여 주세요...');location.href='/';</script>");
			out.close();
			return null;
		}
		
		AdminVo vo = new AdminVo();
		
		vo.setAdminId(adminId);
		vo.setAdminPwd(BCrypt.hashpw(adminPwd, BCrypt.gensalt(12)));
		
		vo.setAdminName(adminName);
		vo.setAdminPhone(adminPhone);
		vo.setAdminEmail(adminEmail);
		vo.setAdminMemo(adminMemo);
		
		if (adminSupper != null && adminSupper.equals("on")) {
			isAdminSupper = true;
		}
		
		vo.setAdmin_supper(isAdminSupper);
		
		AdminService svc = new AdminService();
		
		if (!svc.registerAdmin(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('관리자 회원가입에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin");
		return forward;
	}
}
