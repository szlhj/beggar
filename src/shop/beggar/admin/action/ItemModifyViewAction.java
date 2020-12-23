package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemModifyViewAction.java
 * @Since			: 2020. 12. 21.
 * @Author			: HJLee
 * @Description		: 상품 상세 화면 보여주기
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 21.		HJLee				최초 작성
 *
 */
public class ItemModifyViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pn = request.getParameter("pn");
		String item_sq = request.getParameter("item_sq");
		
		AdminService svc = new AdminService();
		ItemVo vo = new ItemVo();
		vo.setItem_sq(Integer.parseInt(item_sq));
		ItemVo itemVo = svc.getItemDetail(vo);
		
		request.setAttribute("itemVo", itemVo);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/itemDetailModify.jsp");
		return forward;
	}
}
