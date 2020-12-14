package shop.beggar.beggar.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.home.action.HomeAction;
import shop.beggar.beggar.member.action.ChangePwdAction;
import shop.beggar.beggar.member.action.ChangePwdProcAction;
import shop.beggar.beggar.member.action.LoginAction;
import shop.beggar.beggar.member.action.LoginProcAction;
import shop.beggar.beggar.member.action.LogoutAction;
import shop.beggar.beggar.member.action.MemberShipAction;
import shop.beggar.beggar.member.action.ModifyAction;
import shop.beggar.beggar.member.action.ModifyProcAction;
import shop.beggar.beggar.member.action.RegisterAction;
import shop.beggar.beggar.member.action.RegisterProcAction;
import shop.beggar.beggar.member.action.RemoveAction;
import shop.beggar.beggar.member.action.SearchIdAction;
import shop.beggar.beggar.member.action.SearchIdProcAction;
import shop.beggar.beggar.member.action.SearchPwdAction;
import shop.beggar.beggar.member.action.SearchPwdProcAction;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// URL을 찾아오는 경로에 대해 *.do를 가져오는 코드
		String requesetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requesetURI.substring(contextPath.length()).replaceAll("/member", "");
		
		ActionForward forward = null;
		
		//화면 경로 설정
		if (command.equals("/")) {
			Action action = new HomeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/registerAction.do")) {  //회원가입
			Action action = new RegisterAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/RegisterProcAction.do")) {  //회원가입 로직
			Action action = new RegisterProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/removeAction.do")) {  //회원탈퇴
			Action action = new RemoveAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifyAction.do")) {  //회원상세 변경 이동
			Action action = new ModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifyProc.do")) {  //회원상세 변경
			Action action = new ModifyProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/mberShipAction.do")) {  //멤버십
			Action action = new MemberShipAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/loginAction.do")) {  //로그인
			Action action = new LoginAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/loginProcAction.do")) {  //로그인
			Action action = new LoginProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/logoutAction.do")) {  //로그인
			Action action = new LogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (command.equals("/registerProc.do")) {  //회원가입 상세
			Action action = new RegisterProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/searchId.do")) {  //아이디 찾기
			Action action = new SearchIdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/searchIdProcAction.do")) {  //아이디 찾기 로직
			Action action = new SearchIdProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/searchPwd.do")) {  //비밀번호 찾기
			Action action = new SearchPwdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/searchPwdProc.do")) {  //비밀번호 찾기
			Action action = new SearchPwdProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/changePwd.do")) {  //비밀번호 찾기
			Action action = new ChangePwdAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/changePwdProc.do")) {  //비밀번호 찾기
			Action action = new ChangePwdProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//
		if (forward != null) {
			if (forward.isRedirect()) {
				//Redirect
				response.sendRedirect(forward.getPath());
			}else {
				//RequestDispatcher
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request,  response);
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
