package com.jeewaeducation.application_tracking.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.jeewaeducation.application_tracking.exception.DuplicateKeyException;
import com.jeewaeducation.application_tracking.exception.FileNotFoundException;
import com.jeewaeducation.application_tracking.exception.NotFoundException;
import com.jeewaeducation.application_tracking.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.jeewaeducation.application_tracking.exception.IOException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class S3ServiceIMPL implements S3Service {

    @Value("${bucketName}")
    private String bucketName;

    private final AmazonS3 s3;

    public S3ServiceIMPL(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String saveFile(MultipartFile filename, int studentId, String folderCategory) {
        String originalFilename = filename.getOriginalFilename();
        try (InputStream inputStream = filename.getInputStream()) {
            // Define the S3 key (path) using the student ID as a folder
            String key = "students/" + studentId + "/" + folderCategory + "/" + originalFilename;

            if (s3.doesObjectExist(bucketName, key)) {
                throw new DuplicateKeyException("File name exist: " + originalFilename +
                        ", Category: " + folderCategory + ", Student ID: " + studentId +
                        " : change the file name and again upload");
            }

            // Set metadata for the file
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(filename.getSize());

            // Upload the file to S3
            s3.putObject(new PutObjectRequest(bucketName, key, inputStream, metadata));

            return "File uploaded successfully for student ID: " + studentId + ", Category" + folderCategory +
                    ", Filename: " + filename.getOriginalFilename();

        } catch (java.io.IOException e) {
            throw new IOException("Error saving file to S3 for student ID: " + studentId);
        }
    }

    @Override
    public byte[] downloadFile(String studentId, String folderCategory, String filename) {
        String key = "students/" + studentId + "/" + folderCategory + "/" + filename;

        if (!s3.doesObjectExist(bucketName, key)) {
            throw new FileNotFoundException("File not found: " + filename + ", Category: " + folderCategory +
                    ", Student ID: " + studentId);
        }

        // Retrieve the file from S3
        S3Object object = s3.getObject(bucketName, key);
        S3ObjectInputStream objectContent = object.getObjectContent();

        try {
            return IOUtils.toByteArray(objectContent);
        } catch (java.io.IOException e) {
            throw new IOException("Error downloading file from S3: " + filename);
        }
    }

    @Override
    public String deleteFile(int studentId, String folderCategory, String filename) {
        String key = "students/" + studentId + "/" + folderCategory + "/" + filename;

        if (!s3.doesObjectExist(bucketName, key)) {
            throw new FileNotFoundException("File not found: " + filename + ", Category: " + folderCategory + " Student ID: " + studentId);
        }

        s3.deleteObject(bucketName, key);
        return "File deleted: " + filename;
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = s3.listObjectsV2(bucketName);

        List<String> fileKeys = listObjectsV2Result.getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());

        if (fileKeys.isEmpty()) {
            throw new FileNotFoundException("No files found. Please upload files.");
        }

        return fileKeys;
    }

    @Override
    public List<String> listFilesByStudentId(int studentId, String folderCategory) {
        String prefix = "students/" + studentId + "/" + folderCategory + "/"; // Prefix to filter files by student ID

        ListObjectsV2Request request = new ListObjectsV2Request() // Create a request to list objects with the specified prefix
                .withBucketName(bucketName)
                .withPrefix(prefix);

        ListObjectsV2Result result = s3.listObjectsV2(request); // Get the list of objects

        List<String> fileNames = result.getObjectSummaries()
                .stream()
                .map(s3ObjectSummary -> s3ObjectSummary.getKey().replace(prefix, "")) // Remove the prefix to get file names
                .collect(Collectors.toList());

        if (fileNames.isEmpty()) {
            throw new FileNotFoundException("No files found for student ID: " + studentId + " in folder : " + folderCategory + "."); // Check if no files are found and throw an exception
        }

        return fileNames;
    }

    @Override
    public String deleteFilesByStudentId(int studentId) {
        String prefix = "students/" + studentId + "/";

        // List all files under the student's prefix
        ListObjectsV2Request listRequest = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(prefix);

        ListObjectsV2Result listResult = s3.listObjectsV2(listRequest);

        // Delete each file
        List<DeleteObjectsRequest.KeyVersion> keysToDelete = listResult.getObjectSummaries()
                .stream()
                .map(summary -> new DeleteObjectsRequest.KeyVersion(summary.getKey()))
                .collect(Collectors.toList());

        if (keysToDelete.isEmpty()) {
            return "Not found student ID: " + studentId;
        }

        DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(keysToDelete);

        s3.deleteObjects(deleteRequest);

        return "All files for student ID: " + studentId + " have been deleted.";
    }

    @Override
    public Object deleteFilesByCategory(int studentId, String folderCategory) {
        String prefix = "students/" + studentId + "/" + folderCategory + "/";

        // List all files under the student's prefix
        ListObjectsV2Request listRequest = new ListObjectsV2Request()
                .withBucketName(bucketName)
                .withPrefix(prefix);

        ListObjectsV2Result listResult = s3.listObjectsV2(listRequest);

        // Delete each file
        List<DeleteObjectsRequest.KeyVersion> keysToDelete = listResult.getObjectSummaries()
                .stream()
                .map(summary -> new DeleteObjectsRequest.KeyVersion(summary.getKey()))
                .collect(Collectors.toList());

        if (keysToDelete.isEmpty()) {
            return "Folder not found: " + folderCategory + ", Student id: " + studentId;
        }

        DeleteObjectsRequest deleteRequest = new DeleteObjectsRequest(bucketName)
                .withKeys(keysToDelete);

        s3.deleteObjects(deleteRequest);

        return "All files in Category: " + studentId + " have been deleted.";
    }

    private File convertMultiPartFile(MultipartFile file) throws java.io.IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
