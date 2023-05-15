package prography.cakeke.server.store.adpter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreType;

@Getter
@NoArgsConstructor
public class StoreResponse {

    Long id;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

    String name;

    City city;

    District district;

    String location;

    Double latitude;

    Double longitude;

    List<StoreType> storeTypes;

    @Builder
    public StoreResponse(
            Store store
    ) {
        this.id = store.getId();
        this.createdAt = store.getCreatedAt();
        this.modifiedAt = store.getModifiedAt();
        this.name = store.getName();
        this.city = store.getCity();
        this.district = store.getDistrict();
        this.location = store.getLocation();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        this.storeTypes = List.of();
    }

    public StoreResponse updateStoreTag(List<StoreType> storeType) {
        this.storeTypes = storeType;
        return this;
    }
}
