package prography.cakeke.server.common;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

@SpringBootTest
@Transactional
@Rollback
public class BaseMock {
    protected final City testCity = City.SEOUL;
    protected final District testDistrict = District.GANGNAM;
    protected final Double testLatitude = 37.5085138;
    protected final String testLocation = "서울 강남구 테헤란로 1";
    protected final Double testLongitude = 127.0340836;
    protected final String testName = "케이크크";
    protected final String testShareLink = "https://naver.me/cakk";
    protected final StoreType testStoreType = StoreType.CHARACTER;

    protected final String testNaverStoreName = "끌레르 봉봉";
    protected final String testNaverStoreLink = "http://pf.kakao.com/_busxnC";

    protected Store buildStore(String storeName) {
        return Store.builder()
                    .city(testCity)
                    .district(testDistrict)
                    .latitude(testLatitude)
                    .location(testLocation)
                    .longitude(testLongitude)
                    .name(storeName)
                    .shareLink(testShareLink)
                    .build();
    }

    protected StoreTag buildStoreTag() {
        return new StoreTag(testStoreType);
    }

    protected StoreAndTag buildStoreAndTag(Store store, StoreTag storeTag) {
        return StoreAndTag.builder()
                          .storeTag(storeTag)
                          .store(store)
                          .build();
    }

}