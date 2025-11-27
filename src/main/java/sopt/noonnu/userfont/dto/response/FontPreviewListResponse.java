package sopt.noonnu.userfont.dto.response;

import sopt.noonnu.font.domain.Font;
import sopt.noonnu.font.domain.FontMetadata;

import java.util.List;

public record FontPreviewListResponse(
        List<Item> items
) {
    public static FontPreviewListResponse from(List<Font> fonts) {
        List<Item> items = fonts.stream()
                .map(f -> new Item(f.getId(), f.getName(), f.getFontMetadata()))
                .toList();
        return new FontPreviewListResponse(items);
    }

    public record Item(
            Long id,
            String name,
            FontMetadata fontMetadata
    ) {
    }
}
