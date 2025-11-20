package sopt.noonnu.font.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.FontListResponse;
import sopt.noonnu.font.service.FontService;
import sopt.noonnu.font.service.GetFontsCommand;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fonts")
public class FontController implements FontApi {

    private final FontService fontService;

    @GetMapping
    public FontListResponse getFonts(
            @RequestHeader(value = "userId", defaultValue = "POPULAR") Long userId,
            @RequestParam("sortBy") EFontSort sortBy,
            @RequestParam(value = "thicknessNum", defaultValue = "1") int thicknessNum,
            @RequestParam(value = "purpose", required = false) List<EFontPurpose> purposes,
            @RequestParam(value = "shape", required = false) List<EFontShape> shapes,
            @RequestParam(value = "mood", required = false) List<EFontMood> moods,
            @RequestParam(value = "license", required = false) List<EFontLicense> licenses
    ) {
        GetFontsCommand command = GetFontsCommand.of(
                userId,
                sortBy,
                thicknessNum,
                purposes,
                shapes,
                moods,
                licenses
        );

        FontListResponse result = fontService.getFonts(command);

        return result;
    }
}
