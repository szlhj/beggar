package shop.beggar.beggar.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.admin.action
 * @FileName		: ItemAddAction.java
 * @Since			: 2020. 12. 19.
 * @Author			: HJLee
 * @Description		: ��ǰ��� ȭ�� ��� ����
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 19.		HJLee				���� �ۼ�
 *
 */
public class BoardAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/boardAdd.jsp");
		return forward;
	}
}
