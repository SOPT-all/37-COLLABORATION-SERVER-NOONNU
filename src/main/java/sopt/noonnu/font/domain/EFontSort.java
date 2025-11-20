package sopt.noonnu.font.domain;

import lombok.Getter;

@Getter
public enum EFontSort {

    POPULAR("인기순"),
    VIEW_COUNT("조회순"),
    LATEST("최신순"),
    NAME("이름순");

    private final String description;

    EFontSort(String description) {
        this.description = description;
    }
}
