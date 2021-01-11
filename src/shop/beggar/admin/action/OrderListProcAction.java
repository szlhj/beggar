package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.OrderVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: OrderListProcAction.java
 * @Since			: 2021. 1. 10.
 * @Author			: HJLee
 * @Description		: 주문현황 배송상태 변경로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 10.		HJLee				최초 작성
 *
 */
public class OrderListProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String shipping = request.getParameter("shipping");
		String order_sq = request.getParameter("orderSq");
		
		String[] orderNumbers = order_sq.split(",");
		
		AdminService svc = new AdminService();
		OrderVo vo = new OrderVo();
		vo.setShipping(Integer.parseInt(shipping));
		
		for(int i = 0; i< orderNumbers.length; i++) {

			vo.setOrder_sq(Integer.parseInt(orderNumbers[i]));
			
			if (!svc.orderShipping(vo)) {
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근 입니다.');location.href='/';</script>");
				out.close();
				return null;
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/orderList");//경로
		return forward;
	}
}
