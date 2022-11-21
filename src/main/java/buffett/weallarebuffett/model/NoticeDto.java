package buffett.weallarebuffett.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {

    private String title;
    private String content;

    private String name;

    private LocalDateTime regDt;

    private List<CommentDto> commentDtos = new ArrayList<>();

    private long hit;

    public NoticeEntity toEntity() {
        NoticeEntity noticeEntity = NoticeEntity.builder()
            .title(title)
            .content(content)
            .regDt(LocalDateTime.now())
            .hit(0)
            .build();

        return noticeEntity;
    }
}