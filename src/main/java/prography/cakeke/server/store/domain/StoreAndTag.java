package prography.cakeke.server.store.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.Core;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreAndTag extends Core {

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "store_tag")
    private StoreTag storeTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "store")
    private Store store;
}
