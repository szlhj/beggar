/**
 * 
 */
package shop.beggar.beggar.home.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;

/**
 * @PackageName		: shop.beggar.beggar.home.action
 * @FileName		: HomeAction.java
 * @Since			: 2020. 12. 3.
 * @Author			: HJLee
 * @Description		: 메인 화면 경로 지정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 3.		HJLee				최초 작성
 *
 */
public class HomeAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("category");
		String pn = request.getParameter("pn");
		
		if (category == null || category.equals("")) {
			category = "0";
		}
		
		if (pn == null || pn.equals("")) {
			pn = "1";
		}
		
		int page = Integer.parseInt(pn);
		
		ItemVo vo = new ItemVo();
		vo.setCategory(category);
		
		ItemService svc = new ItemService();
		Pagenation pagenation = new Pagenation(page,svc.getArticleCount(category));
					
				
		ArrayList<ItemVo> list = svc.getItemPageInfo(pagenation, category);
		request.setAttribute("category", category);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/index.jsp?category="+category);
		return forward;
	}
}
