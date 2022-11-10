package buffett.weallarebuffett.service;

import buffett.weallarebuffett.model.Member;
import buffett.weallarebuffett.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public long join(Member member) {

        String rawPass = member.getPassword();
        String encPass = bCryptPasswordEncoder.encode(rawPass);
        member.setPassword(encPass);

        return memberRepository.save(member.toEntity()).getId();
    }

}
