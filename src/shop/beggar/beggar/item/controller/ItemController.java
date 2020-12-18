package shop.beggar.beggar.item.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.item.action.DetailAction;
import shop.beggar.beggar.item.action.DummyAction;
import shop.beggar.beggar.item.action.MainItemAction;
import shop.beggar.beggar.item.action.PurchaseAction;
import shop.beggar.beggar.item.action.ShopBasketAction;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;

@WebServlet("/item/*")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// URL을 찾아오는 경로에 대해 *.do를 가져오는 코드
		String requesetURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requesetURI.substring(contextPath.length()).replaceAll("/item", "");
		
		ActionForward forward = null;
		
		//화면 경로 설정
		if (command.equals("/mainItem.do")) {
			Action action = new MainItemAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/detail.do")) { //상세화면
			Action action = new DetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/dummy.do")) { //상세화면
			Action action = new DummyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/shopBasket")) { //상세화면
			Action action = new ShopBasketAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/purchase")) { //상세화면
			Action action = new PurchaseAction();
			
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
