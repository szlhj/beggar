package shop.beggar.beggar.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.MemberVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.board.action
 * @FileName		: BoardAction.java
 * @Since			: 2020. 12. 3.
 * @Author			: HJLee
 * @Description		: 게사판 메인 화면 경로 지정
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2020. 12. 3.		HJLee				최초 작성
 *
 */
public class BoardAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("vo");
		
		//일반 회원인 경우
		
		if(adminVo!=null) {
			mberVo = new MemberVo();
			mberVo.setId("관리자");
		}else if(adminVo==null&&mberVo==null) {
			mberVo = new MemberVo();
			mberVo.setId("비회원");
		}
		
		request.setAttribute("adminVo", adminVo);
		request.setAttribute("vo", mberVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/board.jsp");
		return forward;
	}
}
