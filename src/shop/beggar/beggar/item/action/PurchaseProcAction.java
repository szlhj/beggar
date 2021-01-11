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
import shop.beggar.common.Parser;


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
public class PurchaseProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberVo memberVo = (MemberVo) session.getAttribute("vo");
		String[] ck_item_sq = request.getParameterValues("ck_item_sq");
		String[] item_stok = request.getParameterValues("item_stok");
		String[] dis_price = request.getParameterValues("dis_price");
		if(memberVo == null) {
			OrderVo ovo = new OrderVo();
			String nonmber = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간 설정
			int mber_sq = 0;
			String payment = request.getParameter("payment");
			String record_item = request.getParameter("record");
			String addr_form = request.getParameter("formAddr");
			String addr_to = request.getParameter("toAddr");
			String name_form = request.getParameter("formName");
			String name_to = request.getParameter("toName");
			String name_form_phone = request.getParameter("formPhone");
			String name_to_phone = request.getParameter("toPhone");
			
			ovo.setNonmber(nonmber);
			ovo.setMber_sq(mber_sq);
			ovo.setOrder_payment_plan(Integer.parseInt(payment));
			ovo.setRecord_item(record_item);
			ovo.setAddr_form(addr_form);
			ovo.setAddr_to(addr_to);
			ovo.setName_form(name_form);
			ovo.setName_to(name_to);
			ovo.setName_form_phone(name_form_phone);
			ovo.setName_to_phone(name_to_phone);
			
			for(int i = 0; i < ck_item_sq.length; i++) {
				ItemService isvc = new ItemService();
				ovo.setItem_sq(Integer.parseInt(ck_item_sq[i]));
				ovo.setItem_stok(Integer.parseInt(item_stok[i]));
				ovo.setPrice(Integer.parseInt(dis_price[i]));
				
				if(!(isvc.registerOrderInfo(ovo))) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('주문내용을 입력하지 못했습니다.');history.back();</script>");
					out.close();
					return null;
				}
			}
			request.setAttribute("nonmber", nonmber);
		} else {
			OrderVo ovo = new OrderVo();
			String nonmber = new SimpleDateFormat("yyyyMMddHmsS").format(new Date()); //현재시간 설정
			int mber_sq = memberVo.getMber_sq();
			String payment = request.getParameter("payment");
			String record_item = request.getParameter("record");
			String addr_form = request.getParameter("formAddr");
			String addr_to = request.getParameter("toAddr");
			String name_form = request.getParameter("formName");
			String name_to = request.getParameter("toName");
			String name_form_phone = request.getParameter("formPhone");
			String name_to_phone = request.getParameter("toPhone");
			
			ovo.setNonmber(nonmber);
			ovo.setMber_sq(mber_sq);
			ovo.setOrder_payment_plan(Integer.parseInt(payment));
			ovo.setRecord_item(record_item);
			ovo.setAddr_form(addr_form);
			ovo.setAddr_to(addr_to);
			ovo.setName_form(name_form);
			ovo.setName_to(name_to);
			ovo.setName_form_phone(name_form_phone);
			ovo.setName_to_phone(name_to_phone);
			
			for(int i = 0; i < ck_item_sq.length; i++) {
				ItemService isvc = new ItemService();
				ovo.setItem_sq(Integer.parseInt(ck_item_sq[i]));
				ovo.setItem_stok(Integer.parseInt(item_stok[i]));
				ovo.setPrice(Integer.parseInt(dis_price[i]));
				
				if(!isvc.registerOrderInfo(ovo)) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>alert('주문내용을 입력하지 못했습니다.');history.back();</script>");
					out.close();
					return null;
				}
			}
		}
					
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/purchaseProc.jsp");
		return forward;
	}
}
