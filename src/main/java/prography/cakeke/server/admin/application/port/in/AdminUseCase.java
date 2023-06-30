package prography.cakeke.server.admin.application.port.in;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreType;

public interface AdminUseCase {

    Store uploadImage(String storeName, List<MultipartFile> files);

    Store updateCategory(String storeName, List<StoreType> storeTypes);
}
