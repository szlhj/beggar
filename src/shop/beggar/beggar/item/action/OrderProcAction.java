package shop.beggar.beggar.item.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.OrderVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.item.action
 * @FileName		: OrderProcAction.java
 * @Since			: 2021. 1. 2.
 * @Author			: HJLee
 * @Description		: 결제 로직 화면
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 2.		HJLee				최초 작성
 *
 */
public class OrderProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String mber_sq = request.getParameter("mber_sq");
		String nonmber = request.getParameter("nonmber");
		
		String payment = request.getParameter("payment");
		String formName = request.getParameter("formName");
		String formPhone = request.getParameter("formPhone");
		String formAddr = request.getParameter("formAddr");
		String toName = request.getParameter("toName");
		String toPhone = request.getParameter("toPhone");
		String toAddr = request.getParameter("toAddr");
		String record = request.getParameter("record");
		
		OrderVo vo = new OrderVo();
		
		vo.setOrder_payment_plan(Integer.parseInt(payment));
		vo.setAddr_form(formAddr);
		vo.setName_form_phone(formPhone);
		vo.setName_form(formName);
		vo.setAddr_to(toAddr);
		vo.setName_to(toName);
		vo.setName_to_phone(toPhone);
		vo.setRecord_item(record);
		
		ItemService svc = new ItemService();
		
//		-------------------------------------------------------------------
		if (!mber_sq.equals("0")) {
//			회원
			vo.setMber_sq(Integer.parseInt(mber_sq));
			if (!svc.orderPayment(vo)) {
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('결재를 실패 하였습니다.');history.back();</script>");
				out.close();
				return null;
			}
		} else {
			vo.setNonmber(nonmber);
			if (!svc.orderPaymentNonmber(vo)) {
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('결재를 실패 하였습니다.');history.back();</script>");
				out.close();
				return null;
			}
		}
//		----------------------------------------------------------------------
		
		ActionForward forward = new ActionForward();
		forward.setPath("/");
		return forward;
	}
}
