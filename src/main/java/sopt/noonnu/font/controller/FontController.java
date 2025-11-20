package sopt.noonnu.font.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.FontListResponse;
import sopt.noonnu.font.service.FontService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fonts")
public class FontController implements FontApi {

    private final FontService fontService;

    @GetMapping
    public FontListResponse getFonts(
            @RequestHeader("userId") Long userId,
            @RequestParam("sortBy") EFontSort sortBy,
            @RequestParam("thicknessNum") int thicknessNum,
            @RequestParam(value = "purpose", required = false) List<EFontPurpose> purposes,
            @RequestParam(value = "shape", required = false) List<EFontShape> shapes,
            @RequestParam(value = "mood", required = false) List<EFontMood> moods,
            @RequestParam(value = "license", required = false) List<EFontLicense> licenses
    ) {
        FontListResponse result = fontService.getFonts(
                userId,
                sortBy,
                thicknessNum,
                purposes,
                shapes,
                moods,
                licenses
        );

        return result;
    }
}
