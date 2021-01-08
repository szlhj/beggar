package shop.beggar.beggar.item.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.beggar.beggar.item.action.CartFormAction;
import shop.beggar.beggar.item.action.DetailAction;
import shop.beggar.beggar.item.action.DummyAction;
import shop.beggar.beggar.item.action.MainItemAction;
import shop.beggar.beggar.item.action.OrderAction;
import shop.beggar.beggar.item.action.OrderDeleteAction;
import shop.beggar.beggar.item.action.OrderInquiryAction;
import shop.beggar.beggar.item.action.OrderProcAction;
import shop.beggar.beggar.item.action.OrderViewAction;
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
		if (command.equals("/mainItem")) {
			Action action = new MainItemAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/detail")) { //상세화면
			Action action = new DetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/dummy")) { //상세화면
			Action action = new DummyAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/shopBasket")) { //장바구니 저장명령
			Action action = new ShopBasketAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/purchase")) { //주문상세화면
			Action action = new PurchaseAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/cartForm")) { //장바구니상세화면
			Action action = new CartFormAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/purchase")) { //장바구니상세화면
			Action action = new PurchaseAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
//-----------------------------------------------------------------------------------------
		} else if (command.equals("/order")) { //결제화면
			Action action = new OrderAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderProc")) { //결제로직
			Action action = new OrderProcAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderView")) { //결제후로직
			Action action = new OrderViewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderInquiry")) { //비회원 주문 조회 페이지
			Action action = new OrderInquiryAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/orderDelete")) { //주문조회 페이지 삭제로직
			Action action = new OrderDeleteAction();
			
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
