package prography.cakeke.server.store.domain;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.domain.Core;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Core {

    @BatchSize(size = 10)
    @OneToMany(mappedBy = "store")
    private final List<StoreAndTag> storeAndTags = new ArrayList<>();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shareLink;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private City city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private District district;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = true)
    private String thumbnail;

    @BatchSize(size = 100)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> imageUrls;

    @Builder
    public Store(
            String name,
            City city,
            District district,
            String location,
            String shareLink,
            Double latitude,
            Double longitude
    ) {
        this.name = name;
        this.shareLink = shareLink;
        this.city = city;
        this.district = district;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // 대표 이미지는 교체될 수 있습니다.
    public Store uploadThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
        return this;
    }

    // 케이크 이미지는 교체되지 않고 추가됩니다.
    public Store uploadImageUrls(List<String> imageUrls) {
        this.imageUrls.addAll(imageUrls);
        return this;
    }

    // 케이크 카테고리는 교체될 수 있습니다.
    public Store updateCategory(List<StoreAndTag> storeAndTags) {
        this.storeAndTags.clear();
        this.storeAndTags.addAll(storeAndTags);
        return this;
    }

}