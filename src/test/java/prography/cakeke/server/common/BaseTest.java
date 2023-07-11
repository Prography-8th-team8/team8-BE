package prography.cakeke.server.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import prography.cakeke.server.store.application.service.StoreService;
import prography.cakeke.server.store.domain.City;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

@SpringBootTest
public class BaseTest {
//    @Autowired
//    public MockMvc mockMvc;

//    @Value("${api.naver.search.localpath}")
//    public String testLocalPath;
//
//    @Value("${api.naver.search.clientID}")
//    public String testClientID;
//
//    @Value("${api.naver.search.clientSecretKey}")
//    public String testClientSecretKey;

    protected final City testCity = City.SEOUL;
    protected final District testDistrict = District.GANGNAM;
    protected final Double testLatitude = 37.5085138;
    protected final String testLocation = "서울 강남구 테헤란로 1";
    protected final Double testLongitude = 127.0340836;
    protected final String testName = "케이크크";
    protected final String testShareLink = "https://naver.me/cakk";
    protected final StoreType testStoreType = StoreType.CHARACTER;
    @Autowired
    protected StoreService storeService;

    public Store buildStore() {
        return Store.builder()
                    .city(testCity)
                    .district(testDistrict)
                    .latitude(testLatitude)
                    .location(testLocation)
                    .longitude(testLongitude)
                    .name(testName)
                    .shareLink(testShareLink)
                    .build();
    }

    public StoreTag buildStoreTag() {
        return new StoreTag(testStoreType);
    }

    public StoreAndTag buildStoreAndTag(Store store, StoreTag storeTag) {
        return StoreAndTag.builder()
                          .storeTag(storeTag)
                          .store(store)
                          .build();
    }

}