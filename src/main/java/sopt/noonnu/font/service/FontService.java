package sopt.noonnu.font.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.dto.command.GetFontsQuery;
import sopt.noonnu.font.exception.FontErrorCode;
import sopt.noonnu.font.repository.FontRepository;
import sopt.noonnu.global.exception.BaseException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FontService {

    private final FontRepository fontRepository;

    @Transactional(readOnly = true)
    public Font getFont(Long fontId) {
        return fontRepository.findById(fontId)
                .orElseThrow(() -> BaseException.type(FontErrorCode.NOT_FOUND_FONT));
    }

    @Transactional(readOnly = true)
    public List<Font> getFonts(GetFontsQuery command) {

        return fontRepository.findFontsByCondition(
                command.purposes(),
                command.shapes(),
                command.moods(),
                command.licenses(),
                command.sortBy());
    }
}
