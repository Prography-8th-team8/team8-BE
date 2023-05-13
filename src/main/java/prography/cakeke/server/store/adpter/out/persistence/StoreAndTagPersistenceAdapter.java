package prography.cakeke.server.store.adpter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.application.port.out.LoadStoreAndTagPort;
import prography.cakeke.server.store.domain.StoreAndTag;

@Repository
@RequiredArgsConstructor
public class StoreAndTagPersistenceAdapter implements LoadStoreAndTagPort {

    private final StoreAndTagRepository storeAndTagRepository;

    @Override
    public List<StoreAndTag> getByStoreIds(List<Long> storeIds) {
        return storeAndTagRepository.findByStoreIdIn(storeIds);
    }
}
