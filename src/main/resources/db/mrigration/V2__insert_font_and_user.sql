-- V2__insert_font_and_user.sql

-- 1. 폰트 데이터 일괄 삽입
INSERT INTO fonts (name, producer, thickness_num, phrase, view_count, font_family, font_source, font_weight, font_display, created_at)
VALUES
    ('온글잎 박다현체', '온글잎', 1, '안녕 나의 작고 소중한 고양이', 0, 'OngleipParkDahyeon', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/2411-3@1.0/Ownglyph_ParkDaHyun.woff2'') format(''woff2'')', 'normal', 'swap', NOW()),
    ('건축조각체', 'BSW와 BOOMSPACE', 1, '건축물과 조각품 같은 건축조각체', 0, 'ArchitecturalSculpture', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2303@1.0/ARCHISCULPTURE_v200.woff2'') format(''woff2'')', 'normal', 'swap', NOW()),
    ('을지로체', '우아한 형제들', 1, '을지로에서 많이 보이는 글씨체', 0, 'Euljiro', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_twelve@1.0/BMEULJIRO.woff'') format(''woff'')', 'normal', 'swap', NOW()),
    ('부크크 명조', '(주)부크크', 2, '책을 사랑하는 사람들을 위한 폰트', 0, 'BookkMyungjo', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302@1.0/BookkMyungjo-Lt.woff2'') format(''woff2'')', '400;700', 'swap', NOW()),
    ('조선굴림체', '조선일보', 1, '조금 일하고 많이 벌고 싶다', 0, 'JoseonGulim', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff'') format(''woff'')', 'normal', 'swap', NOW()),
    ('김정철명조', '(주)정림건축', 3, '의식주 중 하나인 건축', 0, 'KimJeongcheolMyoungjo', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/KimjungchulMyungjo-Light.woff2'') format(''woff2'')', '300;400;700', 'swap', NOW()),
    ('나눔스퀘어', '네이버', 1, '너도 떠나보면 나를 알게 될거야', 0, 'NanumSquare', 'url(''https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css'')', 'normal', 'swap', NOW()),
    ('Orbit', 'Sooun Cho', 1, '우리는 우주먼지니까 대충 살아도 돼', 0, 'Orbit', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2310@1.0/Orbit-Regular.woff2'') format(''woff2'')', 'normal', 'swap', NOW()),
    ('피플퍼스트 투쟁체', '피플퍼스트성북센터', 1, '결국 남는건 사람이다', 0, 'PeopleFirstStruggle', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/2406-2@1.0/PeoplefirstFightingTTF.woff2'') format(''woff2'')', 'normal', 'swap', NOW()),
    ('프리텐다드', '길형진 (orioncactus)', 9, '세상에 이런 폰트가 나오다니 천재인듯', 0, 'Pretendard', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/pretendard@1.0/Pretendard-Thin.woff2'') format(''woff2'')', '100;200;300;400;500;600;700;800;900', 'swap', NOW()),
    ('어그로체', '(주)샌드박스네트워크', 3, '어그로가 필요한 순간엔 어그로체를', 0, 'Aggravo', 'url(''https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroL.woff'') format(''woff'')', '300;500;700', 'swap', NOW()),
    ('Wanted Sans', 'Wanted Sans', 7, '[구직중] 김눈누, Designer, +82-10-1234-5678', 0, 'wanted-sans', 'url(''https://cdn.jsdelivr.net/gh/wanteddev/wanted-sans@v1.0.1/packages/wanted-sans/fonts/webfonts/variable/split/WantedSansVariable.min.css'')', '400;500;600;700;800;900;950', 'swap', NOW());

-- 2. font_purposes 일괄 삽입
INSERT INTO font_purposes (font_id, purpose) VALUES
-- 1. 온글잎 박다현체
(1, 'DOCUMENT_DECORATIVE'), (1, 'VIDEO_SUBTITLE'), (1, 'DESIGN_POSTER'),
-- 2. 건축조각체
(2, 'DOCUMENT_DECORATIVE'), (2, 'DESIGN_CARD_NEWS'), (2, 'DESIGN_POSTER'),
-- 3. 을지로체
(3, 'DOCUMENT_TITLE'), (3, 'VIDEO_THUMBNAIL'), (3, 'DESIGN_PPT'), (3, 'DESIGN_CARD_NEWS'), (3, 'DESIGN_POSTER'),
-- 4. 부크크 명조
(4, 'DOCUMENT_BODY'), (4, 'VIDEO_SUBTITLE'), (4, 'DESIGN_PPT'), (4, 'DESIGN_CARD_NEWS'),
-- 5. 조선굴림체
(5, 'DOCUMENT_TITLE'), (5, 'DOCUMENT_BODY'), (5, 'VIDEO_SUBTITLE'), (5, 'DESIGN_PPT'), (5, 'DESIGN_CARD_NEWS'), (5, 'DESIGN_POSTER'),
-- 6. 김정철명조
(6, 'DOCUMENT_TITLE'), (6, 'DOCUMENT_DECORATIVE'), (6, 'VIDEO_THUMBNAIL'), (6, 'DESIGN_CARD_NEWS'), (6, 'DESIGN_POSTER'),
-- 7. 나눔스퀘어
(7, 'DOCUMENT_BODY'), (7, 'VIDEO_SUBTITLE'), (7, 'DESIGN_CARD_NEWS'), (7, 'DESIGN_PPT'),
-- 8. Orbit
(8, 'DOCUMENT_DECORATIVE'), (8, 'VIDEO_SUBTITLE'), (8, 'DESIGN_CARD_NEWS'),
-- 9. 피플퍼스트 투쟁체
(9, 'DOCUMENT_DECORATIVE'), (9, 'DESIGN_CARD_NEWS'), (9, 'DESIGN_POSTER'),
-- 10. 프리텐다드
(10, 'DOCUMENT_TITLE'), (10, 'DOCUMENT_BODY'), (10, 'VIDEO_SUBTITLE'), (10, 'DESIGN_CARD_NEWS'), (10, 'DESIGN_PPT'),
-- 11. 어그로체
(11, 'DOCUMENT_DECORATIVE'), (11, 'VIDEO_THUMBNAIL'), (11, 'DESIGN_CARD_NEWS'), (11, 'DESIGN_PPT'), (11, 'DESIGN_POSTER'),
-- 12. Wanted Sans
(12, 'DOCUMENT_TITLE'), (12, 'DOCUMENT_BODY'), (12, 'VIDEO_SUBTITLE'), (12, 'DESIGN_CARD_NEWS'), (12, 'DESIGN_PPT');

-- 3. font_shapes 일괄 삽입
INSERT INTO font_shapes (font_id, shape) VALUES
                                             (1, 'HANDWRITING'),
                                             (2, 'DECORATIVE'),
                                             (3, 'DECORATIVE'),
                                             (4, 'BATANG'),
                                             (5, 'GULIM'),
                                             (6, 'BATANG'),
                                             (7, 'DOTUM'),
                                             (8, 'BATANG'),
                                             (9, 'DECORATIVE'),
                                             (10, 'DOTUM'),
                                             (11, 'DECORATIVE'),
                                             (12, 'DOTUM');

-- 4. font_moods 일괄 삽입
INSERT INTO font_moods (font_id, mood) VALUES
-- 1. 온글잎 박다현체
(1, 'CUTE'), (1, 'ROUND'),
-- 2. 건축조각체
(2, 'CUTE'), (2, 'ANGULAR'), (2, 'UNIQUE'), (2, 'FREE'), (2, 'FANCY'),
-- 3. 을지로체
(3, 'RETRO'), (3, 'UNIQUE'),
-- 4. 부크크 명조
(4, 'EMOTIONAL'), (4, 'ANGULAR'), (4, 'CLASSIC'),
-- 5. 조선굴림체
(5, 'ROUND'), (5, 'SIMPLE'),
-- 6. 김정철명조
(6, 'ANGULAR'), (6, 'CLASSIC'), (6, 'EMOTIONAL'),
-- 7. 나눔스퀘어
(7, 'SIMPLE'), (7, 'CLASSIC'),
-- 8. Orbit
(8, 'ANGULAR'), (8, 'SIMPLE'), (8, 'EMOTIONAL'),
-- 9. 피플퍼스트 투쟁체
(9, 'UNIQUE'), (9, 'ANGULAR'), (9, 'CHEERFUL'),
-- 10. 프리텐다드
(10, 'ANGULAR'), (10, 'SIMPLE'),
-- 11. 어그로체
(11, 'STRONG'), (11, 'THICK'), (11, 'CHEERFUL'), (11, 'UNIQUE'),
-- 12. Wanted Sans
(12, 'ANGULAR'), (12, 'SIMPLE');

-- 5. font_licenses 일괄 삽입
INSERT INTO font_licenses (font_id, license) VALUES
-- 1. 온글잎 박다현체 (OFL 제외)
(1, 'PRINT'), (1, 'WEB'), (1, 'PACKAGE'), (1, 'VIDEO'), (1, 'EMBEDDING'), (1, 'BI_CI'),
-- 2. 건축조각체 (임베딩, OFL 제외)
(2, 'PRINT'), (2, 'WEB'), (2, 'PACKAGE'), (2, 'VIDEO'), (2, 'BI_CI'),
-- 3. 을지로체 (모두 허용)
(3, 'PRINT'), (3, 'WEB'), (3, 'PACKAGE'), (3, 'VIDEO'), (3, 'EMBEDDING'), (3, 'BI_CI'), (3, 'OFL'),
-- 4. 부크크 명조 (OFL 제외)
(4, 'PRINT'), (4, 'WEB'), (4, 'PACKAGE'), (4, 'VIDEO'), (4, 'EMBEDDING'), (4, 'BI_CI'),
-- 5. 조선굴림체 (OFL 제외)
(5, 'PRINT'), (5, 'WEB'), (5, 'PACKAGE'), (5, 'VIDEO'), (5, 'EMBEDDING'), (5, 'BI_CI'),
-- 6. 김정철명조 (OFL 제외)
(6, 'PRINT'), (6, 'WEB'), (6, 'PACKAGE'), (6, 'VIDEO'), (6, 'EMBEDDING'), (6, 'BI_CI'),
-- 7. 나눔스퀘어 (모두 허용)
(7, 'PRINT'), (7, 'WEB'), (7, 'PACKAGE'), (7, 'VIDEO'), (7, 'EMBEDDING'), (7, 'BI_CI'), (7, 'OFL'),
-- 8. Orbit (OFL 제외)
(8, 'PRINT'), (8, 'WEB'), (8, 'PACKAGE'), (8, 'VIDEO'), (8, 'EMBEDDING'), (8, 'BI_CI'),
-- 9. 피플퍼스트 투쟁체 (모두 허용)
(9, 'PRINT'), (9, 'WEB'), (9, 'PACKAGE'), (9, 'VIDEO'), (9, 'EMBEDDING'), (9, 'BI_CI'), (9, 'OFL'),
-- 10. 프리텐다드 (OFL 제외)
(10, 'PRINT'), (10, 'WEB'), (10, 'PACKAGE'), (10, 'VIDEO'), (10, 'EMBEDDING'), (10, 'BI_CI'),
-- 11. 어그로체 (모두 허용)
(11, 'PRINT'), (11, 'WEB'), (11, 'PACKAGE'), (11, 'VIDEO'), (11, 'EMBEDDING'), (11, 'BI_CI'), (11, 'OFL'),
-- 12. Wanted Sans (모두 허용)
(12, 'PRINT'), (12, 'WEB'), (12, 'PACKAGE'), (12, 'VIDEO'), (12, 'EMBEDDING'), (12, 'BI_CI'), (12, 'OFL');

-- 6. 테스트용 사용자 계정
INSERT INTO users (name, email, password, created_at, modified_at)
VALUES
    ('김눈누', 'test@noonnu.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', NOW(), NOW()),
    ('테스트유저', 'admin@noonnu.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', NOW(), NOW());