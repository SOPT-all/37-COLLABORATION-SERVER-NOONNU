package sopt.noonnu.userfont.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.noonnu.font.domain.Font;
import sopt.noonnu.user.domain.User;
import sopt.noonnu.userfont.domain.UserFonts;
import sopt.noonnu.userfont.dto.response.UserFontResponse;
import sopt.noonnu.userfont.repository.UserFontRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserFontService {

    private final UserFontRepository userFontRepository;

    @Transactional
    public void updateLikeFont(Font font, User user, boolean isLiked) {
        updateFontFlag(font, user, isLiked, UserFonts::updateIsLiked);
    }

    @Transactional
    public void updateCompareFont(Font font, User user, boolean isLiked) {
        updateFontFlag(font, user, isLiked, UserFonts::updateIsCompared);
    }

    @Transactional(readOnly = true)
    public Map<Long, UserFonts> getUserFontMapByUserId(Long userId) {
        List<UserFonts> userFonts = userFontRepository.findByUserId(userId);
        return userFonts.stream()
                .collect(Collectors.toMap(
                        uf -> uf.getFont().getId(),
                        uf -> uf
                ));
    }

    @Transactional(readOnly = true)
    public List<Font> getComparedFontPreviews(Long userId) {
        List<UserFonts> userFonts = userFontRepository.findByUserIdAndIsComparedTrue(userId);

        return userFonts.stream()
                .map(UserFonts::getFont)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserFontResponse getLikedFont(Long userId) {
        List<UserFontResponse.Item> items = userFontRepository.findByUserIdAndIsLikedTrue(userId)
                .stream()
                .map(UserFontResponse.Item::from)
                .toList();

        return UserFontResponse.from(items);
    }

    @Transactional(readOnly = true)
    public UserFontResponse getComparedFont(Long userId) {
        List<UserFontResponse.Item> items = userFontRepository.findByUserIdAndIsComparedTrue(userId)
                .stream()
                .map(UserFontResponse.Item::from)
                .toList();

        return UserFontResponse.from(items);
    }


    private UserFonts createUserFont(
            User user,
            Font font,
            BiConsumer<UserFonts, Boolean> flagUpdater
    ) {
        UserFonts userFont = UserFonts.create(user, font, false, false);
        flagUpdater.accept(userFont, true);
        return userFont;
    }

    private void updateFontFlag(
            Font font,
            User user,
            boolean isTrue,
            BiConsumer<UserFonts, Boolean> flagUpdater
    ) {

        Optional<UserFonts> userFontOpt = userFontRepository
                .findByUserIdAndFontId(user.getId(), font.getId());

        if (userFontOpt.isPresent()) {
            UserFonts userFont = userFontOpt.get();
            flagUpdater.accept(userFont, isTrue);

            if (!userFont.isLiked() && !userFont.isCompared()) {
                userFontRepository.delete(userFont);
            }
        } else {
            if (isTrue) {
                UserFonts newUserFont = createUserFont(user, font, flagUpdater);
                userFontRepository.save(newUserFont);
            }
        }
    }
}
