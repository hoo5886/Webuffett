package buffett.weallarebuffett.controller;

import buffett.weallarebuffett.model.EmailAuth.EmailRequest;
import buffett.weallarebuffett.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping("login/mailConfirm")
    public String mailConfirm(@RequestBody EmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException {

        String authCode = emailService.sendEmail(emailRequest.getEmail());
        return authCode;
    }
}
