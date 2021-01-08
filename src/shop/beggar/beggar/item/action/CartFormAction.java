package shop.beggar.beggar.item.action;


import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;

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
public class CartFormAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginManager lm = LoginManager.getInstance();
		String id = lm.getMemberId(session);
		
		String item_sq_s = request.getParameter("item_sq"); //수량 변경을 위한 item_sq
		if(item_sq_s != null) { 
			String item_stok_string = request.getParameter("item_stok");
			String stok_string = request.getParameter("stok");
			int item_stok = Integer.parseInt(item_stok_string); //장바구니에 적힌 수량에서 +1 또는 -1
			int stok = Integer.parseInt(stok_string); //상품에 총재고 수량
			if(item_stok > stok) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('현재 구매할수 있는 최대 수량입니다.');history.back();</script>");
				out.close();
				return null;
			}
			if(item_stok < 1) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>history.back();</script>");
				out.close();
				return null;
			}
			MemberVo vo = (MemberVo) session.getAttribute("vo");
			int mber_sq = vo.getMber_sq();
			ItemVo ivo = new ItemVo();
			ivo.setItem_sq(Integer.parseInt(item_sq_s));
			ivo.setItem_stok(item_stok);
			ivo.setMber_sq(mber_sq); 
			ItemService svc = new ItemService();
			if(!(svc.changeCartStok(ivo))) { //장바구니 수량 +,- 버튼을 눌렀을때 카트 테이블에 바뀔 수량으로 update
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('수량을 변경하지 못했습니다.');history.back();</script>");
				out.close();
				return null;
			}
		}
		
		if(id == null) {
			Cookie[] cookies = request.getCookies();
			ItemVo ivo = null;
			for (int i = 0; i < cookies.length; i++) {
				Cookie c = cookies[i];
				String cName = c.getName();
				if (cName.equals("item_sq")) {
					String item_sq_string = c.getValue();
					ItemService isvc = new ItemService();
					int item_sq = Integer.parseInt(item_sq_string);
					ivo = isvc.cartItemInfo(item_sq);
					if (ivo == null) {
						response.setContentType("text/html;charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.println("<script>alert('쿠키 상품을 id장바구니에 상품을 저장하지 못했습니다.');history.back();</script>");
						out.close();
						return null;
						}
					}
				}
			
			request.setAttribute("ivo", ivo);
			
			ActionForward forward = new ActionForward();
			forward.setPath("/views/item/cartForm.jsp");
			return forward;
		} else {
			MemberVo vo = (MemberVo) session.getAttribute("vo");
			ItemService isvc = new ItemService();
			int mber_sq = vo.getMber_sq();
			int page = 1;
//			Pagenation pagenation = new Pagenation(page,isvc.getArticleItem(mber_sq));
			
			ArrayList<ItemVo> list = isvc.getCartPageInfo(mber_sq);
			request.setAttribute("list",list);
		}
		
//		String content = request.getParameter("content");
		
//		HttpSession session = request.getSession();
//		request.setAttribute("c", content);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/cartForm.jsp");
		return forward;
	}
}
