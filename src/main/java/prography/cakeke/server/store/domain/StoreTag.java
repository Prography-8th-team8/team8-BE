package prography.cakeke.server.store.domain;

import com.querydsl.core.annotations.QueryProjection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.cakeke.server.common.domain.Core;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreTag extends Core {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreType storeType;

    @QueryProjection
    public StoreTag(StoreType storeType) {
        this.storeType = storeType;
    }

}
