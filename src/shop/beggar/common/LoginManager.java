package shop.beggar.common;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Enumeration;
import java.util.Hashtable;

public class LoginManager implements HttpSessionBindingListener {
    private static Hashtable loginUsers = new Hashtable();

    private LoginManager() {
        super();
    }

    public static LoginManager getInstance() {
        return LoginManager.LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LoginManager INSTANCE = new LoginManager();
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        loginUsers.put(event.getSession(), event.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        loginUsers.remove(event.getSession());
    }

    public void removeSession(String id) {
        Enumeration e = loginUsers.keys();
        HttpSession session = null;
        while(e.hasMoreElements()) {
            session = (HttpSession) e.nextElement();
            if (loginUsers.get(session).equals(id)) {
                session.invalidate();
            }
        }
    }

    public boolean isLogin(String sessionId) {
        boolean isLogin = false;
        Enumeration e = loginUsers.keys();
        String key = "";
        while(e.hasMoreElements()) {
            key = (String) e.nextElement();
            if (sessionId.equals(key)) {
                isLogin = true;
            }
        }
        return isLogin;
    }

    public void setSession(HttpSession session, String id) {
        session.setAttribute(id, this);
    }

    public String getMemberId(HttpSession session) {
        return (String) loginUsers.get(session);
    }
}










