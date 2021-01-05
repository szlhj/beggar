package shop.beggar.admin.action;

import static shop.beggar.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;
import shop.beggar.common.ActionForward;
import shop.beggar.common.RegExp;

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
		if (pn == null) {
			pn = "1";
		}
		
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
		String firstTime= request.getParameter("firstTime");
		String lastTime= request.getParameter("lastTime");
		
		String query="";
		String firstTimeQuery="";
		String lastTimeQuery="";
		String idQuery="";
		String nameQuery="";
		
//		if(firstTime != null && !firstTime.equals("")) {
//			int firstTimeInt=Integer.parseInt(firstTime);
//			int firstTimeYear = firstTimeInt/10000;
//			int firstTimeMonth = (firstTimeInt%10000)/100;
//			int firstTimeDay = firstTimeInt%100;
//			
//			firstTime = (firstTimeYear + "-" + firstTimeMonth + "-" + firstTimeDay);
//		}
//		
//		if(lastTime != null && !lastTime.equals("")) {
//			int lastTimeInt=Integer.parseInt(lastTime);
//			int lastTimeYear = lastTimeInt/10000;
//			int lastTimeMonth = (lastTimeInt%10000)/100;
//			int lastTimeDay = lastTimeInt%100;
//			lastTime = (lastTimeYear + "-" + lastTimeMonth + "-" + lastTimeDay);
//		}
		
		if(firstTime == null || firstTime.equals("")) {
			firstTimeQuery = "";
		}
		else {
			firstTimeQuery = " and '"+firstTime+"'<=a.dttm";
		}
		
		if(lastTime == null || lastTime.equals("")) {
			lastTimeQuery="";
		}
		else {
			lastTimeQuery = " and a.dttm<='"+lastTime+"'";
		}
		
		if(filter == null || filter.equals("")) {
			filter="id";
		}
		
		if(keyword != null && !keyword.equals("")) {
			if(filter.equals("id")) {
				idQuery= " and (id like '%" + keyword + "%')";
			}else if(filter.equals("name")) {
				nameQuery= " and (name like '%" + keyword + "%')";
			}else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다1111.');" + "location.href()='/';</script>");
				out.close();
				return null;
			}
		}
		
		query = firstTimeQuery+lastTimeQuery+idQuery+nameQuery;
		
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
