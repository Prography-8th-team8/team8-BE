package prography.cakeke.server.store.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;

public interface LoadStorePort {
    List<DistrictCountResponse> getDistrictCount();

    List<StoreResponse> getList(List<District> district, Pageable pageable);

    Map<Long, StoreResponse> getStoreDetail(Long storeId);

    Optional<Store> getByName(String name);
}
