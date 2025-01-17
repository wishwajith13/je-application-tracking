package com.jeewaeducation.application_tracking.controller;

import com.jeewaeducation.application_tracking.utility.StandardResponse;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jeewaeducation.application_tracking.service.S3Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequestMapping(value = "/api/v1/s3")
@CrossOrigin
public class S3Controller {
    @Autowired
    private S3Service s3Service;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @ApiOperation(value = "Upload a file to S3")
    public ResponseEntity<StandardResponse> upload(@RequestParam("file") MultipartFile file, @RequestParam("studentId") int studentId, @RequestParam("folderCategory") String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.saveFile(file, studentId, folderCategory)), HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{studentId}/{folderCategory}/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String studentId,@PathVariable String folderCategory, @PathVariable String filename) {
        byte[] fileData = s3Service.downloadFile(studentId, folderCategory, filename);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileData);
    }

    @DeleteMapping("/deleteFiles/{studentId}")
    public ResponseEntity<StandardResponse> deleteFiles(@PathVariable int studentId) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFilesByStudentId(studentId)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFiles/{studentId}/{folderCategory}")
    public ResponseEntity<StandardResponse> deleteFiles(@PathVariable int studentId,@PathVariable String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFilesByCategory(studentId, folderCategory)), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFile/{studentId}/{folderCategory}/{filename}")
    public ResponseEntity<StandardResponse> deleteFile(@PathVariable("studentId") int studentId, @PathVariable("folderCategory") String folderCategory, @PathVariable("filename") String filename) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFile(studentId, folderCategory, filename)), HttpStatus.OK);
    }

    @GetMapping("/getAllFiles")
    public ResponseEntity<StandardResponse> getAllFiles() {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.listAllFiles()), HttpStatus.OK);
    }

    @GetMapping("/listFiles/{studentId}/{folderCategory}")
    public ResponseEntity<StandardResponse> listFiles(@PathVariable int studentId,@PathVariable String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.listFilesByStudentId(studentId,folderCategory)), HttpStatus.OK);
    }
}
