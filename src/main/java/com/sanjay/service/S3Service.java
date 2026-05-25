package com.sanjay.service;

import com.sanjay.model.FileMetadata;
import java.util.ArrayList;
import java.util.List;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import com.sanjay.config.AWSConfig;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.nio.file.Path;
import java.nio.file.Paths;

public class S3Service {

    private final S3Client s3Client;

    public S3Service() {
        this.s3Client = AWSConfig.getS3Client();
    }

    public void testConnection() {

        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        System.out.println("S3 Connected Successfully!");
        System.out.println("Files count: " + response.contents().size());
    }
    public void uploadFile(String filePath) {
    try {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .key(fileName)
                .build();

        s3Client.putObject(request, RequestBody.fromFile(path));

        System.out.println("File uploaded successfully: " + fileName);

    } catch (Exception e) {
        System.out.println("Upload failed: " + e.getMessage());
    }
}
public void listFiles() {
    try {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        if (response.contents().isEmpty()) {
            System.out.println("No files found in bucket.");
            return;
        }

        System.out.println("Files in S3 bucket:");

        for (S3Object object : response.contents()) {
            System.out.println("- " + object.key() + " | Size: " + object.size() + " bytes");
        }

    } catch (Exception e) {
        System.out.println("List failed: " + e.getMessage());
    }
}
public void downloadFile(String fileName) {
    try {
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .key(fileName)
                .build();

        Path downloadPath = Paths.get("downloads/" + fileName);

        s3Client.getObject(request, downloadPath);

        System.out.println("File downloaded successfully: " + downloadPath);

    } catch (Exception e) {
        System.out.println("Download failed: " + e.getMessage());
    }
}
public void deleteFile(String fileName) {
    try {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .key(fileName)
                .build();

        s3Client.deleteObject(request);

        System.out.println("File deleted successfully: " + fileName);

    } catch (Exception e) {
        System.out.println("Delete failed: " + e.getMessage());
    }
}


 public List<FileMetadata> getFileMetadataList() {
    List<FileMetadata> files = new ArrayList<>();

    try {
        ListObjectsV2Request request = ListObjectsV2Request.builder()
                .bucket(AWSConfig.BUCKET_NAME)
                .build();

        ListObjectsV2Response response = s3Client.listObjectsV2(request);

        for (S3Object object : response.contents()) {
            files.add(new FileMetadata(
                    object.key(),
                    object.size(),
                    object.lastModified().toString()
            ));
        }

    } catch (Exception e) {
        System.out.println("Metadata fetch failed: " + e.getMessage());
    }

    return files;
}
}