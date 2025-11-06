package com.project.havebin.global.infrastructure.s3;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3ImageService {
    private final S3Client s3;

    @Value("${spring.cloud.aws.s3.bucket}")
    private String bucketName;

    // 원본 이미지 이름에 붙일 랜덤 UUID를 생성하는 메소드 (이름 중복 방지)
    private String changedImageName(String originName) {
        String random = UUID.randomUUID().toString();
        return random + originName;
    }

    // 이미지를 S3에 업로드하고 이미지의 url을 반환 (Trashcan)
    public String uploadImageToS3(MultipartFile image, String type) {
        // 원본 이미지 이름
        String originName = image
                .getOriginalFilename();

        // 새로 생성된 이미지 이름
        String changedName = type+ "/" + changedImageName(originName);

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(changedName)
                    .acl("public-read")
                    .contentType("image/png")
                    .build();

            s3.putObject(putObjectRequest, RequestBody.fromInputStream(image.getInputStream(), image.getSize()));
        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }

        return s3.utilities()
                .getUrl(builder -> builder.bucket(bucketName).key(changedName))
                .toExternalForm(); // 데이터베이스에 저장할 이미지가 저장된 주소
    }

    // 주소예시: https://havebin.s3.ap-northeast-2.amazonaws.com/Trashcan/25fe6837-8b16-4dd8-91f7-1f002ac92497.png
    public String moveFileInS3(String originalKey) {
        String path = extractFileNameFromURL(originalKey);
        String oldkey = "Unknown_Trashcan/" + path;
        String newKey = "Trashcan/" + path;

        CopyObjectRequest copyRequest = CopyObjectRequest.builder()
                .sourceBucket(bucketName)
                .destinationBucket(bucketName)
                .sourceKey(oldkey)
                .destinationKey(newKey)
                .contentType("image/png")
                .build();
        s3.copyObject(copyRequest);

        // Delete the original file
        DeleteObjectRequest deleteRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(oldkey)
                .build();
        s3.deleteObject(deleteRequest);

        return s3.utilities().getUrl(builder -> builder.bucket(bucketName).key(newKey)).toExternalForm();
    }

    public static String extractFileNameFromURL(String url) {
        if (url == null || url.isEmpty()) {
            return ""; // URL이 null이거나 빈 문자열인 경우 빈 문자열 반환
        }

        // URL의 마지막 '/' 문자 위치를 찾습니다.
        int lastIndex = url.lastIndexOf('/');

        // '/' 이후의 문자열을 반환합니다. '/'가 없는 경우 전체 URL을 반환합니다.
        return lastIndex != -1 ? url.substring(lastIndex + 1) : url;
    }
}
