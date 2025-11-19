package sopt.noonnu.userfont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.noonnu.userfont.domain.UserFonts;

public interface UserFontRepository extends JpaRepository<UserFonts, Long>, UserFontRepositoryCustom {
}

