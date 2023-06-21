package prography.cakeke.server.image.application.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.RequiredArgsConstructor;
import prography.cakeke.server.image.application.port.in.ImageUseCase;
import prography.cakeke.server.image.exceptions.InvalidFileNameException;
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

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.store-dir}")
    private String dirName;

    @Value("${cloud.aws.s3.base-url}")
    private String baseUrl;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * 이미지들을 s3에 업로드합니다.
     * @return 업로드된 이미지 url 리스트
     */
    @Override
    public List<String> uploadImages(List<MultipartFile> multipartFiles) {
        List<String> fileNameList = new ArrayList<>();

        multipartFiles.forEach(file -> {
            mimeValidation(file.getContentType());
            String fileName = dirName + createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());
            try (InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                                           .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다.");
            }
            fileNameList.add(baseUrl + fileName);
        });
        return fileNameList;
    }

    private void mimeValidation(String mime) {
        if (!FILETYPE.contains(mime)) {
            throw new NotSupportedFileFormatException(mime);
        }
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new InvalidFileNameException();
        }
    }
}
