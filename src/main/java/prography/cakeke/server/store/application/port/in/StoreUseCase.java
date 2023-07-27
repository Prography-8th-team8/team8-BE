package prography.cakeke.server.store.application.port.in;

import java.util.List;

import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreNaverBlogSearchApiResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreNaverLocalSearchApiResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

public interface StoreUseCase {
    List<DistrictCountResponse> getCount();

    List<Store> getList(List<District> district, List<StoreType> storeTypes, int page);

    List<Store> reload(
            List<StoreType> storeTypes, int page,
            Double southwestLatitude, Double southwestLongitude,
            Double northeastLatitude, Double northeastLongitude
    );

    List<StoreTag> getStoreTypeByStoreId(Long storeId);

    StoreResponse getStore(Long storeId);

    StoreNaverLocalSearchApiResponse getNaverLocalApiByStore(StoreResponse storeResponse);

    List<StoreNaverBlogSearchApiResponse> getNaverBlogApiByStore(Long storeId, Integer blogNum);

    Store getByName(String name);
}
