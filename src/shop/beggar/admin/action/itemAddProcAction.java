package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.member.service.MemberService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.BCrypt;
import shop.beggar.common.RegExp;

import static shop.beggar.common.RegExp.*;

import java.io.PrintWriter;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: RegisterProcAction.java
 * @Since			: 2020. 12. 9.
 * @Author			: HJLee
 * @Description		: 회원가입 로직
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
		String explanation = request.getParameter("explanation");
		
//		if (!RegExp.isValidExp(item_name, REGEXP_ID)
//				|| !RegExp.isValidExp(pwd, REGEXP_PWD)
//				|| !RegExp.isValidExp(name, REGEXP_NAME)
//				|| !RegExp.isValidExp(phone, REGEXP_NUMBER)
//				|| !RegExp.isValidExp(email, REGEXP_EMAIL)
//				|| !pwd.equals(pwdc)) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
//			out.close();
//			return null;
//		}
//		
//		boolean email_fl = false;
//		boolean sms_fl = false;
//		
//		if (email_fl_string != null && email_fl_string.equals("Y")) {
//			email_fl = true;
//		}
//		if (sms_fl_string != null && sms_fl_string.equals("Y")) {
//			sms_fl = true;
//		}
//		
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
		vo.setExplanation(explanation);
		
		AdminService svc = new AdminService();
		if (!svc.itemAdd(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('상품등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
//		
//		MemberService svc = new MemberService();
//		if (!svc.registerMember(vo)) {
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>alert('회원가입에 실패하였습니다.');location.href='/';</script>");
//			out.close();
//			return null;
//		}
//		
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/main.jsp");
		forward.setRedirect(true);
		return forward;
	}
}
