package buffett.weallarebuffett.config.security.provider;

import buffett.weallarebuffett.model.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 어떤 애
@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 사용자가 입력한 username, password를 뽑아온다.
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        // 이메일 검증
        MemberEntity member = (MemberEntity) userDetailsService.loadUserByUsername(username);

        // 비밀번호 검증
        if(!passwordEncoder.matches(password, member.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(member, null, null);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
