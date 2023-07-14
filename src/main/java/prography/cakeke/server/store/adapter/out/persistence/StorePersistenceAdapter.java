package prography.cakeke.server.store.adapter.out.persistence;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.group.GroupBy.list;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.out.DeleteStorePort;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.application.port.out.SaveStorePort;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.QStore;
import prography.cakeke.server.store.domain.QStoreAndTag;
import prography.cakeke.server.store.domain.QStoreTag;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

@Repository
@RequiredArgsConstructor
public class StorePersistenceAdapter implements LoadStorePort, SaveStorePort, DeleteStorePort {

    private final StoreRepository storeRepository;
    private final StoreTagRepository storeTagRepository;
    private final StoreAndTagRepository storeAndTagRepository;
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
    public List<Store> getList(
            List<District> district, List<StoreType> storeTypes, Pageable pageable,
            Double southwestLatitude, Double southwestLongitude,
            Double northeastLatitude, Double northeastLongitude
    ) {
        return queryFactory
                .selectFrom(store)
                .where(
                        storeDistrictIn(district),
                        storeLongitudeBetween(southwestLongitude, northeastLongitude),
                        storeLatitudeBetween(southwestLatitude, northeastLatitude),
                        store.id.in(
                                JPAExpressions
                                        .select(storeAndTag.store.id)
                                        .from(storeAndTag)
                                        .leftJoin(storeAndTag.storeTag, storeTag)
                                        .where(storeTypeIn(storeTypes))
                        )
                )
                .distinct()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(store.id.asc())
                .fetch();
    }

    @Override
    public Map<Long, StoreResponse> getStoreDetail(Long storeId) {
        return queryFactory
                .select(store)
                .from(store)
                .leftJoin(store.storeAndTags, storeAndTag)
                .leftJoin(storeAndTag.storeTag, storeTag)
                .where(store.id.eq(storeId))
                .transform(
                        groupBy(store.id).as(
                                Projections.constructor(StoreResponse.class,
                                                        store,
                                                        list(storeTag)
                                )
                        )
                );
    }

    @Override
    public Optional<Store> getByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public Optional<StoreTag> getStoreTagByStoreTag(StoreType storeType) {
        return storeTagRepository.findByStoreType(storeType);
    }

    @Override
    public StoreAndTag saveStoreAndTag(StoreAndTag storeAndTag) {
        return storeAndTagRepository.save(storeAndTag);
    }

    @Override
    public List<StoreTag> getStoreTagByStoreId(Long storeId) {
        return queryFactory
                .select(storeTag)
                .from(storeAndTag)
                .leftJoin(storeAndTag.storeTag, storeTag)
                .where(storeAndTag.store.id.eq(storeId))
                .fetch();
    }

    @Override
    public void deleteStoreAndTagByStoreId(Long storeId) {
        storeAndTagRepository.deleteByStoreId(storeId);
    }

    private BooleanExpression storeDistrictIn(List<District> district) {
        return district != null ? store.district.in(district) : null;
    }

    private BooleanExpression storeTypeIn(List<StoreType> storeTypes) {
        return storeTypes != null ? storeTag.storeType.in(storeTypes) : null;
    }

    private BooleanExpression storeLongitudeBetween(Double southwestLongitude, Double northeastLongitude) {
        return southwestLongitude != null || northeastLongitude != null ?
               store.longitude.between(southwestLongitude, northeastLongitude) : null;
    }

    private BooleanExpression storeLatitudeBetween(Double southwestLatitude, Double northeastLatitude) {
        return southwestLatitude != null || northeastLatitude != null ?
               store.latitude.between(southwestLatitude, northeastLatitude) : null;
    }
}