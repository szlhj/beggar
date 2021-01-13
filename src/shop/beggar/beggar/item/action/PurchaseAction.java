package shop.beggar.beggar.item.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.beggar.vo.OrderVo;
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
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("vo");
		ItemService isvc = new ItemService();
		String[] ck_item_sq = request.getParameterValues("ck_item_sq");
		String item_sq_string = request.getParameter("item_sq");
		if (memberVo == null && item_sq_string != null) { //비회원 장바구니,비회원 상품 디테일 주문하기 눌렀을때 
			int item_sq = Integer.parseInt(item_sq_string);
			ItemVo ivo = null;
			ivo = isvc.cartItemInfo(item_sq); 
			if (ivo == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('장바구니상품을 가져오지 못했습니다.');history.back();</script>");
				out.close();
				return null;
				}
			String item_stok = request.getParameter("i_stok");
			ivo.setItem_stok(Integer.parseInt(item_stok));
			request.setAttribute("ivo",ivo);
		} else if (memberVo != null && item_sq_string != null) { // 로그인 상품디테일에서 주문하기 눌렀을때
			int item_sq = Integer.parseInt(item_sq_string);
			String item_stok = request.getParameter("i_stok");
			ItemVo ivo = null;
			ivo = isvc.cartItemInfo(item_sq); 
			if (ivo == null) { 
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('상품정보를 가져오지 못했습니다.');history.back();</script>");
				out.close();
				return null;	
				}
			int mber_sq = memberVo.getMber_sq();
			OrderVo imvo = null;
			imvo = isvc.purchaseAddrInfo(mber_sq);
			request.setAttribute("imvo", imvo);
			ivo.setItem_stok(Integer.parseInt(item_stok));
			request.setAttribute("ivo",ivo);
		} else {  //로그인 장바구니에서 선택 주문하기 눌렀을때
			if ((memberVo == null || memberVo.getId().equals(""))) {
				response.setContentType("text/html;charset=UTF-8;");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
				out.close();
				return null;
			}
			
			int mber_sq = memberVo.getMber_sq();
			OrderVo imvo = null;
			imvo = isvc.purchaseAddrInfo(mber_sq);
			int countOrder = isvc.countOrderInfo(mber_sq) ;
			if (countOrder > 0 && imvo == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('상품정보를 가져오지 못했습니다.');history.back();</script>");
				out.close();
				return null;
				}
			ArrayList<ItemVo> purch_list = new ArrayList<ItemVo>();
			for(int i = 0 ; i < ck_item_sq.length; i++) {
				purch_list.add(isvc.getPurchasePage(mber_sq , Integer.parseInt(ck_item_sq[i])));
			}
			request.setAttribute("imvo", imvo);
			request.setAttribute("purch_list",purch_list);
		}
	
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/purchase.jsp");
		return forward;
	}
}
