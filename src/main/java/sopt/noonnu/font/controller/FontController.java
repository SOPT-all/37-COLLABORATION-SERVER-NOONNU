package sopt.noonnu.font.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.response.FontResponse;
import sopt.noonnu.font.dto.response.PreviewFontResponse;
import sopt.noonnu.font.service.FontService;
import sopt.noonnu.global.dto.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FontController implements FontApi{

    private final FontService fontService;

    @GetMapping("/fonts")
    public ResponseEntity<ApiResponse<List<FontResponse>>> getFonts(
            @RequestHeader("userId") Long userId,
            @RequestParam("sortBy") EFontSort sortBy,
            @RequestParam("thicknessNum") int thicknessNum,
            @RequestParam(value = "purpose", required = false) List<EFontPurpose> purposes,
            @RequestParam(value = "shape", required = false) List<EFontShape> shapes,
            @RequestParam(value = "mood", required = false) List<EFontMood> moods,
            @RequestParam(value = "license", required = false) List<EFontLicense> licenses
    ) {
        List<FontResponse> result = fontService.getFonts(
                userId,
                sortBy,
                thicknessNum,
                purposes,
                shapes,
                moods,
                licenses
        );

        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/user/compared-fonts/preview")
    public ResponseEntity<ApiResponse<List<PreviewFontResponse>>> getComparedFontPreviews(
            @RequestHeader("userId") Long userId
    ) {
        List<PreviewFontResponse> result = fontService.getComparedFontPreviews(userId);

        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
