package shop.beggar.admin.action;

import static shop.beggar.common.RegExp.*;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.beggar.common.RegExp;
import shop.beggar.admin.service.AdminService;
import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;
import shop.beggar.common.ActionForward;

/**
 * @PackageName : shop.beggar.beggar.member.action
 * @FileName : ItemListAction.java
 * @Since : 2020. 12. 9.
 * @Author : HJLee
 * @Description : 상품조회 화면 경로
 *              =====================================================================================
 *              Modification History
 *              =====================================================================================
 *              Date Author Note
 *              -------------------------------------------------------------------------------------
 *              2020. 12. 9. HJLee 최초 작성
 *
 */
public class BoardListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pn = request.getParameter("pn");

		if (pn == null) {
			pn = "1";
		}

		int page = Integer.parseInt(pn);

		if (!RegExp.isValidExp(pn, REGEXP_NUMBER) || page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/boardList?pn=1';</script>");
			out.close();
			return null;
		}

		String filter = request.getParameter("filter");
		String query = "";
		if (filter == null || filter.equals("")) {
			query = "";
		} else {
			query = " and (board_number= '" + filter + "')";
		}
		AdminService svc = new AdminService();
		Pagenation pagenation = new Pagenation(page, svc.getBoardArticleCount());
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/admin/boardList?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<BoardVo> list = svc.getBoardArticleList(pagenation, query);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		request.setAttribute("filter", filter);
		request.setAttribute("pn", pn);

		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/boardList.jsp");// 경로
		return forward;
	}
}
