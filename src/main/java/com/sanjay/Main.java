package com.sanjay;

import com.sanjay.service.S3Service;
import com.sanjay.service.ReportService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        S3Service s3Service = new S3Service();
        ReportService reportService = new ReportService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Cloud File Storage System =====");
            System.out.println("1. Upload File");
            System.out.println("2. List Files");
            System.out.println("3. Download File");
            System.out.println("4. Delete File");
            System.out.println("5. Generate Storage Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file path: ");
                    String uploadPath = sc.nextLine();
                    s3Service.uploadFile(uploadPath);
                    break;

                case 2:
                    s3Service.listFiles();
                    break;

                case 3:
                    System.out.print("Enter file name to download: ");
                    String downloadName = sc.nextLine();
                    s3Service.downloadFile(downloadName);
                    break;

                case 4:
                    System.out.print("Enter file name to delete: ");
                    String deleteName = sc.nextLine();
                    s3Service.deleteFile(deleteName);
                    break;

                case 5:
                    reportService.generateReport(s3Service.getFileMetadataList());
                    break;

                case 6:
                    System.out.println("Exiting application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}