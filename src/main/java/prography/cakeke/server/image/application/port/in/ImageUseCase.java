package prography.cakeke.server.image.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUseCase {

    List<String> uploadImages(List<MultipartFile> multipartFiles);
}
