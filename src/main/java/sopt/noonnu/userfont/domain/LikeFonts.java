package sopt.noonnu.userfont.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.global.base.BaseCreatedEntity;
import sopt.noonnu.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "compare_fonts", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "font_id"}))
public class LikeFonts extends BaseCreatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "font_id", nullable = false)
    private Font font;

    private LikeFonts(User user, Font font){
        this.user = user;
        this.font = font;
    }
}