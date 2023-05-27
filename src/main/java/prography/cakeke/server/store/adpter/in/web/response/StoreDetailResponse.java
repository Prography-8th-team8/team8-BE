package prography.cakeke.server.store.adpter.in.web.response;

import java.util.List;

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

    List<StoreNaverBlogSearchApiResponse> blogPosts; // 네이버 블로그 목록

    @Builder
    public StoreDetailResponse(
            StoreResponse storeResponse, StoreNaverLocalSearchApiResponse storeNaverLocalSearchApiResponse, List<StoreNaverBlogSearchApiResponse> blogPosts
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
        this.link = storeNaverLocalSearchApiResponse.getLink();
        this.description = storeNaverLocalSearchApiResponse.getDescription();
        this.phoneNumber = storeNaverLocalSearchApiResponse.getPhoneNumber();
        this.address = storeNaverLocalSearchApiResponse.getAddress();
        this.blogPosts = blogPosts;
    }
}