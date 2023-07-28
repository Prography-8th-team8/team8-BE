package prography.cakeke.server.store.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;
import prography.cakeke.server.store.domain.District;

@Getter
public class FeedImageResponse {

    Long storeId;

    String storeName;

    District district;

    String imageUrl;

    @Builder
    public FeedImageResponse(Long storeId, String storeName, District district, String imageUrl) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.district = district;
        this.imageUrl = imageUrl;
    }
}
