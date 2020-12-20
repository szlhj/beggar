package shop.beggar.admin.action;

import static shop.beggar.common.RegExp.*;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.beggar.common.RegExp;
import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: MemberManagementAction.java
 * @Since			: 2020. 12. 18.
 * @Author			: HJLee
 * @Description		: 회원 리스트 보기
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 18.		HJLee				최초 작성
 *
 */
public class MemberManagementAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String pn = request.getParameter("pn");
		int page = Integer.parseInt(pn);
		
		if(!RegExp.isValidExp(pn, REGEXP_NUMBER)
				|| page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/memberManagement?pn=1';</script>");
			out.close();
			return null;
		}
		
		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String query="";
		if(filter == null || filter.equals("")) {
			filter="id";
		}
		if(keyword != null && !keyword.equals("")) {
			if(filter.equals("id")) {
				query= " and (id like '%" + keyword + "%')";
			}else if(filter.equals("name")) {
				query= " and (name like '%" + keyword + "%')";
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다1111.');" + "location.href()='/';</script>");
				out.close();
				return null;
			}
		}
		
		AdminService svc = new AdminService();
		Pagenation pagenation = new Pagenation(page,svc.getArticleCount());
		if(page>pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/memberManagement?pn="+pagenation.getTotalPageCount()+"';</script>");
			out.close();
			return null;
		}
		
		
		ArrayList<MemberVo> list = svc.getArticleList(pagenation, query);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/memberViews.jsp");//경로
		return forward;
	}
}
