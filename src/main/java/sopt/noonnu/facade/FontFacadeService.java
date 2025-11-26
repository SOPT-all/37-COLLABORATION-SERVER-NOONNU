package sopt.noonnu.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.dto.command.GetFontsQuery;
import sopt.noonnu.font.dto.response.FontListResponse;
import sopt.noonnu.font.dto.response.FontPreviewListResponse;
import sopt.noonnu.font.service.FontService;
import sopt.noonnu.user.domain.User;
import sopt.noonnu.user.service.UserService;
import sopt.noonnu.userfont.domain.UserFonts;
import sopt.noonnu.userfont.dto.command.UpdateFontFlagCommandDto;
import sopt.noonnu.userfont.dto.response.UserFontResponse;
import sopt.noonnu.userfont.service.UserFontService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FontFacadeService {
    private final UserService userService;
    private final FontService fontService;
    private final UserFontService userFontService;

    @Transactional
    public void updateLikeFont(UpdateFontFlagCommandDto command){
        Font font = fontService.getFont(command.fontId());
        User user = userService.getUser(command.userId());
        userFontService.updateLikeFont(font, user, command.isTrue());
    }


    @Transactional
    public void updateCompareFont(UpdateFontFlagCommandDto command){
        Font font = fontService.getFont(command.fontId());
        User user = userService.getUser(command.userId());
        userFontService.updateCompareFont(font, user, command.isTrue());
    }

    @Transactional(readOnly = true)
    public FontListResponse getFonts(GetFontsQuery command){
        List<Font> fonts = fontService.getFonts(command);

        Map<Long, UserFonts> userFontMap = userFontService.getUserFontMapByUserId(command.userId());

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

    @Transactional(readOnly = true)
    public FontPreviewListResponse getComparedFontPreviews(Long userId) {
        List<Font> fonts = userFontService.getComparedFontPreviews(userId);

        return FontPreviewListResponse.from(fonts);
    }

    @Transactional(readOnly = true)
    public UserFontResponse getLikedFont(Long userId) {
        return userFontService.getLikedFont(userId);
    }

    @Transactional(readOnly = true)
    public UserFontResponse getComparedFont(Long userId) {
        return userFontService.getComparedFont(userId);
    }
}
