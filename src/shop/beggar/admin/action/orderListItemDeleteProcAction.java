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
 * @FileName		: orderListItemDeleteProcAction.java
 * @Since			: 2021. 1. 11.
 * @Author			: HJLee
 * @Description		: 주문 아이템 삭제 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 11.		HJLee				최초 작성
 *
 */
public class orderListItemDeleteProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String order_sq = request.getParameter("orderSq");
		
		String[] orderSq = order_sq.split(",");
		
		AdminService svc = new AdminService();
		OrderVo vo = new OrderVo();
		
		for(int i = 0; i< orderSq.length; i++) {

			vo.setOrder_sq(Integer.parseInt(orderSq[i]));
			
			if (!svc.orderShippingDelete(vo)) {
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근 입니다.');location.href='/';</script>");
				out.close();
				return null;
			}
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/orderListItemDelete");//경로
		return forward;
	}
}
