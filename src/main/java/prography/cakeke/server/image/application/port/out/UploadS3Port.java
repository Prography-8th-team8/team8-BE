package prography.cakeke.server.image.application.port.out;

import org.springframework.web.multipart.MultipartFile;

public interface UploadS3Port {
    String uploadS3(MultipartFile file);
}
