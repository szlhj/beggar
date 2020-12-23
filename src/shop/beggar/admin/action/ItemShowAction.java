package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemShowAction.java
 * @Since			: 2020. 12. 20.
 * @Author			: HJLee
 * @Description		: 아이템 메인 화면에 등록하기 위한 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 20.		HJLee				최초 작성
 *
 */
public class ItemShowAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String item_sq = request.getParameter("sq");
		String show = (String) request.getParameter("isShow");
		
		boolean show_fl = false;
		if (show.equals("true")) {
			show_fl = true;
		}
		AdminService svc = new AdminService();
		
		ItemVo vo = new ItemVo();
		vo.setItem_sq(Integer.parseInt(item_sq));
		
		ItemVo itemVo = svc.getItemDetail(vo);
		
		if (itemVo.isDel_fl()) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 상품은 삭제 되어 있습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		
		vo.setShow_fl(show_fl);
		
		if (!svc.itemShow(vo, adminVo.getAdmin_sq())) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품 등제에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/itemDetail?item_sq=" + item_sq);
		return forward;
	}
}
