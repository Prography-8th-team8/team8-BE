package prography.cakeke.server.admin.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.admin.application.port.in.AdminUseCase;
import prography.cakeke.server.image.application.port.in.ImageUseCase;
import prography.cakeke.server.store.application.port.in.StoreUseCase;
import prography.cakeke.server.store.domain.Store;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminService implements AdminUseCase {

    private static final int REPRESENTATIVE_INDEX = 0;    // 0번째 업로드 되는 사진은 대표사진
    private final StoreUseCase storeUseCase;
    private final ImageUseCase imageUseCase;

    /**
     * 해당 가게의 대표이미지와 이미지를 업로드합니다.
     * @param storeName 가게 이름
     * @param files 가져올 블로그 개수 (기본 값 3)
     * @return 가게 정보
     */
    @Override
    @Transactional
    public Store uploadImage(String storeName, List<MultipartFile> files) {
        Store store = storeUseCase.getByName(storeName);
        List<String> imageLinks = imageUseCase.uploadImages(files);

        // 대표이미지 먼저 바꾼다. (업로드 할 떄, 0번째 업로드 되는 사진은 대표사진이라는 제약이 있다.)
        store.uploadThumbnail(imageLinks.get(REPRESENTATIVE_INDEX));
        // imageLinks의 첫 번째인 대표이미지를 제외한 나머지 사진들은 모두 이미지 필드에 추가한다.
        imageLinks.remove(REPRESENTATIVE_INDEX);
        store.uploadImageUrls(imageLinks);

        return store;
    }
}
