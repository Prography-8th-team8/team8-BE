package prography.cakeke.server.store.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import prography.cakeke.server.common.BaseTest;
import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreBlogResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreDetailResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.adapter.out.persistence.StoreAndTagRepository;
import prography.cakeke.server.store.adapter.out.persistence.StoreRepository;
import prography.cakeke.server.store.adapter.out.persistence.StoreTagRepository;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreTag;
import prography.cakeke.server.store.domain.StoreType;

class StoreServiceTest extends BaseTest {
    @Autowired
    protected StoreService storeService;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreTagRepository storeTagRepository;
    @Autowired
    private StoreAndTagRepository storeAndTagRepository;

    // 가게 및 태그 생성
    private void createStoreWithTag() {
        Store testStore = buildStore(testName);
        StoreTag testStoreTag = buildStoreTag();
        StoreAndTag testStoreAndTag = buildStoreAndTag(testStore, testStoreTag);

        storeRepository.save(testStore);
        storeTagRepository.save(testStoreTag);
        storeAndTagRepository.save(testStoreAndTag);
    }

    private void createNaverStore() {
        Store testStore = buildStore(testNaverStoreName);
        storeRepository.save(testStore);
    }

    @BeforeEach
    void setup() {
        // 가게 생성
        createStoreWithTag();

        // 외부 테스트용 가게 생성
        createNaverStore();
    }

    @Test
    @DisplayName("각 구의 개수 반환(성공)")
    public void getCountTestSuccess() {
        List<DistrictCountResponse> districtCountResponseList = storeService.getCount();
        assertThat((long) districtCountResponseList.size()).isEqualTo(1L);
    }

    @Test
    @DisplayName("가게 리스트 반환(성공)")
    public void getStoreListTestSuccess() {
        List<District> testDistrictList = new ArrayList<>();
        testDistrictList.add(testDistrict);

        List<StoreType> testStoreTypeList = new ArrayList<>();
        testStoreTypeList.add(testStoreType);
        List<StoreResponse> testStoreResponseList = storeService.getList(testDistrictList, testStoreTypeList,
                                                                         1);

        assertThat(testStoreResponseList.get(0).getCity()).isEqualTo(testCity);
        assertThat(testStoreResponseList.get(0).getDistrict()).isEqualTo(testDistrict);
        assertThat(testStoreResponseList.get(0).getLatitude()).isEqualTo(testLatitude);
        assertThat(testStoreResponseList.get(0).getLocation()).isEqualTo(testLocation);
        assertThat(testStoreResponseList.get(0).getLongitude()).isEqualTo(testLongitude);
        assertThat(testStoreResponseList.get(0).getName()).isEqualTo(testName);
        assertThat(testStoreResponseList.get(0).getShareLink()).isEqualTo(testShareLink);
        assertThat(testStoreResponseList.get(0).getStoreTypes()).isEqualTo(testStoreTypeList);
    }

    @Test
    @DisplayName("좌표 내 케이크샵 조회(성공)")
    public void getStoreReloadTestSuccess() {
        List<StoreType> testStoreTypeList = new ArrayList<>();
        testStoreTypeList.add(testStoreType);
        final Double TEST_ALPHA = 0.3; // 테스트 범위 임계값

        List<StoreResponse> testStoreResponseList = storeService.reload(testStoreTypeList, 1,
                                                                        testLatitude - TEST_ALPHA,
                                                                        testLongitude - TEST_ALPHA,
                                                                        testLatitude + TEST_ALPHA,
                                                                        testLongitude + TEST_ALPHA);

        assertThat(testStoreResponseList).hasSize(1);

        assertThat(testStoreResponseList.get(0).getCity()).isEqualTo(testCity);
        assertThat(testStoreResponseList.get(0).getDistrict()).isEqualTo(testDistrict);
        assertThat(testStoreResponseList.get(0).getLatitude()).isEqualTo(testLatitude);
        assertThat(testStoreResponseList.get(0).getLocation()).isEqualTo(testLocation);
        assertThat(testStoreResponseList.get(0).getLongitude()).isEqualTo(testLongitude);
        assertThat(testStoreResponseList.get(0).getName()).isEqualTo(testName);
        assertThat(testStoreResponseList.get(0).getShareLink()).isEqualTo(testShareLink);
        assertThat(testStoreResponseList.get(0).getStoreTypes().get(0)).isEqualTo(testStoreType);
    }

    @Test
    @DisplayName("가게 상세정보 + 네이버 검색 테스트(성공)")
    public void getStoreDetailTestSuccess() {
        Long testStoreId = storeRepository.findByName(testNaverStoreName).get().getId();
        StoreDetailResponse testStoreDetailResponse = storeService.getStoreDetail(testStoreId);

        assertThat(testStoreDetailResponse.getAddress()).isEqualTo(testNaverStoreAddress);
    }

    @Test
    @DisplayName("가게 네이버 블로그 테스트(성공)")
    public void getStoreBlogTestSuccess() {
        Long testStoreId = storeRepository.findByName(testNaverStoreName).get().getId();
        StoreBlogResponse testStoreBlogResponse = storeService.getStoreBlog(testStoreId, 2);

        assertThat(testStoreBlogResponse.getBlogPosts()).hasSize(2);
    }

    @Test
    @DisplayName("가게 정보 반환 테스트(성공)")
    public void getStoreByNameTestSuccess() {
        Store testStore = storeService.getByName(testName);

        assertThat(testStore.getCity()).isEqualTo(testCity);
        assertThat(testStore.getDistrict()).isEqualTo(testDistrict);
        assertThat(testStore.getLatitude()).isEqualTo(testLatitude);
        assertThat(testStore.getLocation()).isEqualTo(testLocation);
        assertThat(testStore.getLongitude()).isEqualTo(testLongitude);
        assertThat(testStore.getName()).isEqualTo(testName);
        assertThat(testStore.getShareLink()).isEqualTo(testShareLink);
    }

}