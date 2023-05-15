package prography.cakeke.server.store.application.port.out;

import java.util.List;

import org.springframework.data.domain.Pageable;

import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.domain.District;

public interface LoadStorePort {
    List<DistrictCountResponse> getDistrictCount();

    List<StoreResponse> getList(List<District> district, Pageable pageable);
}
