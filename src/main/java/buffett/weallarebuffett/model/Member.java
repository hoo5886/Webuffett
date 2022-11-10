package buffett.weallarebuffett.model;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @NotEmpty(message = "회원이름은 필수입니다.")
    private String username;

    @NotEmpty(message = "이메일은 필수입니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상, 16자 이하로 입력해주세요.")
    private String password;

    @NotEmpty(message = "전화번호는 필수입니다.")
    private String phone;

    private MemberCategory memberCate;

    private long following;
    private long follower;

    private LocalDateTime regDt;
    private Boolean auth;

    //Member -> MemberEntity로 변환 후 -> MemberRepository 전송
    public MemberEntity toEntity() {
        MemberEntity member = MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phone(phone)
                .memberCate(MemberCategory.NON_AUTH)
                .follower(0)
                .following(0)
                .regDt(LocalDateTime.now())
                .auth(false)
                .build();

        return member;
    }

    /* Q. Member와 MemberEntity를 구분해놓는 이유
    * A. 엔티티는 내부 tb와 직접적으로 매핑하기 위한 클래스이기 때문에
    * 엔티티 인스턴스를 서비스 코드 내부에서 데이터를 주고 받기 위한 용도로 쓰이거나
    * 이 과정에서 데이터를 변경, 삭제 등의 행동이 들어가게 되면 이 클래스의 원래 역할에서
    * 벗어나게 되는 것이다.
    * */
}
