package prography.cakeke.server.store.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import prography.cakeke.server.store.adapter.in.web.response.UploadImageResponse;

public interface ImageUseCase {
    UploadImageResponse postUploadImage(List<MultipartFile> multipartFiles);
}
