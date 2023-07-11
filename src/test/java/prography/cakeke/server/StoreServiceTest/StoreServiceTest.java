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
import prography.cakeke.server.store.adapter.out.persistence.StoreRepository;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;

class StoreServiceTest extends BaseTest {
    private final StoreRepository storeRepository;

    @Autowired
    StoreServiceTest(StoreRepository storeRepository) {this.storeRepository = storeRepository;}

    public void createStore() {
        Store testStore = buildStore();
        storeRepository.save(testStore);
    }

    @BeforeEach
    void setup() {
        // 가게 생성
        createStore();
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
        List<StoreResponse> storeResponseList = storeService.getList(testDistrictList, null, 1);

        assertThat(storeResponseList).hasSize(1);

        assertThat(storeResponseList.get(0).getCity()).isEqualTo(testCity);
        assertThat(storeResponseList.get(0).getDistrict()).isEqualTo(testDistrict);
        assertThat(storeResponseList.get(0).getLatitude()).isEqualTo(testLatitude);
        assertThat(storeResponseList.get(0).getLocation()).isEqualTo(testLocation);
        assertThat(storeResponseList.get(0).getLongitude()).isEqualTo(testLongitude);
        assertThat(storeResponseList.get(0).getName()).isEqualTo(testName);
        assertThat(storeResponseList.get(0).getShareLink()).isEqualTo(testShareLink);
    }

}
