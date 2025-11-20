package sopt.noonnu.font.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.noonnu.font.domain.Font;

public interface FontRepository extends JpaRepository<Font, Long>, FontRepositoryCustom {
}
