package prography.cakeke.server.store.application.port.out;

import prography.cakeke.server.store.adpter.in.web.response.StoreNaverLocalSearchApiResponse;

public interface LoadNaverSearchApiPort {
    StoreNaverLocalSearchApiResponse getNaverSearchApiResponse(String storeName);
}
