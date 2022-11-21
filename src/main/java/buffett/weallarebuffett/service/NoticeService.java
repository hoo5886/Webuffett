package buffett.weallarebuffett.service;


import buffett.weallarebuffett.model.MemberEntity;
import buffett.weallarebuffett.model.NoticeDto;
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
    public long post(Long memberId, NoticeDto noticeDto) {
        MemberEntity memberEntity = memberRepository.findById(memberId)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        NoticeEntity noticeEntity = noticeDto.toEntity();

        //등록일 포매팅 yyyy-MM-dd HH:mm

        noticeEntity.setMember(memberEntity);

        return noticeRepository.save(noticeEntity).getId();
    }

    public List<NoticeEntity> list() {

        return noticeRepository.findAll();
    }

    @Transactional
    public long upHit(NoticeEntity noticeEntity) {

        noticeEntity.upHit();

        return noticeRepository.save(noticeEntity).getId();
    }
}
