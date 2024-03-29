package prography.cakeke.server.admin.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.admin.application.port.in.AdminUseCase;
import prography.cakeke.server.image.application.port.in.ImageUseCase;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.application.port.out.DeleteStorePort;
import prography.cakeke.server.store.application.port.out.LoadStorePort;
import prography.cakeke.server.store.application.port.out.SaveStorePort;
import prography.cakeke.server.store.domain.Store;
import prography.cakeke.server.store.domain.StoreAndTag;
import prography.cakeke.server.store.domain.StoreType;
import prography.cakeke.server.store.exceptions.NotFoundStoreTagException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService implements AdminUseCase {

    private static final int REPRESENTATIVE_INDEX = 0;    // 0번째 업로드 되는 사진은 대표사진
    private final StoreUseCase storeUseCase;
    private final ImageUseCase imageUseCase;
    private final SaveStorePort saveStorePort;
    private final LoadStorePort loadStorePort;
    private final DeleteStorePort deleteStorePort;

    /**
     * 해당 가게의 대표이미지와 이미지를 업로드합니다.
     * @param storeName 가게 이름
     * @param files 가게 대표이미지와 이미지들
     * @return 가게 정보
     */
    @Override
    @Transactional
    public Store uploadImage(String storeName, List<MultipartFile> files) {
        Store store = storeUseCase.getByName(storeName);
        List<String> imageLinks = imageUseCase.uploadImages(files);

        store.uploadThumbnail(imageLinks.get(REPRESENTATIVE_INDEX));
        imageLinks.remove(REPRESENTATIVE_INDEX);
        store.uploadImageUrls(imageLinks);

        return store;
    }

    /**
     * 해당 가게의 케이크 카테고리를 변경합니다.
     * @param storeName 가게 이름
     * @param storeTypes 가게 케이크의 카테고리들
     * @return 가게 정보
     */
    @Override
    @Transactional
    public Store updateCategory(String storeName, List<StoreType> storeTypes) {
        Store store = storeUseCase.getByName(storeName);

        deleteStorePort.deleteStoreAndTagByStoreId(store.getId());

        List<StoreAndTag> category = storeTypes.stream()
                                               .map(loadStorePort::getStoreTagByStoreTag)
                                               // 위에서 구한 StoreTag를 StoreAndTag의 StoreTag에 넣고 저장해 StoreAndTag를 만든다.
                                               .map(it -> saveStorePort.saveStoreAndTag(
                                                       StoreAndTag.builder()
                                                                  .storeTag(it.orElseThrow(
                                                                          NotFoundStoreTagException::new))
                                                                  .store(store)
                                                                  .build()))
                                               .collect(Collectors.toList());

        store.updateCategory(category);
        return store;
    }
}
