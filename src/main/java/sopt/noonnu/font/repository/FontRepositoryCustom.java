package sopt.noonnu.font.repository;

import sopt.noonnu.font.domain.*;

import java.util.List;

public interface FontRepositoryCustom {
    List<Font> findFontsByCondition(
            int thicknessNum,
            List<EFontPurpose> purposes,
            List<EFontShape> shapes,
            List<EFontMood> moods,
            List<EFontLicense> licenses,
            EFontSort sortType
    );
}
