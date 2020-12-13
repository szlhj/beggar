package shop.beggar.beggar.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.RegExp;

import static shop.beggar.common.RegExp.*;

import java.io.PrintWriter;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: RegisterProcAction.java
 * @Since			: 2020. 12. 9.
 * @Author			: HJLee
 * @Description		: 회원가입 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 9.		HJLee				최초 작성
 *
 */
public class RegisterProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		String name = request.getParameter("name");
		String phone = request.getParameter("tel");
		String email = request.getParameter("email");
		String email_fl_string = request.getParameter("email_fl");
		String sms_fl_string = request.getParameter("sms_fl");
		
		if (!RegExp.isValidExp(id, REGEXP_ID)
				|| !RegExp.isValidExp(pwd, REGEXP_PWD)
				|| !RegExp.isValidExp(name, REGEXP_NAME)
				|| !RegExp.isValidExp(phone, REGEXP_NUMBER)
				|| !RegExp.isValidExp(email, REGEXP_EMAIL)
				|| !pwd.equals(pwdc)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		boolean email_fl = false;
		boolean sms_fl = false;
		
		if (email_fl_string != null && email_fl_string.equals("Y")) {
			email_fl = true;
		}
		if (sms_fl_string != null && sms_fl_string.equals("Y")) {
			sms_fl = true;
		}
		
		MemberVo vo = new MemberVo();
		vo.setId(id);
		vo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		vo.setName(name);
		vo.setPhone(phone);
		vo.setEmail(email);
		vo.setEmail_fl(email_fl);
		vo.setSms_fl(sms_fl);
		
		MemberService svc = new MemberService();
		if (!svc.registerMember(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원가입에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
	}
}
