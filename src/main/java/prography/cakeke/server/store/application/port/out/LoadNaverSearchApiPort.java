package prography.cakeke.server.store.application.port.out;

import java.util.List;

import prography.cakeke.server.store.adpter.in.web.response.StoreNaverBlogSearchApiResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreNaverLocalSearchApiResponse;

public interface LoadNaverSearchApiPort {
    StoreNaverLocalSearchApiResponse getNaverLocalSearchResponse(String storeName);

    List<StoreNaverBlogSearchApiResponse> getNaverBlogSearchResponse(String storeName);
}
