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
import shop.beggar.common.LoginManager;

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
		String mber_id = request.getParameter("mber_id");
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("vo");
		
		int sq = 0;
		//일반 회원인 경우
		
		//관리자 회원인 경우
		if (mberVo == null && adminVo!=null) {
				sq = 0;//adminVo.getAdmin_sq();	
		} else if(mberVo!=null){
			sq = mberVo.getMber_sq();
		} else {
			sq = 0;//비 로그인 회원
		}
		
		if(sq==0) {
			mberVo = new MemberVo();
			mberVo.setId("관리자");
		}
		
		AdminService svc = new AdminService();
		BoardVo vo = new BoardVo();
		vo.setBoard_sq(Integer.parseInt(board_sq));
		
		if(mberVo!=null&&!mber_id.equals(mberVo.getId())) {
			svc.increaseCount(vo);
		}
		
		BoardVo boardVo = svc.getBoardDetail(vo);
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("pn", pn);
		request.setAttribute("adminVo", adminVo);
		request.setAttribute("vo", mberVo);
		request.setAttribute("mber_id", mber_id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/boardDetail.jsp");
		return forward;
	}
}
