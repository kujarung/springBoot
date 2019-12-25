package muscle.school.muman;

import muscle.school.muman.sigin.service.SignService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class SessionListener implements HttpSessionListener {

    @Autowired
    SignService signService;
    @Autowired
    HttpServletRequest request;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        int time = 60*60*2;
        se.getSession().setMaxInactiveInterval(time); //세션만료60분
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        session.invalidate();
        System.out.println("sessionTimeOut");
    }

}