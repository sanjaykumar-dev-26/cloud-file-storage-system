package com.sanjay.service;

import com.sanjay.model.FileMetadata;
import com.sanjay.service.S3Service;

import java.io.FileWriter;
import java.util.List;

public class ReportService {

    public void generateReport(List<FileMetadata> files) {
        try {
            FileWriter writer = new FileWriter("reports/storage-report.txt");

            writer.write("===== Cloud Storage Report =====\n\n");

            long totalSize = 0;

            for (FileMetadata file : files) {
                writer.write(file.toString() + "\n");
                totalSize += file.getSize();
            }

            writer.write("\nTotal Files: " + files.size());
            writer.write("\nTotal Storage Used: " + totalSize + " bytes");

            writer.close();

            S3Service s3Service = new S3Service();
s3Service.uploadFile("reports/storage-report.txt");

            System.out.println("Storage report generated successfully: reports/storage-report.txt");

        } catch (Exception e) {
            System.out.println("Report generation failed: " + e.getMessage());
        }
    }
}