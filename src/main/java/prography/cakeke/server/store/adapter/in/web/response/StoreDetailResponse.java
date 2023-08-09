package prography.cakeke.server.store.adapter.in.web.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreTag;
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

    List<String> imageUrls;

    List<StoreType> storeTypes;

    String link; // 가게 URL

    String description; // 가게 설명

    String phoneNumber; // 가게 번호

    String address; // 도로명 주소

    @Builder
    public StoreDetailResponse(
            Store store, StoreNaverLocalSearchApiResponse storeNaverLocalSearchApiResponse
    ) {
        List<StoreTag> storeTagList = store.getStoreAndTags().stream().map(StoreAndTag::getStoreTag).toList();
        this.storeTypes = storeTagList != null ?
                          storeTagList.stream().map(StoreTag::getStoreType).collect(Collectors.toList())
                                               : List.of();
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
        this.link = storeNaverLocalSearchApiResponse.getLink();
        this.description = storeNaverLocalSearchApiResponse.getDescription();
        this.phoneNumber = storeNaverLocalSearchApiResponse.getPhoneNumber();
        this.address = storeNaverLocalSearchApiResponse.getAddress();
    }
}
