# Cloud File Storage & Report Management System

A Java-based AWS project that allows users to upload, download, list, and delete files from Amazon S3. It also generates storage reports with file metadata.

## Features
- Upload files to AWS S3
- Download files from AWS S3
- List all uploaded files
- Delete files from S3
- Generate storage report
- Track file metadata like name, size, and last modified time
- Upload generated report to S3

## Tech Stack
- Java
- Maven
- AWS S3
- AWS SDK for Java
- IAM
- Git & GitHub

## AWS Services Used
- Amazon S3
- IAM

## Project Structure
- `config` - AWS configuration
- `model` - File metadata model
- `service` - S3 and report services
- `reports` - generated reports
- `downloads` - downloaded files
- `test-files` - sample files for upload

## How to Run
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass=com.sanjay.Main