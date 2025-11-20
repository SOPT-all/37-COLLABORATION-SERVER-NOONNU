package sopt.noonnu.font.service;

import sopt.noonnu.font.domain.*;

import java.util.List;

public record GetFontsCommand(
        Long userId,
        EFontSort sortBy,
        Integer thicknessNum,
        List<EFontPurpose> purposes,
        List<EFontShape> shapes,
        List<EFontMood> moods,
        List<EFontLicense> licenses
) {
    public static GetFontsCommand of(
            Long userId,
            EFontSort sortBy,
            Integer thicknessNum,
            List<EFontPurpose> purposes,
            List<EFontShape> shapes,
            List<EFontMood> moods,
            List<EFontLicense> licenses
    ) {
        return new GetFontsCommand(
                userId,
                sortBy,
                thicknessNum,
                purposes,
                shapes,
                moods,
                licenses
        );
    }
}
