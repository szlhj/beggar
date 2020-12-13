package shop.beggar.beggar.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: SearchIdProcAction.java
 * @Since			: 2020. 12. 11.
 * @Author			: HJLee
 * @Description		: 아이디 찾기 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 11.		HJLee				최초 작성
 *
 */
public class SearchIdProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String searchName = request.getParameter("searchName");
		String searchEmail = request.getParameter("searchEmail");
		String searchTel = request.getParameter("searchTel");
		
		String query = " and name='" + searchName + "'";
		
		if (searchEmail == null
				&& searchTel == null
				|| searchEmail.equals("")
				&& searchTel.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이메일 과 정화번호중 한곳은 입력을 하여야 합니다.');</script>");
			out.close();
			return null;
		} else if (searchEmail == null
				&& searchTel != null
				|| searchEmail.equals("")
				&& !searchTel.equals("")) {
			query = query + " and phone='" + searchTel + "'";
		} else if (searchEmail != null
				&& searchTel == null
				|| !searchEmail.equals("")
				&& searchTel.equals("")) {
			query = query +  " and email='" + searchEmail + "'";
		} else if (searchEmail != null
				&& searchTel != null
				|| !searchEmail.equals("")
				&& !searchTel.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이메일 과 정화번호중 한곳만 입력을 하여야 합니다.');</script>");
			out.close();
			return null;
		}
		
		MemberService svc = new MemberService();
		String id = svc.searchId(query);
		
		request.setAttribute("searchId", id);
		request.setAttribute("pageCount", "1");
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/searchId.jsp");
		return forward;
	}
}
