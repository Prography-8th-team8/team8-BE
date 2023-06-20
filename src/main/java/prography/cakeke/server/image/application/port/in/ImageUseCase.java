package prography.cakeke.server.image.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import prography.cakeke.server.image.adapter.in.web.response.UploadImageResponse;

public interface ImageUseCase {
    UploadImageResponse uploadImage(List<MultipartFile> multipartFiles);
}
