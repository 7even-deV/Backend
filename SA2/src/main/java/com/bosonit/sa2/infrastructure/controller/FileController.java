package com.bosonit.sa2.infrastructure.controller;

import com.bosonit.sa2.application.service.FileService;
import com.bosonit.sa2.domain.File;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.NoSuchFileException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        try {
            fileService.save(files);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully load.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Corrupted or repeated file.");
        }
    }

    @PostMapping("/upload/local")
    public ResponseEntity<String> uploadLocal(@RequestParam("files") List<MultipartFile> files) {
        try {
            fileService.uploadToLocal(files);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully load.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Corrupted or repeated file.");
        }
    }

    @PostMapping("/upload/db")
    public ResponseEntity<String> uploadDB(@RequestParam("files") MultipartFile multipartFile) {
        File file = fileService.uploadToDB(multipartFile);
        if (!multipartFile.isEmpty() && file != null) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully load.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Fail to upload file, it's empty or missed.");
        }
    }

    @GetMapping("/{fileId}")
    public ResponseEntity<Object> getFileId(@PathVariable("fileId") String fileId) throws NoSuchFileException {
        File file = fileService.downloadFileId(fileId);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(file);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No file was found with id: " + fileId);
        }
    }

    @GetMapping("/name/{fileName}")
    public ResponseEntity<Object> getFileName(@PathVariable("fileName") String fileName) throws NoSuchFileException {
        File file = fileService.downloadFileName(fileName);
        if (file != null) {
            return ResponseEntity.status(HttpStatus.OK).body(file);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No file was found with name: " + fileName);
        }
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Object> downloadFileId(@PathVariable("fileId") String fileId) {
        File file = fileService.downloadFileId(fileId);
        if (file != null) {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + file.getFileName())
                    .body(new ByteArrayResource(file.getFileData()));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No file was found with id: " + fileId);
        }
    }

    @GetMapping("/download/name/{fileName}")
    public ResponseEntity<Object> downloadFileName(@PathVariable("fileName") String fileName) {
        File file = fileService.downloadFileName(fileName);
        if (file != null) {
            return ResponseEntity.ok().contentType(MediaType.parseMediaType(file.getFileType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + file.getFileName())
                    .body(new ByteArrayResource(file.getFileData()));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No file was found with name: " + fileName);
        }
    }
}
