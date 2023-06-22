package prography.cakeke.server.admin.adapter.in.web;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import prography.cakeke.server.admin.application.port.in.AdminUseCase;
import prography.cakeke.server.store.adapter.in.web.response.StoreResponse;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminUseCase adminUseCase;

    @PostMapping(value = "/upload/image/{storeName}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "케이크샵 이미지 업로드", description = "AWS S3에 케이크샵 이미지 업로드")
    public ResponseEntity<StoreResponse> uploadImage(
            @PathVariable(value = "storeName") String storeName,
            @RequestPart("image") List<MultipartFile> files) {
        return ResponseEntity.ok().body(this.adminUseCase.uploadImage(storeName, files).toResponse());
    }
}
