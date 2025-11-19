package sopt.noonnu.userfont.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.global.base.BaseCreatedEntity;
import sopt.noonnu.user.domain.User;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user_fonts", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "font_id"}))
public class UserFonts extends BaseCreatedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isLiked", nullable = false)
    private boolean isLiked;

    @Column(name = "isCompared", nullable = false)
    private boolean isCompared;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "font_id", nullable = false)
    private Font font;

    @Builder(access = AccessLevel.PRIVATE)
    private UserFonts(final User user,
                      final Font font,
                      final boolean isLiked,
                      final boolean isCompared) {
        this.user = user;
        this.font = font;
        this.isLiked = isLiked;
        this.isCompared = isCompared;
    }

}
