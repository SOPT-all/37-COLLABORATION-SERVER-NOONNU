package sopt.noonnu.font.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import sopt.noonnu.font.domain.*;
import sopt.noonnu.font.dto.response.FontListResponse;
import sopt.noonnu.global.dto.CustomErrorResponse;

import java.util.List;

@Tag(name = "Font")
public interface FontApi {

    @Operation(
            summary = "무료 폰트 전체 조회",
            description = """
                    정렬 및 필터 조건에 따라 무료 폰트 목록을 조회합니다.  
                    
                    - 정렬 기준(sortBy): POPULAR, VIEW_COUNT, LATEST, NAME  
                    - 필터(purpose/shape/mood/license): 여러 값 전달 가능  
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.
                    ApiResponse(
                    responseCode = "200",
                    description = "무료 폰트 목록 조회 성공",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FontListResponse.class))
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.
                    ApiResponse(
                    responseCode = "400",
                    description = "잘못된 요청 파라미터",
                    content = @Content(
                            schema = @Schema(implementation = CustomErrorResponse.class)
                    )
            )
    })
    @GetMapping("/fonts")
    FontListResponse getFonts(
            @Parameter(
                    in = ParameterIn.HEADER,
                    name = "userId",
                    description = "사용자 ID",
                    required = true
            )
            @RequestHeader("userId") Long userId,

            @Parameter(
                    description = "정렬 기준 (인기순, 조회순, 최신순, 이름순)",
                    required = true
            )
            @RequestParam("sortBy") EFontSort sortBy,

            @Parameter(
                    description = "폰트 용도 필터",
                    required = false
            )
            @RequestParam(value = "purpose", required = false) List<EFontPurpose> purposes,

            @Parameter(
                    description = "폰트 형태 필터",
                    required = false
            )
            @RequestParam(value = "shape", required = false) List<EFontShape> shapes,

            @Parameter(
                    description = "폰트 분위기 필터",
                    required = false
            )
            @RequestParam(value = "mood", required = false) List<EFontMood> moods,

            @Parameter(
                    description = "폰트 허용 범위 필터",
                    required = false
            )
            @RequestParam(value = "license", required = false) List<EFontLicense> licenses
    );

}
