package prography.cakeke.server.store.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.application.port.out.LoadStoreAndTagPort;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.StoreAndTag;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService implements StoreUseCase {

    private final LoadStorePort loadStorePort;
    private final LoadStoreAndTagPort loadStoreAndTagPort;

    @Override
    public List<DistrictCountResponse> getCount() {
        return loadStorePort.getDistrictCount();
    }

    @Override
    public List<StoreResponse> getList(List<District> district, int page) {
        List<StoreResponse> responses = loadStorePort.getList(district, PageRequest.of(page - 1, 15));
        List<Long> ids = responses.stream()
                                  .map(StoreResponse::getId)
                                  .toList();

        List<StoreAndTag> storeAndTags = loadStoreAndTagPort.getByStoreIds(ids);
        return responses.stream()
                        .map(it -> it.updateStoreTag(
                                storeAndTags.stream()
                                            .filter(tag -> tag.getStore().getId().equals(it.getId()))
                                            .map(tag -> tag.getStoreTag().getStoreType())
                                            .collect(Collectors.toList())))
                        .collect(Collectors.toList());
    }
}
