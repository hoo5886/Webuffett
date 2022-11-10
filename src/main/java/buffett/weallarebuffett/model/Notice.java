package buffett.weallarebuffett.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notice {

    private String title;
    private String content;

    private LocalDateTime regDt;

    private long hit;

    public NoticeEntity toEntity() {

        NoticeEntity notice = NoticeEntity.builder()
            .title(title)
            .content(content)
            .regDt(LocalDateTime.now())
            .hit(0)
            .build();

        return notice;
    }
}