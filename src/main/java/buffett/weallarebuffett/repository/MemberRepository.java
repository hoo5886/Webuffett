package buffett.weallarebuffett.repository;

import buffett.weallarebuffett.model.MemberEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUsername(String name);

    Optional<MemberEntity> findByEmail(String email);
}
