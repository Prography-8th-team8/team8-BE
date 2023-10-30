package prography.cakeke.server.image.application.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.image.application.port.in.ImageUseCase;
import prography.cakeke.server.image.application.port.out.UploadS3Port;
import prography.cakeke.server.image.exceptions.NotSupportedFileFormatException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ImageService implements ImageUseCase {

    private static final List<String> FILETYPE = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/jpg"
    );

    private final UploadS3Port uploadS3Port;

    /**
     * 이미지들을 s3에 업로드합니다.
     * @return 업로드된 이미지 url 리스트
     */
    @Override
    public List<String> uploadImages(List<MultipartFile> multipartFiles) {
        List<String> fileNameList = new ArrayList<>();

        multipartFiles.forEach(file -> {
            mimeValidation(file.getContentType());
            String fileLink = uploadS3Port.uploadS3(file);
            fileNameList.add(fileLink);
        });
        return fileNameList;
    }

    private void mimeValidation(String mime) {
        if (!FILETYPE.contains(mime)) {
            throw new NotSupportedFileFormatException(mime);
        }
    }
}
