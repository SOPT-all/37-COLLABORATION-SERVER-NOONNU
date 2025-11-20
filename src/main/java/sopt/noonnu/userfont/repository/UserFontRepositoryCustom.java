package sopt.noonnu.userfont.repository;

import sopt.noonnu.userfont.domain.UserFonts;

import java.util.List;

public interface UserFontRepositoryCustom {
    List<UserFonts> findByUserId(Long userId);
}
