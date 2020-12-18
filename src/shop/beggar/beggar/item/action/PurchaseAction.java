package shop.beggar.beggar.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;


/**
 * @PackageName		: shop.beggar.beggar.item.action
 * @FileName		: DetailAction.java
 * @Since			: 2020. 12. 3.
 * @Author			: HJLee
 * @Description		: 상품 상세 화면 경로 지정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 3.		HJLee				최초 작성
 *
 */
public class PurchaseAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/purchase.jsp");
		return forward;
	}
}
