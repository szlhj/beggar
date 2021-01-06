package shop.beggar.beggar.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.item.action
 * @FileName		: OrderInquiryAction.java
 * @Since			: 2021. 1. 5.
 * @Author			: HJLee
 * @Description		: 비회원 주문조회
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 5.		HJLee				최초 작성
 *
 */
public class OrderInquiryAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/orderInquiry.jsp");
		return forward;
	}
}
