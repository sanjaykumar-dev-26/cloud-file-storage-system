package com.sanjay.config;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class AWSConfig {

    public static final String BUCKET_NAME = "cloud-file-storage-sanjay-001";

    public static S3Client getS3Client() {
        return S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .build();
    }
}