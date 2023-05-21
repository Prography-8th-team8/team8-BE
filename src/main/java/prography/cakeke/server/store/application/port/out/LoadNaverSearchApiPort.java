package prography.cakeke.server.store.application.port.out;

import prography.cakeke.server.store.adpter.in.web.response.StoreNaverSearchApiResponse;

public interface LoadNaverSearchApiPort {
    StoreNaverSearchApiResponse getNaverSearchApiResponse(String storeName);
}
