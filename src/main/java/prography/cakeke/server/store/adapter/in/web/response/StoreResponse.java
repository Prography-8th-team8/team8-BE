package prography.cakeke.server.store.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

@Getter
@AllArgsConstructor
public class StoreResponse {

    Long id;

    LocalDateTime createdAt;

    LocalDateTime modifiedAt;

    String name;

    String shareLink;

    City city;

    District district;

    String location;

    Double latitude;

    Double longitude;

    String thumbnail;

    List<String> imageUrls;

    List<StoreType> storeTypes;

    public StoreResponse(Store store) {
        this.id = store.getId();
        this.createdAt = store.getCreatedAt();
        this.modifiedAt = store.getModifiedAt();
        this.name = store.getName();
        this.shareLink = store.getShareLink();
        this.city = store.getCity();
        this.district = store.getDistrict();
        this.location = store.getLocation();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        this.thumbnail = store.getThumbnail();
        this.imageUrls = store.getImageUrls();
        this.storeTypes = null;
    }

    @Builder
    @QueryProjection
    public StoreResponse(
            Store store, List<StoreTag> storeTag
    ) {
        this.id = store.getId();
        this.createdAt = store.getCreatedAt();
        this.modifiedAt = store.getModifiedAt();
        this.name = store.getName();
        this.shareLink = store.getShareLink();
        this.city = store.getCity();
        this.district = store.getDistrict();
        this.location = store.getLocation();
        this.latitude = store.getLatitude();
        this.longitude = store.getLongitude();
        this.thumbnail = store.getThumbnail();
        this.imageUrls = store.getImageUrls();
        this.storeTypes = storeTag != null ?
                          storeTag.stream().map(StoreTag::getStoreType).collect(Collectors.toList())
                                           : List.of();
    }
}

