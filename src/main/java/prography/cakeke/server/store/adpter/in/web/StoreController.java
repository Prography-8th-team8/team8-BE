package prography.cakeke.server.store.adpter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.store.adpter.in.web.response.DistrictCountResponse;
import prography.cakeke.server.store.adpter.in.web.response.StoreResponse;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.domain.District;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreUseCase storeUseCase;

    @GetMapping("/district/count")
    public ResponseEntity<List<DistrictCountResponse>> getCount() {
        return ResponseEntity.ok().body(this.storeUseCase.getCount());
    }

    @GetMapping("/list")
    public ResponseEntity<List<StoreResponse>> getList(
            @RequestParam(value = "district") List<District> district,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page
    ) {
        return ResponseEntity.ok().body(
                this.storeUseCase.getList(district, page));
    }
}
