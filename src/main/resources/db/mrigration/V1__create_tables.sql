-- V1__create_tables.sql

-- Users 테이블
CREATE TABLE users
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    created_at  DATETIME(6)  NOT NULL,
    modified_at DATETIME(6)  NULL,
    CONSTRAINT UK_users_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Fonts 테이블
CREATE TABLE fonts
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    producer      VARCHAR(255) NOT NULL,
    thickness_num INT          NOT NULL,
    phrase        VARCHAR(255) NOT NULL,
    font_family   VARCHAR(255) NOT NULL,
    font_display  VARCHAR(255) NOT NULL,
    font_source   VARCHAR(255) NOT NULL,
    font_weight   VARCHAR(255) NOT NULL,
    view_count    BIGINT       DEFAULT 0,
    created_at    DATETIME(6)  NOT NULL,
    INDEX idx_fonts_thickness (thickness_num),
    INDEX idx_fonts_producer (producer)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Font Licenses 테이블 (ElementCollection)
CREATE TABLE font_licenses
(
    font_id BIGINT NOT NULL,
    license ENUM('BI_CI', 'EMBEDDING', 'OFL', 'PACKAGE', 'PRINT', 'VIDEO', 'WEB') NOT NULL,
    PRIMARY KEY (font_id, license),
    CONSTRAINT FK_font_licenses_font FOREIGN KEY (font_id) REFERENCES fonts (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Font Moods 테이블 (ElementCollection)
CREATE TABLE font_moods
(
    font_id BIGINT NOT NULL,
    mood    ENUM('ANGULAR', 'CHEERFUL', 'CLASSIC', 'CUTE', 'EMOTIONAL', 'FANCY', 'FREE', 'RETRO', 'ROUND', 'SIMPLE', 'STRONG', 'THICK', 'UNIQUE') NOT NULL,
    PRIMARY KEY (font_id, mood),
    CONSTRAINT FK_font_moods_font FOREIGN KEY (font_id) REFERENCES fonts (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Font Purposes 테이블 (ElementCollection)
CREATE TABLE font_purposes
(
    font_id BIGINT NOT NULL,
    purpose ENUM('DESIGN_CARD_NEWS', 'DESIGN_POSTER', 'DESIGN_PPT', 'DOCUMENT_BODY', 'DOCUMENT_DECORATIVE', 'DOCUMENT_TITLE', 'VIDEO_SUBTITLE', 'VIDEO_THUMBNAIL') NOT NULL,
    PRIMARY KEY (font_id, purpose),
    CONSTRAINT FK_font_purposes_font FOREIGN KEY (font_id) REFERENCES fonts (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Font Shapes 테이블 (ElementCollection)
CREATE TABLE font_shapes
(
    font_id BIGINT NOT NULL,
    shape   ENUM('BATANG', 'DECORATIVE', 'DOTUM', 'GULIM', 'HANDWRITING') NOT NULL,
    PRIMARY KEY (font_id, shape),
    CONSTRAINT FK_font_shapes_font FOREIGN KEY (font_id) REFERENCES fonts (id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- User Fonts 테이블
CREATE TABLE user_fonts
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT      NOT NULL,
    font_id     BIGINT      NOT NULL,
    is_liked    BIT         NOT NULL DEFAULT 0,
    is_compared BIT         NOT NULL DEFAULT 0,
    created_at  DATETIME(6) NOT NULL,
    CONSTRAINT UK_user_fonts_user_font UNIQUE (user_id, font_id),
    CONSTRAINT FK_user_fonts_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT FK_user_fonts_font FOREIGN KEY (font_id) REFERENCES fonts (id) ON DELETE CASCADE,
    INDEX idx_user_fonts_user_liked (user_id, is_liked),
    INDEX idx_user_fonts_user_compared (user_id, is_compared)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;