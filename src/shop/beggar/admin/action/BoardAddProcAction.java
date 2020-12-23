package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.BoardVo;
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
public class BoardAddProcAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//관리자 아이디 받기//
		
		String board_number = request.getParameter("board_number");
		String goods_info = request.getParameter("goods_info");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		BoardVo vo = new BoardVo();
		vo.setBoard_number(board_number);
		vo.setGoods_info(goods_info);
		vo.setTitle(title);
		vo.setContent(Parser.chgToStr(content));
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		
		AdminService svc = new AdminService();
		if (!svc.boardAdd(vo, adminVo.getAdmin_sq())) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시판등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/");
		forward.setRedirect(true);
		return forward;
	}
}
