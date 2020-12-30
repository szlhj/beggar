package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Parser;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemModifyProcAction.java
 * @Since			: 2020. 12. 20.
 * @Author			: HJLee
 * @Description		: 상품상세 수정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 20.		HJLee				최초 작성
 *
 */
public class ItemModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		
		String item_name = request.getParameter("item_name");
		String category = request.getParameter("category");
		String code = request.getParameter("code");
		String price = request.getParameter("price");
		String discount = request.getParameter("discount");
		String stok = request.getParameter("stok");
		String color = request.getParameter("color");
		String item_number = request.getParameter("item_number");
		String item_rating = request.getParameter("item_rating");
		String size = request.getParameter("size");
		String explanation = request.getParameter("content");
		
		String newFileRealName = (String) session.getAttribute("fileImgNamePathRealName");
		String domain = (String) session.getAttribute("fileImgNameDomain");
		String pathFolder = (String) session.getAttribute("fileImgNamePathFoldery");
		
		String pn = request.getParameter("pn");
		String item_sq = request.getParameter("item_sq");
		
		if (item_sq == null || item_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근 입니다.');location.href='/admin';</script>");
			out.close();
			return null;
		}
		
		ItemVo vo = new ItemVo();
		vo.setItem_sq(Integer.parseInt(item_sq));
		vo.setItem_name(item_name);
		vo.setCategory(category);
		vo.setCode(code);
		vo.setPrice(Integer.parseInt(price));
		vo.setDiscount(Integer.parseInt(discount));
		vo.setStok(Integer.parseInt(stok));
		vo.setColor(color);
		vo.setItem_number(item_number);
		vo.setItem_rating(item_rating);
		vo.setSize(size);
		if (explanation != null) {
			vo.setExplanation(Parser.chgToStr(explanation));
		}
		vo.setFilepath(domain + pathFolder + newFileRealName);
		
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		
		AdminService svc = new AdminService();
		if (!svc.itemModify(vo, adminVo.getAdmin_sq())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품수정에 실패하였습니다.');history.back();</script>");
			out.close();
			return null;
		}
		
		if (!svc.fileItemSq(Integer.parseInt(item_sq), newFileRealName)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품이미지 등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/itemList?pn=" + pn);
		return forward;
	}
}
