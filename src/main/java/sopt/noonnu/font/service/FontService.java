package sopt.noonnu.font.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.dto.command.GetFontsCommand;
import sopt.noonnu.font.exception.FontErrorCode;
import sopt.noonnu.font.repository.FontRepository;
import sopt.noonnu.global.exception.BaseException;
import sopt.noonnu.global.exception.CommonErrorCode;
import sopt.noonnu.userfont.service.UserFontService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FontService {

    private final FontRepository fontRepository;
    private final UserFontService userFontService;

    @Transactional(readOnly = true)
    public Font getFont(Long fontId) {
        return fontRepository.findById(fontId)
                .orElseThrow(() -> BaseException.type(FontErrorCode.NOT_FOUND_FONT));
    }

    @Transactional(readOnly = true)
    public List<Font> getFonts(GetFontsCommand command) {
        Integer thicknessNum = command.thicknessNum();

        if (thicknessNum != null && (thicknessNum < 1 || thicknessNum > 9)) {
            throw new BaseException(CommonErrorCode.VALIDATION_ERROR);
        }

        return fontRepository.findFontsByCondition(
                thicknessNum,
                command.purposes(),
                command.shapes(),
                command.moods(),
                command.licenses(),
                command.sortBy());
    }
}
