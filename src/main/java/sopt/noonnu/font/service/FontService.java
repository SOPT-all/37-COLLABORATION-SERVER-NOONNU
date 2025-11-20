package sopt.noonnu.font.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.FontListResponse;
import sopt.noonnu.font.repository.FontRepository;
import sopt.noonnu.global.exception.BaseException;
import sopt.noonnu.global.exception.CommonErrorCode;
import sopt.noonnu.userfont.domain.UserFonts;
import sopt.noonnu.userfont.service.UserFontService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FontService {

    private final FontRepository fontRepository;
    private final UserFontService userFontService;

    public FontListResponse getFonts(
            Long userId,
            EFontSort sortBy,
            Integer thicknessNum,
            List<EFontPurpose> purposes,
            List<EFontShape> shapes,
            List<EFontMood> moods,
            List<EFontLicense> licenses
    ) {
        if (thicknessNum != null && (thicknessNum < 1 || thicknessNum > 9)) {
            throw new BaseException(CommonErrorCode.VALIDATION_ERROR);
        }

        List<Font> fonts = fontRepository.findFontsByCondition(
                thicknessNum,
                purposes,
                shapes,
                moods,
                licenses,
                sortBy
        );

        Map<Long, UserFonts> userFontMap = userFontService.getUserFontMapByUserId(userId);

        List<FontListResponse.FontResponse> fontResponses = fonts.stream()
                .map(font -> {
                    Long fontId = font.getId();
                    UserFonts userFont = userFontMap.get(fontId);

                    boolean isLiked = userFont != null && userFont.isLiked();
                    boolean isCompared = userFont != null && userFont.isCompared();

                    return FontListResponse.FontResponse.of(font, isLiked, isCompared);
                })
                .toList();

        return FontListResponse.from(fontResponses);
    }
}
