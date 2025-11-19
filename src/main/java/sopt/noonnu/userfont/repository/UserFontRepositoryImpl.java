package sopt.noonnu.userfont.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
}
