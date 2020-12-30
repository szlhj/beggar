package shop.beggar.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.ItemVo;
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
public class BoardModifyProcAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("memberVo");
	    
		int sq = 0;
		//일반 회원인 경우
		
		//관리자 회원인 경우
		if (mberVo == null) {
			if(adminVo!=null) {
				sq = 0;//adminVo.getAdmin_sq();	
			}
		} else {
			sq = mberVo.getMber_sq();
		}
		
		String board_number = request.getParameter("board_number");
		String goods_info = request.getParameter("goods_info");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String board_sq = request.getParameter("board_sq");
		
		if(board_sq==null||board_sq.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근입니다.');location.href='/';</script>");
			out.close();
			return null;
		}
		
		int b_sq = Integer.parseInt(board_sq);
		
		BoardVo vo = new BoardVo();
		vo.setBoard_number(board_number);
		vo.setGoods_info(goods_info);
		vo.setTitle(title);
		vo.setContent(Parser.chgToStr(content));
		vo.setBoard_sq(b_sq);
		
		AdminService svc = new AdminService();
		if (!svc.boardModify(vo)) {
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
