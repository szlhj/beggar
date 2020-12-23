package shop.beggar.beggar.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.board.action
 * @FileName		: WriteBoardAction.java
 * @Since			: 2020. 12. 17.
 * @Author			: HJLee
 * @Description		: 글쓰기 폼으로 이동
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 17.		HJLee				최초 작성
 *
 */
public class WriteBoardAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/writing.jsp");
		return forward;
	}
}
