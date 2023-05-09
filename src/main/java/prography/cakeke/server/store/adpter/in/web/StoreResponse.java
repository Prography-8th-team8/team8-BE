package prography.cakeke.server.store.adpter.in.web;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreType;

@Getter
@NoArgsConstructor
public class StoreResponse {

    Long id;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

    String name;

    String city;

    String district;

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
        this.city = store.getCity().toString();
        this.district = store.getDistrict().toString();
        this.location = store.getLocation();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        // todo: storeTypes 관련 메소드 추가 예정
    }
}
