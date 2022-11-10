package buffett.weallarebuffett.model.EmailAuth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailRequest {
    @Email
    @NotBlank(message = "이메일(필수)")
    private String email;

}
