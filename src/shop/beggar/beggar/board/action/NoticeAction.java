package shop.beggar.beggar.board.action;

import static shop.beggar.common.RegExp.REGEXP_NUMBER;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.service.AdminService;
import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.BoardVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.Pagenation;
import shop.beggar.common.RegExp;

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
public class NoticeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String pn = request.getParameter("pn");
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("vo");
		
		
		
		if (pn == null) {
			pn = "1";
		}

		int page = Integer.parseInt(pn);

		if (!RegExp.isValidExp(pn, REGEXP_NUMBER) || page < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/board/boardList?pn=1';</script>");
			out.close();
			return null;
		}

		String filter = request.getParameter("filter");
		String firstTime= request.getParameter("firstTime");
		String lastTime= request.getParameter("lastTime");
		
		String query = "";
		String firstTimeQuery="";
		String lastTimeQuery="";
		String board_numberQuery="";
		
		if (filter == null || filter.equals("")) {
			filter = "0";
		}
		if(firstTime == null || firstTime.equals("")) {
			firstTimeQuery = "";
		}
		else {
			firstTimeQuery = " and '"+firstTime+"'<=dttm";
		}
		
		if(lastTime == null || lastTime.equals("")) {
			lastTimeQuery="";
		}
		else {
			lastTimeQuery = " and dttm<='"+lastTime+"'";
		}
		filter = "1";//필터값지정
		if (filter == null || filter.equals("")) {
			board_numberQuery = "";
		}else{
			board_numberQuery = " and (board_number= '" + filter + "')";
		}
		
		query = firstTimeQuery+lastTimeQuery+board_numberQuery+" and (del_fl= 0)";
		
		AdminService svc = new AdminService();
		Pagenation pagenation = new Pagenation(page, svc.getBoardArticleCount(query));
		if (page > pagenation.getTotalPageCount()) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>location.href='/board/boardList?pn=" + pagenation.getTotalPageCount() + "';</script>");
			out.close();
			return null;
		}

		ArrayList<BoardVo> list = svc.getBoardArticleList(pagenation, query);
		request.setAttribute("pagenation", pagenation);
		request.setAttribute("list", list);
		request.setAttribute("filter", filter);
		request.setAttribute("pn", pn);
		request.setAttribute("adminVo", adminVo);
		request.setAttribute("vo", mberVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/boardList.jsp");// 경로
		
		return forward;
	}
}
