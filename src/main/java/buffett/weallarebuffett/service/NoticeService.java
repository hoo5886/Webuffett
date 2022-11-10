package buffett.weallarebuffett.service;


import buffett.weallarebuffett.model.Notice;
import buffett.weallarebuffett.model.NoticeEntity;
import buffett.weallarebuffett.repository.NoticeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public long post(Notice notice) {

        return noticeRepository.save(notice.toEntity()).getId();
    }

    public List<NoticeEntity> list() {

        return noticeRepository.findAll();
    }
}
