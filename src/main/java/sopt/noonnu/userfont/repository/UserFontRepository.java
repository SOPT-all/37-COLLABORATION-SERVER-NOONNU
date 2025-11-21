package sopt.noonnu.userfont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.noonnu.userfont.domain.UserFonts;

import java.util.List;
import java.util.Optional;

public interface UserFontRepository extends JpaRepository<UserFonts, Long> {
    List<UserFonts> findByUserId(Long userId);

    List<UserFonts> findByUserIdAndIsComparedTrue(Long userId);

    Optional<UserFonts> findByUserIdAndFontId(Long userId, Long fontId);

    List<UserFonts> findByUserIdAndIsLikedTrue(Long userId);
}
