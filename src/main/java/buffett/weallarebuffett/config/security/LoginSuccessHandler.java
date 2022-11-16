package buffett.weallarebuffett.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

//https://kimsaemjava.tistory.com/251 : 로그인 성공 했을 경우 처리할 일 - AuthenticationSuccessHandler
//https://hungseong.tistory.com/60 :로그인 성공 시 이전 페이지로 이동

@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
       /* HttpSession session = request.getSession();
        session.setAttribute("HI", authentication.getName() + "오늘도 시뻘건 하루를!");*/

        log.info("오늘도 시뻘건 하루를!");

        response.sendRedirect("/loginSuccess");
    }
}
