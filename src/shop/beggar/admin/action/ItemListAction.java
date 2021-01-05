package shop.beggar.admin.action;

import static shop.beggar.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;
import shop.beggar.common.RegExp;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: ItemListAction.java
 * @Since			: 2020. 12. 9.
 * @Author			: HJLee
 * @Description		: 상품조회 화면 경로
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 9.		HJLee				최초 작성
 *
 */
public class ItemListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pn = request.getParameter("pn");
		
		if (pn == null) {
			pn = "1";
		}
		
		int page = Integer.parseInt(pn);
		
		if(!RegExp.isValidExp(pn, REGEXP_NUMBER)
				|| page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/itemList?pn=1';</script>");
			out.close();
			return null;
		}
		
		String filter = request.getParameter("filter");
		String keyword = request.getParameter("keyword");
		String query="";
		if(filter == null || filter.equals("")) {
			filter="code";
		}
		if(keyword != null && !keyword.equals("")) {
			if(filter.equals("code")) {
				query= " and (code like '%" + keyword + "%')";
			} else if(filter.equals("name")) {
				query= " and (item_name like '%" + keyword + "%')";
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.');location.href='/admin/';</script>");
				out.close();
				return null;
			}
		}
		
		AdminService svc = new AdminService();
		Pagenation pagenation = new Pagenation(page,svc.getItemArticleCount());
		if(page>pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/itemList?pn="+pagenation.getTotalPageCount()+"';</script>");
			out.close();
			return null;
		}
		
		
		ArrayList<ItemVo> list = svc.getItemArticleList(pagenation, query);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		request.setAttribute("filter", filter);
		request.setAttribute("keyword", keyword);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/itemList.jsp");//경로
		return forward;
	}
}
