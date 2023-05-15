package prography.cakeke.server.store.adpter.out.persistence;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

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
import prography.cakeke.server.store.domain.QStoreAndTag;
import prography.cakeke.server.store.domain.QStoreTag;

@Repository
@RequiredArgsConstructor
public class StorePersistenceAdapter implements LoadStorePort {

    private final StoreRepository storeRepository;
    private final JPAQueryFactory queryFactory;
    private final QStore store = QStore.store;
    private final QStoreAndTag storeAndTag = QStoreAndTag.storeAndTag;
    private final QStoreTag storeTag = QStoreTag.storeTag;

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
                .selectFrom(store)
                .leftJoin(store.storeAndTagList, storeAndTag)
                .leftJoin(storeAndTag.storeTag, storeTag)
                .where(
                        store.district.in(district)
                )
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .transform(
                        groupBy(store.id).list(
                                Projections.constructor(StoreResponse.class,
                                                        store,
                                                        list(storeTag)
                                )
                        )
                );
    }
}
