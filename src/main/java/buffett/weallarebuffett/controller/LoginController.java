package buffett.weallarebuffett.controller;


import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String login(Model model,
        @RequestParam(value="error", required = false) String error,
        @RequestParam(value="exception", required = false) String exception,
        HttpServletRequest request) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/login";
    }

    @GetMapping("/loginSuccess")
    public String logicSuccess() {
        return "/notice/main";
    }
}
