package prography.cakeke.server.store.application.port.out;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import prography.cakeke.server.store.adapter.in.web.response.DistrictCountDTO;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

public interface LoadStorePort {
    List<DistrictCountDTO> getDistrictCount();

    List<Store> getList(
            List<District> district, List<StoreType> storeTypes, Pageable pageable,
            Double southwestLongitude, Double northeastLongitude,
            Double southwestLatitude, Double northeastLatitude
    );

    Map<Long, StoreResponse> getStore(Long storeId);

    Optional<Store> getByName(String name);

    Optional<StoreTag> getStoreTagByStoreTag(StoreType storeType);

    List<StoreTag> getStoreTagByStoreId(Long storeId);
}
