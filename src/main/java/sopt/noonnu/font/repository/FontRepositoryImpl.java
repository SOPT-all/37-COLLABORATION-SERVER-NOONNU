package sopt.noonnu.font.repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import java.util.List;

import sopt.noonnu.font.domain.*;
import sopt.noonnu.userfont.domain.QUserFonts;

@RequiredArgsConstructor
public class FontRepositoryImpl implements FontRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Font> findFontsByCondition(
            int thicknessNum,
            List<EFontPurpose> purposes,
            List<EFontShape> shapes,
            List<EFontMood> moods,
            List<EFontLicense> licenses,
            EFontSort sortType
    ) {
        QFont font = QFont.font;
        QUserFonts userFont = QUserFonts.userFonts;

        NumberExpression<Long> likeCount =
                userFont.isLiked.when(true).then(1L).otherwise(0L).sum();

        return queryFactory
                .selectFrom(font)
                .from(font)
                .leftJoin(userFont).on(userFont.font.eq(font))
                .where(
                        thicknessEq(thicknessNum, font),
                        purposesIn(purposes, font),
                        shapesIn(shapes, font),
                        moodsIn(moods, font),
                        licensesIn(licenses, font)
                )
                .groupBy(font.id)
                .orderBy(orderSpecifier(sortType, font, likeCount))
                .fetch();
    }

    private BooleanExpression thicknessEq(int thicknessNum, QFont font) {
        return font.thicknessNum.eq(thicknessNum);
    }

    private BooleanExpression purposesIn(List<EFontPurpose> purposes, QFont font) {
        if (purposes == null || purposes.isEmpty()) return null;
        return font.fontPurposes.any().in(purposes);
    }

    private BooleanExpression shapesIn(List<EFontShape> shapes, QFont font) {
        if (shapes == null || shapes.isEmpty()) return null;
        return font.fontShapes.any().in(shapes);
    }

    private BooleanExpression moodsIn(List<EFontMood> moods, QFont font) {
        if (moods == null || moods.isEmpty()) return null;
        return font.fontMoods.any().in(moods);
    }

    private BooleanExpression licensesIn(List<EFontLicense> licenses, QFont font) {
        if (licenses == null || licenses.isEmpty()) return null;
        return font.fontLicenses.any().in(licenses);
    }

    private OrderSpecifier<?> orderSpecifier(EFontSort sortType, QFont font, NumberExpression<Long> likeCount) {
        return switch (sortType) {
            case POPULAR -> likeCount.desc();
            case VIEW_COUNT -> font.viewCount.desc();
            case LATEST -> font.createdAt.desc();
            case NAME -> font.name.asc();
        };
    }
}
