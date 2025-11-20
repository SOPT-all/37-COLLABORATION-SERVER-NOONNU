package sopt.noonnu.font.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.response.FontResponse;
import sopt.noonnu.font.dto.response.PreviewFontResponse;
import sopt.noonnu.font.repository.FontRepository;
import sopt.noonnu.global.exception.BaseException;
import sopt.noonnu.global.exception.CommonErrorCode;
import sopt.noonnu.userfont.domain.UserFonts;
import sopt.noonnu.userfont.repository.UserFontRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FontService {

    private final FontRepository fontRepository;
    private final UserFontRepository userFontRepository;

    public List<FontResponse> getFonts(
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

        Map<Long, UserFonts> userFontMap = new HashMap<>();
        List<UserFonts> userFonts = userFontRepository.findByUserId(userId);
        for (UserFonts uf : userFonts) {
            userFontMap.put(uf.getFont().getId(), uf);
        }

        return fonts.stream()
                .map(font -> {
                    Long fontId = font.getId();
                    UserFonts userFont = userFontMap.get(fontId);

                    boolean isLiked = userFont != null && userFont.isLiked();
                    boolean isCompared = userFont != null && userFont.isCompared();

                    return FontResponse.of(font, isLiked, isCompared);
                })
                .toList();
    }

    public List<PreviewFontResponse> getComparedFontPreviews(Long userId) {
        return userFontRepository.findComparedFontPreviews(userId);
    }
}
