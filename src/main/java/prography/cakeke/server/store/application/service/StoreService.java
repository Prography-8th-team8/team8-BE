package prography.cakeke.server.store.application.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreBlogResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreDetailResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreNaverBlogSearchApiResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreNaverLocalSearchApiResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.application.port.out.LoadNaverSearchApiPort;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreType;
import prography.cakeke.server.store.exceptions.NotAllowedLocationException;
import prography.cakeke.server.store.exceptions.NotFoundStoreException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService implements StoreUseCase {
    private static final Integer PAGE_SIZE = 100;   // 한 페이지당 사이즈
    private static final Double SEOUL_SOUTHWEST_LATITUDE = 37.4289;   // 남서쪽 위도
    private static final Double SEOUL_SOUTHWEST_LONGITUDE = 126.7649;     // 남서쪽 경도
    private static final Double SEOUL_NORTHEAST_LATITUDE = 37.7010;     // 북동쪽 위도
    private static final Double SEOUL_NORTHEAST_LONGITUDE = 127.1839;    // 북동쪽 경도

    private final LoadNaverSearchApiPort loadNaverSearchApiPort;
    private final LoadStorePort loadStorePort;

    /**
     * 각 구별 가게의 개수를 반환합니다.
     * @return 각 구별 가게의 개수
     */
    @Override
    public List<DistrictCountResponse> getCount() {
        return loadStorePort.getDistrictCount();
    }

    /**
     * 가게 정보가 필요한 구 이름과 현재 페이지 번호를 받아 가게 리스트를 반환합니다.
     * @param district 구 이름
     * @param page 페이지 번호
     * @return 가게 리스트
     */
    @Override
    public List<StoreResponse> getList(List<District> district, List<StoreType> storeTypes, int page) {
        // southwestLatitude, southwestLongitude, northeastLatitude, northeastLongitude는 null
        return loadStorePort.getList(district, storeTypes, PageRequest.of(page - 1, PAGE_SIZE),
                                     null, null, null, null);
    }

    /**
     * 현재 화면의 남서쪽 위도, 경도, 북동쪽 위도, 경도를 받아 해당하는 가게 리스트를 반환합니다.
     * @param page 페이지 번호
     * @param southwestLatitude 남서쪽 위도
     * @param southwestLongitude 남서쪽 경도
     * @param northeastLatitude 북동쪽 위도
     * @param northeastLongitude 북동쪽 경도
     * @return 가게 리스트
     */
    @Override
    public List<StoreResponse> reload(
            List<StoreType> storeTypes, int page,
            Double southwestLatitude, Double southwestLongitude,
            Double northeastLatitude, Double northeastLongitude
    ) {
        // 서울시 외곽의 서비스 불가 지역 체크
        if (northeastLatitude < SEOUL_SOUTHWEST_LATITUDE || southwestLatitude > SEOUL_NORTHEAST_LATITUDE) {
            throw new NotAllowedLocationException();
        }
        if (northeastLongitude < SEOUL_SOUTHWEST_LONGITUDE || southwestLongitude > SEOUL_NORTHEAST_LONGITUDE) {
            throw new NotAllowedLocationException();
        }

        // district는 null
        return loadStorePort.getList(
                null, storeTypes, PageRequest.of(page - 1, PAGE_SIZE),
                southwestLatitude, southwestLongitude,
                northeastLatitude, northeastLongitude
        );
    }

    /**
     * 가게 아이디를 받아 가게 상세정보를 반환합니다.
     * @param storeId 가게 아이디
     * @return 가게 상세정보
     */
    @Override
    public StoreDetailResponse getStoreDetail(Long storeId) {
        StoreResponse storeResponse = loadStorePort.getStoreDetail(storeId).get(storeId);
        final String storeName = storeResponse.getName();
        // 네이버 지역 검색 api 결과값 리턴
        StoreNaverLocalSearchApiResponse storeNaverLocalSearchApiResponse =
                loadNaverSearchApiPort.getNaverLocalSearchResponse(storeName);
        // 추가 정보 합쳐서 리턴
        return new StoreDetailResponse(storeResponse, storeNaverLocalSearchApiResponse);
    }

    /**
     * 해당 가게의 네이버 블로그 정보를 반환합니다.
     * @param storeId 가게 아이디
     * @param blogNum 가져올 블로그 개수 (기본 값 3)
     * @return 블로그 리스트
     */
    @Override
    public StoreBlogResponse getStoreBlog(Long storeId, Integer blogNum) {
        StoreResponse storeResponse = loadStorePort.getStoreDetail(storeId).get(storeId);
        final String storeName = storeResponse.getName();
        // 네이버 블로그 검색 api 결과값 리턴
        List<StoreNaverBlogSearchApiResponse> storeNaverBlogSearchApiResponseList =
                loadNaverSearchApiPort.getNaverBlogSearchResponse(storeName, blogNum);
        return new StoreBlogResponse(storeNaverBlogSearchApiResponseList);
    }

    /**
     * 해당 가게 정보를 반환합니다.
     * @param name 가게 이름
     * @return 가게 정보
     */
    @Override
    public Store getByName(String name) {
        return loadStorePort.getByName(name).orElseThrow(NotFoundStoreException::new);
    }
}
