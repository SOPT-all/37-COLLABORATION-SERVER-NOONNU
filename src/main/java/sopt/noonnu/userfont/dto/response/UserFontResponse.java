package sopt.noonnu.userfont.dto.response;

import sopt.noonnu.font.domain.FontMetadata;
import sopt.noonnu.userfont.domain.UserFonts;

import java.util.List;

public record UserFontResponse(
        List<Item> items
) {
    public static UserFontResponse from(List<Item> items) {
        return new UserFontResponse(items);
    }

    public record Item(
            Long id,
            String name,
            String producer,
            int thicknessNum,
            String phrase,
            boolean isLiked,
            boolean isCompared,
            FontMetadata fontMetadata
    ) {

        public static Item from(UserFonts userFonts) {
            return new Item(
                    userFonts.getFont().getId(),
                    userFonts.getFont().getName(),
                    userFonts.getFont().getProducer(),
                    userFonts.getFont().getThicknessNum(),
                    userFonts.getFont().getPhrase(),
                    userFonts.isLiked(),
                    userFonts.isCompared(),
                    userFonts.getFont().getFontMetadata()
                    );
        }
    }
}

