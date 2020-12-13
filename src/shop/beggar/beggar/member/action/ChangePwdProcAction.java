package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: ChangePwdProcAction.java
 * @Since			: 2020. 12. 11.
 * @Author			: HJLee
 * @Description		: 비밀변호 변경 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 11.		HJLee				최초 작성
 *
 */
public class ChangePwdProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String extantPwd = request.getParameter("extantPwd");
		String newPwd = request.getParameter("newPwd");
		String newPwdc = request.getParameter("newPwdc");
		String mber_sq = request.getParameter("mber_sq");
		
		if (extantPwd == null || newPwd == null || newPwdc == null
				|| !newPwd.equals(newPwdc)
				|| mber_sq == null || mber_sq.equals("")) {
			response.setContentType("text/html;charset-UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberVo vo = new MemberVo();
		vo.setMber_sq(Integer.parseInt(mber_sq));
		vo.setPwd(BCrypt.hashpw(newPwd, BCrypt.gensalt(12)));
		
		MemberService svc = new MemberService();
		
		if (!svc.changeNewPwd(vo)) {
			response.setContentType("text/html;charset-UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('새로운 비밀번호 저장에 실패하였습니다.');history.back;</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
