package prography.cakeke.server.store.adapter.in.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FeedImageResponse {

    Long storeId;

    String imageUrl;

    @Builder
    public FeedImageResponse(Long storeId, String imageUrl) {
        this.storeId = storeId;
        this.imageUrl = imageUrl;
    }
}
