package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.IpTraceUtils;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: LoginAdminProc.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 로그인 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class LoginAdminProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String adminId = request.getParameter("adminId");
		String adminPwd = request.getParameter("adminPwd");
		
		if (adminId == null
				|| adminId.equals("")
				|| adminPwd == null
				|| adminPwd.equals("")) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근 입니다.');location.href='/admin';</script>");
			out.close();
			return null;
		}
		
		AdminService svc = new AdminService();
		AdminVo vo = new AdminVo();
		vo.setAdminId(adminId);
		AdminVo adminVo = svc.adminLoginInfo(vo);
		
		if(adminVo == null 
				|| adminVo.getAdmin_sq() == 0
				|| !BCrypt.checkpw(adminPwd, adminVo.getAdminPwd())) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인 하세요.');history.back();</script>");
			out.close();
			return null;
		}
		
		String ipAddr = IpTraceUtils.getRemoteAddr(request);
		
		if (!svc.adminHistory(adminVo, ipAddr)) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 히스토리 저장에 실패 하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		int count = svc.supperAdminInfo();
		
		HttpSession session = request.getSession();
		session.setAttribute("adminVo", adminVo);
		session.setAttribute("adminSupperCount", count);
//		session.setMaxInactiveInterval(60*60);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/");
		return forward;
	}
}
