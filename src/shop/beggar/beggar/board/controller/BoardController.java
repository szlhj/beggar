package shop.beggar.beggar.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import shop.beggar.beggar.board.action.BoardAction;
import shop.beggar.beggar.board.action.BoardAddAction;
import shop.beggar.beggar.board.action.BoardAddProcAction;
import shop.beggar.beggar.board.action.BoardDelAction;
import shop.beggar.beggar.board.action.BoardDetailAction;
import shop.beggar.beggar.board.action.BoardModifyProcAction;
import shop.beggar.beggar.board.action.BoardModifyViewAction;
import shop.beggar.beggar.board.action.MyQuestionAction;
import shop.beggar.beggar.board.action.NoticeAction;
import shop.beggar.beggar.board.action.OneAndOneQuestionAction;
import shop.beggar.beggar.board.action.ProductRelatedAction;
import shop.beggar.beggar.home.action.HomeAction;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// URL을 찾아오는 경로에 대해 *.do를 가져오는 코드
		String requesetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requesetURI.substring(contextPath.length()).replaceAll("/board", "");
		
		ActionForward forward = null;
		
		//화면 경로 설정
		if (command.equals("/")) {
			Action action = new BoardAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/productRelated")) { //제품관련
			Action action = new ProductRelatedAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/oneAndOneQuestion")) { //1:1문의
			Action action = new OneAndOneQuestionAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/myQuestion")) { //내가 쓴 글 보기
			Action action = new MyQuestionAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/notice")) { //내가 쓴 글 보기
			Action action = new NoticeAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/addBoard")) { 
			Action action = new BoardAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/addBoardProc")) { 
			Action action = new BoardAddProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/detailBoard")) { 
			Action action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/modifyBoardView")) { 
			Action action = new BoardModifyViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/delBoard")) { 
			Action action = new BoardDelAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/modifyBoardProc")) { 
			Action action = new BoardModifyProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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
