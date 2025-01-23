package com.jeewaeducation.application_tracking.controller;

import com.jeewaeducation.application_tracking.utility.StandardResponse;
import io.swagger.annotations.ApiOperation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jeewaeducation.application_tracking.service.S3Service;
import org.springframework.web.multipart.MultipartFile;


import static java.net.HttpURLConnection.HTTP_OK;

@RestController
@RequestMapping(value = "/api/v1/s3")
@CrossOrigin
@RequiredArgsConstructor
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping(path = "/{studentId}/{folderCategory}", consumes = "multipart/form-data") //multipart/form-data is a MIME (Multipurpose Internet Mail Extensions) type used for submitting form data that includes files, along with other form fields. It allows the transfer of binary data (e.g., files) and textual data (e.g., strings) in a single request.
    @ApiOperation(value = "Upload a file to S3") // describe a specific operation (endpoint or method) in an API. Use for swagger.
    public ResponseEntity<StandardResponse> upload(@RequestParam("file") MultipartFile file, @PathVariable int studentId, @PathVariable String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.saveFile(file, studentId, folderCategory)), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/{folderCategory}/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String studentId, @PathVariable String folderCategory, @PathVariable String filename) {
        byte[] fileData = s3Service.downloadFile(studentId, folderCategory, filename);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileData);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<StandardResponse> deleteFiles(@PathVariable int studentId) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFilesByStudentId(studentId)), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/{folderCategory}")
    public ResponseEntity<StandardResponse> deleteFiles(@PathVariable int studentId, @PathVariable String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFilesByCategory(studentId, folderCategory)), HttpStatus.OK);
    }

    @DeleteMapping("/{studentId}/{folderCategory}/{filename}")
    public ResponseEntity<StandardResponse> deleteFile(@PathVariable("studentId") int studentId, @PathVariable("folderCategory") String folderCategory, @PathVariable("filename") String filename) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.deleteFile(studentId, folderCategory, filename)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllFiles() {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.listAllFiles()), HttpStatus.OK);
    }

    @GetMapping("/{studentId}/{folderCategory}")
    public ResponseEntity<StandardResponse> listFiles(@PathVariable int studentId, @PathVariable String folderCategory) {
        return new ResponseEntity<>(new StandardResponse(HTTP_OK, "Success",
                s3Service.listFilesByStudentId(studentId, folderCategory)), HttpStatus.OK);
    }
}
