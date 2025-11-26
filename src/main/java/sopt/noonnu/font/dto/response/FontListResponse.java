package sopt.noonnu.font.dto.response;

import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.domain.FontMetadata;

import java.util.List;

public record FontListResponse(
        List<FontResponse> fonts
) {
    public static FontListResponse from(List<FontResponse> list) {
        return new FontListResponse(list);
    }

    public record FontResponse(
            Long id,
            String name,
            String producer,
            int thicknessNum,
            String phrase,
            boolean isLiked,
            boolean isCompared,
            FontMetadata fontMetadata
    ) {
        public static FontResponse of(Font font, boolean isLiked, boolean isCompared) {
            return new FontResponse(
                    font.getId(),
                    font.getName(),
                    font.getProducer(),
                    font.getThicknessNum(),
                    font.getPhrase(),
                    isLiked,
                    isCompared,
                    font.getFontMetadata()
            );
        }
    }
}
