package prography.cakeke.server.image.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import prography.cakeke.server.image.adapter.in.web.response.UploadImageResponse;
import prography.cakeke.server.image.application.port.in.ImageUseCase;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageUseCase imageUseCase;

    @Operation(description = "사진 S3 업로드")
    @PostMapping("/upload")
    public ResponseEntity<UploadImageResponse> postUploadImage(
            @RequestPart("image") List<MultipartFile> multipartFiles) {
        return ResponseEntity.ok(this.imageUseCase.postUploadImage(multipartFiles));
    }
}
