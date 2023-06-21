package prography.cakeke.server.store.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.StoreType;

@Getter
@AllArgsConstructor
public class StoreDetailResponse {
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

    List<String> images;

    List<StoreType> storeTypes;

    String link; // 가게 URL

    String description; // 가게 설명

    String phoneNumber; // 가게 번호

    String address; // 도로명 주소

    @Builder
    public StoreDetailResponse(
            StoreResponse storeResponse, StoreNaverLocalSearchApiResponse storeNaverLocalSearchApiResponse
    ) {
        this.id = storeResponse.getId();
        this.createdAt = storeResponse.getCreatedAt();
        this.modifiedAt = storeResponse.getModifiedAt();
        this.name = storeResponse.getName();
        this.shareLink = storeResponse.getShareLink();
        this.city = storeResponse.getCity();
        this.district = storeResponse.getDistrict();
        this.location = storeResponse.getLocation();
        this.latitude = storeResponse.getLatitude();
        this.longitude = storeResponse.getLongitude();
        this.storeTypes = storeResponse.getStoreTypes();
        this.thumbnail = storeResponse.getThumbnail();
        this.images = storeResponse.getImages();
        this.link = storeNaverLocalSearchApiResponse.getLink();
        this.description = storeNaverLocalSearchApiResponse.getDescription();
        this.phoneNumber = storeNaverLocalSearchApiResponse.getPhoneNumber();
        this.address = storeNaverLocalSearchApiResponse.getAddress();
    }
}
