package sopt.noonnu.userfont.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.dto.response.PreviewFontResponse;
import sopt.noonnu.userfont.domain.UserFonts;
import sopt.noonnu.userfont.repository.UserFontRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserFontService {

    private final UserFontRepository userFontRepository;

    public List<UserFonts> getUserFontsByUserId(Long userId) {
        return userFontRepository.findByUserId(userId);
    }

    public Map<Long, UserFonts> getUserFontMapByUserId(Long userId) {
        List<UserFonts> userFonts = userFontRepository.findByUserId(userId);
        Map<Long, UserFonts> map = new HashMap<>();
        for (UserFonts uf : userFonts) {
            map.put(uf.getFont().getId(), uf);
        }

        return map;
    }

    public List<PreviewFontResponse> getComparedFontPreviews(Long userId) {
        return userFontRepository.findComparedFontPreviews(userId);
    }
}
