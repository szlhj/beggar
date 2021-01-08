package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.MemberVo;
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
public class BoardAnswerProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("vo");
		
		String board_sq = request.getParameter("board_sq");
		String comment = request.getParameter("comment");
		
		if(board_sq==null||board_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int b_sq = Integer.parseInt(board_sq);
		
		BoardVo vo = new BoardVo();
		vo.setBoard_sq(b_sq);
		vo.setComment(comment);
		
		AdminService svc = new AdminService();
		if (!svc.boardAnswer(vo)) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('게시판등록에 실패하였습니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("/admin/boardList");// 경로
		forward.setRedirect(true);
		return forward;
	}
}