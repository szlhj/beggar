package shop.beggar.beggar.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

/**
 * @PackageName		: shop.beggar.beggar.member.action
 * @FileName		: MyPageAction.java
 * @Since			: 2021. 1. 6.
 * @Author			: HJLee
 * @Description		: mypage 화면 경로
 * =====================================================================================
 * 								   Modification History
 * =====================================================================================
 * Date				Author				Note
 * -------------------------------------------------------------------------------------
 * 2021. 1. 6.		HJLee				최초 작성
 *
 */
public class MyPageAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		forward.setPath("/views/member/myPage.jsp");
		return forward;
	}
}
