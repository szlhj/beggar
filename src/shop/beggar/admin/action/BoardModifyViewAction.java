package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemModifyViewAction.java
 * @Since			: 2020. 12. 21.
 * @Author			: HJLee
 * @Description		: 상품 상세 화면 보여주기
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 21.		HJLee				최초 작성
 *
 */
public class BoardModifyViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String pn = request.getParameter("pn");
		String board_sq = request.getParameter("board_sq");
		
		AdminService svc = new AdminService();
		BoardVo vo = new BoardVo();
		vo.setBoard_sq(Integer.parseInt(board_sq));
		BoardVo boardVo = svc.getBoardDetail(vo);
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("pn", pn);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/boardDetailModify.jsp");
		return forward;
	}
}
