package sopt.noonnu.userfont.repository;

import sopt.noonnu.font.dto.response.PreviewFontResponse;
import sopt.noonnu.userfont.domain.UserFonts;

import java.util.List;

public interface UserFontRepositoryCustom {
    List<UserFonts> findByUserId(Long userId);

    List<PreviewFontResponse> findComparedFontPreviews(Long userId);
}
