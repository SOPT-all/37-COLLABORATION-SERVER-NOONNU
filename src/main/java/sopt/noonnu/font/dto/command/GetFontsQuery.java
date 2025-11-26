package sopt.noonnu.font.dto.command;

import sopt.noonnu.font.domain.*;

import java.util.List;

public record GetFontsQuery(
        Long userId,
        EFontSort sortBy,
        List<EFontPurpose> purposes,
        List<EFontShape> shapes,
        List<EFontMood> moods,
        List<EFontLicense> licenses
) {
    public static GetFontsQuery of(
            Long userId,
            EFontSort sortBy,
            List<EFontPurpose> purposes,
            List<EFontShape> shapes,
            List<EFontMood> moods,
            List<EFontLicense> licenses
    ) {
        return new GetFontsQuery(
                userId,
                sortBy,
                purposes,
                shapes,
                moods,
                licenses
        );
    }
}
