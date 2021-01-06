package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemDetailAction.java
 * @Since			: 2020. 12. 19.
 * @Author			: HJLee
 * @Description		: 관리자 상품 상세 화면 경로설정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 19.		HJLee				최초 작성
 *
 */
public class BoardDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String board_sq = request.getParameter("board_sq");
		String pn = request.getParameter("pn");
		
		AdminService svc = new AdminService();
		BoardVo vo = new BoardVo();
		vo.setBoard_sq(Integer.parseInt(board_sq));
		
//		svc.increaseCount(Integer.parseInt(board_sq));
				
		BoardVo boardVo = svc.getBoardDetail(vo);
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/boardDetail.jsp");
		return forward;
	}
}
