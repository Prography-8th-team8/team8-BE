package prography.cakeke.server.store.application.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreDetailResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreNaverBlogSearchApiResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreNaverLocalSearchApiResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.application.port.out.LoadNaverSearchApiPort;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.domain.District;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService implements StoreUseCase {

    private final LoadNaverSearchApiPort loadNaverSearchApiPort;
    private final LoadStorePort loadStorePort;

    /**
     * 각 구별 가게 갯수를 반환합니다.
     */
    @Override
    public List<DistrictCountResponse> getCount() {
        return loadStorePort.getDistrictCount();
    }

    /**
     * 가게 정보가 필요한 구 이름과 현재 페이지 번호를 받아 가게 리스트를 반환합니다.
     */
    @Override
    public List<StoreResponse> getList(List<District> district, int page) {
        return loadStorePort.getList(district, PageRequest.of(page - 1, 15));
    }

    /**
     * 가게 아이디를 받아 가게 상세정보를 반환합니다.
     */
    @Override
    public StoreDetailResponse getStoreDetail(Long storeId){
        StoreResponse storeResponse = loadStorePort.getStoreDetail(storeId);
        final String storeName = storeResponse.getName();
        // 네이버 지역 검색 api 결과값 리턴
        StoreNaverLocalSearchApiResponse storeNaverLocalSearchApiResponse = loadNaverSearchApiPort.getNaverLocalSearchResponse(storeName);
        // 네이버 블로그 검색 api 결과값 리턴
        List<StoreNaverBlogSearchApiResponse> storeNaverBlogSearchApiResponseList = loadNaverSearchApiPort.getNaverBlogSearchResponse(storeName);
        // 추가 정보 합쳐서 리턴
        return new StoreDetailResponse(storeResponse, storeNaverLocalSearchApiResponse, storeNaverBlogSearchApiResponseList);
    }
}