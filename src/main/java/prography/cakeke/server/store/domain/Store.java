package prography.cakeke.server.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.Core;
import prography.cakeke.server.store.adpter.in.web.StoreResponse;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends Core {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private City city;

    @Column(nullable = false)
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