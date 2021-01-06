package shop.beggar.beggar.item.action;

import java.io.PrintWriter;

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
 * @FileName		: OrderDeleteAction.java
 * @Since			: 2021. 1. 5.
 * @Author			: HJLee
 * @Description		: 주문 삭제 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 5.		HJLee				최초 작성
 *
 */
public class OrderDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVo mberVo = (MemberVo)session.getAttribute("ssvo");
		
		String nonmber = request.getParameter("nonmber");
		
		OrderVo orderVo = new OrderVo();
		
		if (mberVo != null) {
			orderVo.setMber_sq(mberVo.getMber_sq());
		}
		orderVo.setNonmber(nonmber);
		
		ItemService svc = new ItemService();
		
		if (!svc.orderDelete(orderVo)) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('이미 배송이 시작 되어 삭제가 불가능 합니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		return forward;
	}
}
