package shop.beggar.beggar.item.action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shop.beggar.beggar.item.service.ItemService;
import shop.beggar.beggar.vo.ItemVo;
import shop.beggar.common.Action;
import shop.beggar.common.ActionForward;
import shop.beggar.common.LoginManager;



public class DummyAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
				
		for(int i = 1 ; i <= 250 ; i++) {
			ItemVo vo = new ItemVo();
			vo.setPrice(i);
			vo.setDiscount(20);
			vo.setStok(i);
			vo.setDel_fl(false);
			vo.setShow_fl(true);
			vo.setCategory(Integer.toString(3));
			vo.setCode(Integer.toString(i));
			vo.setColor(Integer.toString(i));
			vo.setItem_name(Integer.toString(i));
			vo.setItem_number(Integer.toString(i));
			vo.setItem_rating(Integer.toString(i));
			vo.setSize(Integer.toString(i));
			vo.setExplanation(Integer.toString(i));
			vo.setPreview(Integer.toString(i));
			vo.setFilepath(Integer.toString(i));
//			vo.setCntnt(Integer.toString(i));
			
			ItemService svc = new ItemService();
			if(!svc.dummyRegisterItemInfo(vo)) {
				response.setContentType("text/html;charset=UTF-8"); //답변줄 페이지 속성
				PrintWriter out = response.getWriter();
				out.println("<script>alert('게시글을 저장하는데 실패하였습니다.');"
						+ "history.back();</script>");
				out.close();
				return null;
				
			}
		}
				
		ActionForward forward = new ActionForward();
		forward.setPath("/item/mainItem.do");
		forward.setRedirect(true);
		return forward;
	}
}
