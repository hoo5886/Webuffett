package buffett.weallarebuffett.service;


import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.model.Notice;
import buffett.weallarebuffett.model.NoticeEntity;
import buffett.weallarebuffett.repository.MemberRepository;
import buffett.weallarebuffett.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public long post(Long memberId, Notice notice) {
        MemberEntity member = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("회원이 없슴다"));
        NoticeEntity noticeEntity = notice.toEntity();

        noticeEntity.setMember(member);

        return noticeRepository.save(noticeEntity).getId();
    }

    public List<NoticeEntity> list() {

        return noticeRepository.findAll();
    }
}
