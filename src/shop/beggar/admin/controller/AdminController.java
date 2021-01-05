package shop.beggar.admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.admin.action.AdminAction;
import shop.beggar.admin.action.AdminDetailAction;
import shop.beggar.admin.action.AdminListAction;
import shop.beggar.admin.action.AdminModifyAction;
import shop.beggar.admin.action.AdminModifyProcAction;
import shop.beggar.admin.action.AdminRegisterAction;
import shop.beggar.admin.action.AdminRegisterProcAction;
import shop.beggar.admin.action.BoardAddAction;
import shop.beggar.admin.action.BoardAddProcAction;
import shop.beggar.admin.action.BoardDelAction;
import shop.beggar.admin.action.BoardDetailAction;
import shop.beggar.admin.action.BoardListAction;
import shop.beggar.admin.action.BoardModifyProcAction;
import shop.beggar.admin.action.BoardModifyViewAction;
import shop.beggar.admin.action.FileExAction;
import shop.beggar.admin.action.FileUploadAction;
import shop.beggar.admin.action.ItemAddAction;
import shop.beggar.admin.action.ItemAddProcAction;
import shop.beggar.admin.action.ItemDelAction;
import shop.beggar.admin.action.ItemDetailAction;
import shop.beggar.admin.action.ItemListAction;
import shop.beggar.admin.action.ItemModifyProcAction;
import shop.beggar.admin.action.ItemModifyViewAction;
import shop.beggar.admin.action.ItemShowAction;
import shop.beggar.admin.action.LoginAdminProcAction;
import shop.beggar.admin.action.LogoutAdminAction;
import shop.beggar.admin.action.AdminRegisterAction;
import shop.beggar.admin.action.MemberManagementAction;
import shop.beggar.admin.action.AdminRegisterProcAction;
import shop.beggar.admin.action.BoardAddAction;
import shop.beggar.admin.action.BoardAddProcAction;
import shop.beggar.admin.action.BoardDelAction;
import shop.beggar.admin.action.BoardDetailAction;
import shop.beggar.admin.action.BoardListAction;
import shop.beggar.admin.action.BoardModifyProcAction;
import shop.beggar.admin.action.BoardModifyViewAction;
import shop.beggar.admin.action.EditCheckAction;
import shop.beggar.admin.action.FileExAction;
import shop.beggar.admin.action.FileUploadAction;
import shop.beggar.beggar.board.action.FrequentlyAction;
import shop.beggar.beggar.board.action.MyQuestionAction;
import shop.beggar.beggar.board.action.OneAndOneQuestionAction;
import shop.beggar.beggar.board.action.ProductRelatedAction;
import shop.beggar.beggar.home.action.HomeAction;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// URL을 찾아오는 경로에 대해 *.do를 가져오는 코드
		String requesetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requesetURI.substring(contextPath.length()).replaceAll("/admin", "");
		
		ActionForward forward = null;
		
		//화면 경로 설정
		if (command.equals("/") || command.equals("")) {
			Action action = new AdminAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/memberManagement")) { //
			Action action = new MemberManagementAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/LoginAdminProc")) { //
			Action action = new LoginAdminProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/registerAdmin")) {
			Action action = new AdminRegisterAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/registerAdminProc")) {
			Action action = new AdminRegisterProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifyAdmin")) {
			Action action = new AdminModifyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/modifyAdminProc")) {
			Action action = new AdminModifyProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemList")) {
			Action action = new ItemListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemDetail")) {
			Action action = new ItemDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemAdd")) {
			Action action = new ItemAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemAddProc")) {
			Action action = new ItemAddProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemModifyProc")) {
			Action action = new ItemModifyProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/logoutAdmin")) {
			Action action = new LogoutAdminAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemShow")) {
			Action action = new ItemShowAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemDel")) {
			Action action = new ItemDelAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/itemModifyView")) {
			Action action = new ItemModifyViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/fileUpAndDown")) {  //파일 업로드 및 다운로드 예제
			Action action = new FileExAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/fileUpload")) {  //파일 업로드 및 다운로드 예제
			Action action = new FileUploadAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardAdd")) { 
			Action action = new BoardAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardAddProc")) { 
			Action action = new BoardAddProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardList")) { 
			Action action = new BoardListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	else if (command.equals("/boardDetail")) {
			Action action = new BoardDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyView")) {
			Action action = new BoardModifyViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardModifyProc")) {
			Action action = new BoardModifyProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDel")) {
			Action action = new BoardDelAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/listAdmin")) { 
			Action action = new AdminListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/detailAdmin")) { 
			Action action = new AdminDetailAction();
			
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
