package prography.cakeke.server.StoreServiceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import prography.cakeke.server.common.BaseTest;
import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
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
    private final StoreRepository storeRepository;
    private final StoreTagRepository storeTagRepository;
    private final StoreAndTagRepository storeAndTagRepository;

    @Autowired
    StoreServiceTest(StoreRepository storeRepository,
                     StoreTagRepository storeTagRepository,
                     StoreAndTagRepository storeAndTagRepository) {
        this.storeRepository = storeRepository;
        this.storeTagRepository = storeTagRepository;
        this.storeAndTagRepository = storeAndTagRepository;
    }

    // 가게 및 태그 생성
    private void createStoreWithTag() {
        Store testStore = buildStore();
        StoreTag testStoreTag = buildStoreTag();
        StoreAndTag testStoreAndTag = buildStoreAndTag(testStore, testStoreTag);

        storeRepository.save(testStore);
        storeTagRepository.save(testStoreTag);
        storeAndTagRepository.save(testStoreAndTag);
    }

    @BeforeEach
    void setup() {
        createStoreWithTag();
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
        List<StoreResponse> testStoreResponseList = storeService.getList(testDistrictList, null, 1);

        assertThat(testStoreResponseList.get(0).getCity()).isEqualTo(testCity);
        assertThat(testStoreResponseList.get(0).getDistrict()).isEqualTo(testDistrict);
        assertThat(testStoreResponseList.get(0).getLatitude()).isEqualTo(testLatitude);
        assertThat(testStoreResponseList.get(0).getLocation()).isEqualTo(testLocation);
        assertThat(testStoreResponseList.get(0).getLongitude()).isEqualTo(testLongitude);
        assertThat(testStoreResponseList.get(0).getName()).isEqualTo(testName);
        assertThat(testStoreResponseList.get(0).getShareLink()).isEqualTo(testShareLink);
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

        assertThat(testStoreResponseList.get(0).getCity()).isEqualTo(testCity);
        assertThat(testStoreResponseList.get(0).getDistrict()).isEqualTo(testDistrict);
        assertThat(testStoreResponseList.get(0).getLatitude()).isEqualTo(testLatitude);
        assertThat(testStoreResponseList.get(0).getLocation()).isEqualTo(testLocation);
        assertThat(testStoreResponseList.get(0).getLongitude()).isEqualTo(testLongitude);
        assertThat(testStoreResponseList.get(0).getName()).isEqualTo(testName);
        assertThat(testStoreResponseList.get(0).getShareLink()).isEqualTo(testShareLink);
    }

}