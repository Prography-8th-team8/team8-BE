package prography.cakeke.server.store.application.port.out;

import prography.cakeke.server.store.domain.StoreAndTag;

public interface SaveStorePort {
    StoreAndTag saveStoreAndTag(StoreAndTag storeAndTag);
}
