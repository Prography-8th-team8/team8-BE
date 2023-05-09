package prography.cakeke.server.store.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.Core;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreAndTag extends Core {

    @ManyToOne(targetEntity = StoreTag.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_tag_id")
    private StoreTag storeTag;

    @ManyToOne(targetEntity = Store.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
