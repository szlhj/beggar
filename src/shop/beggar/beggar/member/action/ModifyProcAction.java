package shop.beggar.beggar.member.action;

import static shop.beggar.common.RegExp.REGEXP_EMAIL;
import static shop.beggar.common.RegExp.REGEXP_ID;
import static shop.beggar.common.RegExp.REGEXP_NAME;
import static shop.beggar.common.RegExp.REGEXP_NUMBER;
import static shop.beggar.common.RegExp.REGEXP_PWD;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.LoginManager;
import shop.beggar.common.RegExp;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: MidifyProcAction.java
 * @Since			: 2020. 12. 14.
 * @Author			: HJLee
 * @Description		: ȸ������ ȭ�� ��� ����
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 14.		HJLee				���� �ۼ�
 *
 */
public class ModifyProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		
		if (id == null) {
			ActionForward forward = new ActionForward();
			forward.setPath("/");
			forward.setRedirect(true);
			return forward;
		}
		
		String pwd = request.getParameter("pwd");
		String pwdc = request.getParameter("pwdc");
		if( !RegExp.isValidExp(pwd, REGEXP_PWD)
				|| !pwd.equals(pwdc))
				 { //��й�ȣ�� �˻���
			response.setContentType("text/html;charset=UTF-8"); //�亯�� ������ �Ӽ�
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� ������ ��й�ȣ�Դϴ�.');"
					+ "location.href='/';</script>");
			out.close();
			return null;
		}
		MemberService svc = new MemberService();
		MemberVo mfVo	= (MemberVo) session.getAttribute("vo");
		
		if(pwd != null || !(pwd.equals(""))) {
			mfVo.setPwd(pwd);
		} else {
			mfVo.setPwd(null);
		}
		
		mfVo.setPwd(BCrypt.hashpw(pwd, BCrypt.gensalt(12)));
		
		String phone = request.getParameter("tel");
		String email = request.getParameter("email");
		String sms_fl_string = request.getParameter("sms_fl");
		String email_fl_string = request.getParameter("email_fl");
		boolean email_fl = false;
		boolean sms_fl = false;
		
		if (email_fl_string != null && email_fl_string.equals("Y")) {
			email_fl = true;
		}
		if (sms_fl_string != null && sms_fl_string.equals("Y")) {
			sms_fl = true;
		}
		if ( !RegExp.isValidExp(phone, REGEXP_NUMBER)
				|| !RegExp.isValidExp(email, REGEXP_EMAIL)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('�߸��� �����Դϴ�.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		mfVo.setPhone(phone);
		mfVo.setEmail(email);
		
		
		mfVo.setEmail_fl(email_fl);
		mfVo.setSms_fl(sms_fl);
		
		if(!svc.modifyMember(mfVo)) { //���������� �����Ѱ��
			response.setContentType("text/html;charset=UTF-8"); //�亯�� ������ �Ӽ�
			PrintWriter out = response.getWriter();
//			out.println("<script>alert('�߸��� �����Դϴ�.');location.href='/';</script>");
			out.println("<script>alert('ȸ�� ���� ������ �����Ͽ����ϴ�.');"
					+ "history.back();</script>");
			out.close();
			return null;
			}
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		return forward;
	}
}
