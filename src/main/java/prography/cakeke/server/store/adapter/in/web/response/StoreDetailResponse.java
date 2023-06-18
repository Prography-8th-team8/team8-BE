package prography.cakeke.server.store.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StoreDetailResponse extends StoreResponse {

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
        this.link = storeNaverLocalSearchApiResponse.getLink();
        this.description = storeNaverLocalSearchApiResponse.getDescription();
        this.phoneNumber = storeNaverLocalSearchApiResponse.getPhoneNumber();
        this.address = storeNaverLocalSearchApiResponse.getAddress();
    }
}
