package shop.beggar.beggar.item.action;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.RegExp;

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
public class DetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String item_sq_string = request.getParameter("item_sq");
		
		int item_sq = Integer.parseInt(item_sq_string);
		
		if(item_sq <1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 상품 번호입니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		ItemVo vo = new ItemVo();
		vo.setItem_sq(item_sq);
		
		ItemService svc = new ItemService();
		ItemVo ivo = svc.getItemDetailInfo(vo);
		if(ivo == null) {
			response.setContentType("text/html;charset=UTF-8"); //답변줄 페이지 속성
			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보를 불러오는데 실패하였습니다.');"
					+ "history.back();</script>");
			out.close();
			return null;
		}
		
//		String content = request.getParameter("content");
		
//		HttpSession session = request.getSession();
//		request.setAttribute("c", content);
		
		request.setAttribute("ivo", ivo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/item/detail.jsp");
		return forward;
	}
}
