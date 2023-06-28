package com.kestrel.weddingbookkeeper.external.storage;

import com.amazonaws.services.s3.AmazonS3Client;
import com.kestrel.weddingbookkeeper.external.storage.exception.AwsS3Exception;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AwsS3FileService implements FileService {

    @Value("${cloud.aws.s3.bucket.name}")
    private String bucketName;

    @Value("${cloud.aws.s3.bucket.url}")
    private String defaultUrl;

    private final AmazonS3Client amazonS3Client;

    public AwsS3FileService(final AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    @Override
    public String upload(InputStream inputStream, String directory) {
        try (InputStream resource = inputStream) {
            String fileName = generateFileName(directory);
            amazonS3Client.putObject(bucketName, fileName, resource, null);
            return getResourceUrl(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            throw new AwsS3Exception();
        }
    }

    private String generateFileName(String directory) {
        return String.format("%s/%s.png", directory, getRandomUUID());
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String getResourceUrl(String savedFileName) {
        return amazonS3Client.getResourceUrl(bucketName, savedFileName);
    }
}
