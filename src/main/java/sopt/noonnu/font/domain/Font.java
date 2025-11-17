package sopt.noonnu.font.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import sopt.noonnu.global.base.BaseCreatedEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "fonts")
public class Font extends BaseCreatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "producer", nullable = false)
    private String producer;

    @Column(name = "thickness_num", nullable = false)
    private int thicknessNum;

    @Column(name = "phrase", nullable = false)
    private String phrase;

   @Column(name = "view_count")
    private long viewCount;

    @Embedded
    private FontMetadata fontMetadata;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "font_purposes", joinColumns = @JoinColumn(name ="font_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "purpose")
    @BatchSize(size = 100)
    private final Set<EFontPurpose> fontPurposes = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "font_shapes", joinColumns = @JoinColumn(name ="font_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "shape")
    @BatchSize(size = 100)
    private final Set<EFontShape> fontShapes = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "font_moods", joinColumns = @JoinColumn(name ="font_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "mood")
    @BatchSize(size = 100)
    private final Set<EFontMood> fontMoods = new HashSet<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "font_licenses", joinColumns = @JoinColumn(name ="font_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "license")
    @BatchSize(size = 100)
    private final Set<EFontLicense> fontLicenses = new HashSet<>();

}
