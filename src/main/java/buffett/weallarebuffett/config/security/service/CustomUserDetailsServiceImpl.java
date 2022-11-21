package buffett.weallarebuffett.config.security.service;

import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        MemberEntity memberEntity = this.memberRepository.findByEmail(email)
            .orElseThrow(
                () -> new UsernameNotFoundException("couldn't find user email -> " + email));

        return memberEntity;
    }
}
