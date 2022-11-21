package buffett.weallarebuffett.service;

import buffett.weallarebuffett.model.MemberDto;
import buffett.weallarebuffett.model.MemberEntity;
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
    public long join(MemberDto memberDto) {

        String rawPass = memberDto.getPassword();
        String encPass = bCryptPasswordEncoder.encode(rawPass);
        memberDto.setPassword(encPass);

        MemberEntity memberEntity = memberDto.toEntity();

        return memberRepository.save(memberEntity).getId();
    }

}
