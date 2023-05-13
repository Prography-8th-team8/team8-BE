package prography.cakeke.server.store.application.port.out;

import java.util.List;

import prography.cakeke.server.store.domain.StoreAndTag;

public interface LoadStoreAndTagPort {
    List<StoreAndTag> getByStoreIds(List<Long> storeIds);
}
