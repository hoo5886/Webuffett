package buffett.weallarebuffett.model;

import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private long id;

    @OneToMany(mappedBy = "member")
    private List<NoticeEntity> notices = new ArrayList<>();

    private String username;
    private String email;
    private String password;
    private String phone;

    private MemberCategory memberCate;

    private long following;
    private long follower;

    private LocalDateTime regDt;
    private Boolean auth;


    public MemberEntity(String username, String email, String password, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.memberCate = MemberCategory.NON_AUTH;
        this.follower = 0L;
        this.following = 0L;
        this.regDt = LocalDateTime.now();
        this.auth = false;
    }

    public MemberEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}