package sopt.noonnu.userfont.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import sopt.noonnu.font.domain.QFont;
import sopt.noonnu.font.dto.response.PreviewFontResponse;
import sopt.noonnu.userfont.domain.QUserFonts;
import sopt.noonnu.userfont.domain.UserFonts;

import java.util.List;

@RequiredArgsConstructor
public class UserFontRepositoryImpl implements UserFontRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserFonts> findByUserId(Long userId) {
        QUserFonts userFont = QUserFonts.userFonts;

        return queryFactory
                .selectFrom(userFont)
                .where(userFont.user.id.eq(userId))
                .fetch();
    }

    @Override
    public List<PreviewFontResponse> findComparedFontPreviews(Long userId) {
        QUserFonts uf = QUserFonts.userFonts;
        QFont font = QFont.font;

        return queryFactory
                .select(
                        Projections.constructor(
                                PreviewFontResponse.class,
                                font.id,
                                font.name
                        )
                )
                .from(uf)
                .join(uf.font, font)
                .where(
                        uf.user.id.eq(userId),
                        uf.isCompared.isTrue()
                )
                .fetch();
    }
}
