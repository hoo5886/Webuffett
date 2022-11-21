package buffett.weallarebuffett.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "notice")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String title;
    private String content;
    private LocalDateTime regDt;
    private long hit;

    @OneToMany(mappedBy = "notice")
    private List<CommentEntity> commentEntities = new ArrayList<>();

    public void setMember(MemberEntity member) {
        this.member = member;
    }

    public void upHit() {
        this.hit += 1;
    }
}
