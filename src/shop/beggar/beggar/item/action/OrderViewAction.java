package shop.beggar.beggar.item.action;

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
 * @FileName		: OrderViewAction.java
 * @Since			: 2021. 1. 4.
 * @Author			: HJLee
 * @Description		: 결제 완료 후 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 4.		HJLee				최초 작성
 *
 */
public class OrderViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVo mberVo = (MemberVo) session.getAttribute("ssvo");
		
		String nonmber = request.getParameter("nonmber");
		
		ItemService svc = new ItemService();
		OrderVo vo = new OrderVo();
		if (mberVo != null) {
			vo.setMber_sq(mberVo.getMber_sq());
		}
		vo.setNonmber(nonmber);
		
		ArrayList<OrderVo> List = svc.orderPaymentList(vo);
		
		OrderVo voAddr = svc.orderPaymentListAddr(vo);
		
		request.setAttribute("orderVoList", List);
		request.setAttribute("nonmber", nonmber);
		request.setAttribute("orderVo", voAddr);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/orderView.jsp");
		return forward;
	}
}
