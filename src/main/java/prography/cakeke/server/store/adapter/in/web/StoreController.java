package prography.cakeke.server.store.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adapter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreBlogResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreDetailResponse;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.domain.District;
import prography.cakeke.server.store.domain.StoreType;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreUseCase storeUseCase;

    @Operation(description = "각 구별 가게 갯수 조회")
    @GetMapping("/district/count")
    public ResponseEntity<List<DistrictCountResponse>> getCount() {
        return ResponseEntity.ok().body(this.storeUseCase.getCount());
    }

    @Operation(description = "각 구별 가게 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreResponse>> getList(
            @RequestParam(value = "district") List<District> district,
            @RequestParam(value = "storeTypes", required = false) List<StoreType> storeTypes,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page
    ) {
        return ResponseEntity.ok().body(this.storeUseCase.getList(district, storeTypes, page));
    }

    @Operation(description = "이 지역 재검색")
    @GetMapping("/reload")
    public ResponseEntity<List<StoreResponse>> reload(
            @RequestParam(value = "storeTypes", required = false) List<StoreType> storeTypes,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "southwestLatitude", required = true) Double southwestLatitude,
            @RequestParam(value = "southwestLongitude", required = true) Double southwestLongitude,
            @RequestParam(value = "northeastLatitude", required = true) Double northeastLatitude,
            @RequestParam(value = "northeastLongitude", required = true) Double northeastLongitude
    ) {
        return ResponseEntity.ok().body(
                this.storeUseCase.reload(
                        storeTypes, page,
                        southwestLatitude, southwestLongitude,
                        northeastLatitude, northeastLongitude
                )
        );
    }

    @Operation(description = "케이크샵 상세 정보 조회(상세 정보만)")
    @GetMapping("/{id}")
    public ResponseEntity<StoreDetailResponse> getStoreDetail(@PathVariable("id") Long storeId) {
        return ResponseEntity.ok().body(this.storeUseCase.getStoreDetail(storeId));
    }

    @Operation(description = "케이크샵 블로그 정보 조회")
    @GetMapping("/{id}/blog")
    public ResponseEntity<StoreBlogResponse> getStoreBlog(
            @PathVariable(value = "id") Long storeId,
            @RequestParam(value = "num", required = false, defaultValue = "3") Integer blogNum) {
        return ResponseEntity.ok().body(this.storeUseCase.getStoreBlog(storeId, blogNum));
    }
}
