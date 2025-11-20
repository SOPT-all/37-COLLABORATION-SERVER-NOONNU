package sopt.noonnu.font.dto.response;

import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.domain.FontMetadata;

public record FontResponse(
        Long id,
        String name,
        String producer,
        int thicknessNum,
        String phrase,
        boolean isLiked,
        boolean isCompared,
        FontMetadataResponse fontMetadata
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
                FontMetadataResponse.from(font.getFontMetadata())
        );
    }

    public record FontMetadataResponse(
            String fontFamily,
            String fontSource,
            String fontWeight,
            String fontDisplay
    ) {
        public static FontMetadataResponse from(FontMetadata metadata) {
            return new FontMetadataResponse(
                    metadata.getFontFamily(),
                    metadata.getFontSource(),
                    metadata.getFontWeight(),
                    metadata.getFontDisplay()
            );
        }
    }
}
