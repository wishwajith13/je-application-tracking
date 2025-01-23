package com.jeewaeducation.application_tracking.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface S3Service {


    String saveFile(MultipartFile filename, int studentId, String folderCategory);

    byte[] downloadFile(String studentId,String folderCategory, String filename);
    String deleteFile(int studentId, String folderCategory, String filename);

    List<String> listAllFiles();

    List<String> listFilesByStudentId(int studentId, String folderCategory);

    String deleteFilesByStudentId(int studentId);

    Object deleteFilesByCategory(int studentId, String folderCategory);
}
