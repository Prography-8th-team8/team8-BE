package prography.cakeke.server.store.adpter.out.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.QStore;

@Repository
@RequiredArgsConstructor
public class StorePersistenceAdapter implements LoadStorePort {

    private final StoreRepository storeRepository;
    private final JPAQueryFactory queryFactory;
    private final QStore store = QStore.store;

    @Override
    public List<DistrictCountResponse> getDistrictCount() {
        return queryFactory
                .select(Projections.fields(DistrictCountResponse.class,
                                           store.district,
                                           store.id.count().as("count")
                ))
                .from(store)
                .groupBy(store.district)
                .fetch();
    }

    @Override
    public List<StoreResponse> getList(List<District> district, Pageable pageable) {
        return queryFactory
                .select(Projections.fields(StoreResponse.class,
                                           store.id,
                                           store.createdAt,
                                           store.modifiedAt,
                                           store.name,
                                           store.city,
                                           store.district,
                                           store.location,
                                           store.latitude,
                                           store.longitude
                ))
                .from(store)
                .where(store.district.in(district))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
