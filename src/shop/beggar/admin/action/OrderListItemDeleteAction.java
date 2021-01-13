package shop.beggar.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.OrderVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: OrderListItemDeleteAction.java
 * @Since			: 2021. 1. 10.
 * @Author			: HJLee
 * @Description		: 주문 현황 삭제 화면으로
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 10.		HJLee				최초 작성
 *
 */
public class OrderListItemDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		
		if (adminVo == null) {
			response.setContentType("text/html;charset=UTF-8;");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근 입니다.');location.href='/';</script>");
		}
		
		AdminService svc = new AdminService();
		ArrayList<OrderVo> list = svc.itemDeleteList();
		
		request.setAttribute("orderListItemDeleteList", list);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/orderListItemDelete.jsp");//경로
		return forward;
	}
}
