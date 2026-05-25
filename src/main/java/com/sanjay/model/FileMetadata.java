package com.sanjay.model;

import java.util.ArrayList;
import java.util.List;

import com.sanjay.config.AWSConfig;

import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

public class FileMetadata {
    private String fileName;
    private long size;
    private String lastModified;

    public FileMetadata(String fileName, long size, String lastModified) {
        this.fileName = fileName;
        this.size = size;
        this.lastModified = lastModified;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSize() {
        return size;
    }

    public String getLastModified() {
        return lastModified;
    }

    @Override
    public String toString() {
        return "File Name: " + fileName +
                ", Size: " + size + " bytes" +
                ", Last Modified: " + lastModified;
    }
   
}