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
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: RegisterProcAction.java
 * @Since			: 2020. 12. 9.
 * @Author			: HJLee
 * @Description		: 상품등록 로직
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 9.		HJLee				최초 작성
 *
 */
public class ItemAddProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//관리자 아이디 받기//
		
		
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
		
		HttpSession session = request.getSession();
		String directory = (String) session.getAttribute("fileImgNamePathDirectory");
		String newFileRealName = (String) session.getAttribute("fileImgNamePathRealName");
		String domain = (String) session.getAttribute("fileImgNameDomain");
		String pathFolder = (String) session.getAttribute("fileImgNamePathFoldery");
		
		ItemVo vo = new ItemVo();
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
		vo.setExplanation(Parser.chgToStr(explanation));
		vo.setFilepath(Parser.chgToStr(domain + pathFolder + newFileRealName));
		
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		
		AdminService svc = new AdminService();
		if (!svc.itemAdd(vo, adminVo.getAdmin_sq())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int item_sq = svc.searchItemSq(vo);
		
		if (!svc.fileItemSq(item_sq, newFileRealName)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품이미지 등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/itemList");
		forward.setRedirect(true);
		return forward;
	}
}
