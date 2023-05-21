package prography.cakeke.server.store.adpter.in.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class StoreDetailResponse extends StoreResponse{

    String link; // 가게 URL

    String description; // 가게 설명

    String phoneNumber; // 가게 번호

    String address; // 도로명 주소

    @Builder
    public StoreDetailResponse(
            StoreResponse storeResponse, StoreNaverSearchApiResponse storeNaverSearchApiResponse
    ) {
        this.id = storeResponse.getId();
        this.createdAt = storeResponse.getCreatedAt();
        this.modifiedAt = storeResponse.getModifiedAt();
        this.name = storeResponse.getName();
        this.city = storeResponse.getCity();
        this.district = storeResponse.getDistrict();
        this.latitude = storeResponse.getLatitude();
        this.longitude = storeResponse.getLongitude();
        this.storeTypes = storeResponse.getStoreTypes();
        this.link = storeNaverSearchApiResponse.getLink();
        this.description = storeNaverSearchApiResponse.getDescription();
        this.phoneNumber = storeNaverSearchApiResponse.getPhoneNumber();
        this.address = storeNaverSearchApiResponse.getAddress();
    }
}
