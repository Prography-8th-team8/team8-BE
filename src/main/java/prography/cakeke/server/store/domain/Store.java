package prography.cakeke.server.store.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.Core;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Core {

    @OneToMany(mappedBy = "store")
    private final List<StoreAndTag> storeAndTagList = new ArrayList<>();

    @Column(nullable = false)
    private String name;

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

    @Builder
    public Store(
            String name,
            City city,
            District district,
            String location,
            Double latitude,
            Double longitude
    ) {
        this.name = name;
        this.city = city;
        this.district = district;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public StoreResponse toResponse() {
        return StoreResponse.builder()
                            .store(this)
                            .build();
    }
}