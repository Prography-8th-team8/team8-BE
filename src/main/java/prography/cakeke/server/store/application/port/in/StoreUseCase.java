package prography.cakeke.server.store.application.port.in;

import java.util.List;

import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreDetailResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.domain.District;

public interface StoreUseCase {
    List<DistrictCountResponse> getCount();

    List<StoreResponse> getList(List<District> district, int page);

    StoreDetailResponse getStoreDetail(Long storeId);
}
