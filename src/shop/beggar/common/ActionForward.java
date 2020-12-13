package shop.beggar.common;

public class ActionForward {
	// 서블릿에서 요청 어리 후 포워딩 될 뷰 페이지 url
	private String path;
	// 포워딩 팡식( true:redirect, false:dispatch )
	// redirect : 주소가 바뀌어 requese가 바뀜
	// dispatch : 주소가 바뀌지 않는다.
	private boolean redirect;
	
	public ActionForward() {}
	
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.redirect = redirect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}
	
	
}
