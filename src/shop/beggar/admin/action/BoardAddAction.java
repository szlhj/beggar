package shop.beggar.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.admin.vo.AdminVo;
import shop.beggar.beggar.vo.MemberVo;
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
		
		HttpSession session = request.getSession();
		AdminVo adminVo = (AdminVo) session.getAttribute("adminVo");
		MemberVo mberVo = (MemberVo) session.getAttribute("vo");
	    
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
		
		request.setAttribute("adminVo", adminVo);
		request.setAttribute("vo", mberVo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/views/admin/boardAdd.jsp");
		return forward;
	}
}
