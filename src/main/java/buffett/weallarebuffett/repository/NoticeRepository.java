package buffett.weallarebuffett.repository;

import buffett.weallarebuffett.model.NoticeEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    List<NoticeEntity> findAll();

    NoticeEntity findByTitle(String title);

}
