package prography.cakeke.server.admin.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import prography.cakeke.server.store.domain.Store;

public interface AdminUseCase {

    Store uploadImage(String storeName, List<MultipartFile> files);
}
