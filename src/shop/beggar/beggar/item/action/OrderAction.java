package shop.beggar.beggar.item.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.beggar.vo.OrderVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.item.action
 * @FileName		: OrderAction.java
 * @Since			: 2020. 12. 31.
 * @Author			: HJLee
 * @Description		: 결제 화면 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 31.		HJLee				최초 작성
 *
 */
public class OrderAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("ssvo");
		
		String nonmber = "1234";
		
		if ((memberVo == null || memberVo.getId().equals("")) && (nonmber == null || nonmber.equals(""))) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ItemService svc = new ItemService();
		OrderVo orderVo = null;
		ArrayList<OrderVo> orderList = null;
		
		if (memberVo == null) {
//			비회원
			orderList = svc.orderItemListnonmber(nonmber);
		} else {
//			회원
			orderVo = svc.orderMberInfo(memberVo.getMber_sq());
			orderList = svc.orderItemList(memberVo.getMber_sq());
		}
		
		request.setAttribute("orderVo", orderVo);
		request.setAttribute("orderList", orderList);
		request.setAttribute("nonmber", nonmber);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/order.jsp");
		return forward;
	}
}
