package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;
import shop.beggar.common.RegExp;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: RemoveProcAction.java
 * @Since			: 2020. 12. 7.
 * @Author			: HJLee
 * @Description		: 회원탍퇴 proc
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 7.		HJLee				최초 작성
 *
 */
public class RemoveProcAction implements Action{
 @Override
public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		
		if (id == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');"
					+ "location.href='/';</script>");
			out.close();
			return null;
		}
		
		String pwd = request.getParameter("pwd");
		
		if (RegExp.isEmpty(pwd)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
//		MemberVo vo = svc.getMemberLoginInfo(id);
//		if (vo == null || !pwd.equals(vo.getMember_pwd())) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('비밀번호를 확인해 주세요.');location.href='/';</script>");
//			out.close();
//			return null;
//		}
		
//		vo.setMember_del_fl(true);
		
//		if (!svc.leaveMember(vo)) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('회원탈퇴에 실패하였습니다.');location.href='/';</script>");
//			out.close();
//			return null;
//		}
		
		lm.removeSession(id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		forward.setRedirect(true);
		return forward;
}
}
