package prography.cakeke.server.store.adapter.in.web.response;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Getter;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

@Getter
public class StoreTagResponse {

    Long storeId;

    List<StoreType> types;

    @Builder
    public StoreTagResponse(Long storeId, List<StoreTag> storeAndTag) {
        this.storeId = storeId;
        this.types = storeAndTag.stream().map(StoreTag::getStoreType).collect(Collectors.toList());
    }
}