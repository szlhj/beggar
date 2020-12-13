package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.RandomGenerator;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: SearchPwdProcAction.java
 * @Since			: 2020. 12. 11.
 * @Author			: HJLee
 * @Description		: 비밀번호 찾기 로직
 * 					  임시비밀번호 10자리를 발급 받아 DB에 저장하고 임시비밀번호로 비밀번호를 변경하는 화면으로
 * 					  가서 변경을 할수 있게.
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 11.		HJLee				최초 작성
 *
 */
public class SearchPwdProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String shId = request.getParameter("shId");
		String shName = request.getParameter("shName");
		String shEmail = request.getParameter("shEmail");
		String shPhone = request.getParameter("shPhone");
		
		
		//임시비밀번호 생성
		String randomPwd = RandomGenerator.randomPwd();
		
//		if (randomPwd != null) {
//			response.setContentType("text/html;charset=UTF-8;");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('임시비밀번호는 " + randomPwd + ");history.back();</script>");
//			out.close();
//		}
		
		MemberVo vo = new MemberVo();
		vo.setId(shId);
		vo.setPwd(BCrypt.hashpw(randomPwd, BCrypt.gensalt(12)));
		
		MemberService svc = new MemberService();
		if (!svc.changeRandomPwd(vo)) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('임시비밀번호를 저장하지 못했습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		MemberVo chVo = svc.getMemberLoginInfo(vo);
		
		request.setAttribute("randomPwd", randomPwd);
		request.setAttribute("mber_sq", String.valueOf(chVo.getMber_sq()));
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/changePwd.jsp");
		return forward;
	}
}
