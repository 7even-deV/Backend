package com.bosonit.sa2.application.service;

import com.bosonit.sa2.domain.File;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    public void save(MultipartFile file) throws Exception;
    public void save(List<MultipartFile> file) throws Exception;
    public void uploadToLocal(MultipartFile multipartFile);
    public void uploadToLocal(List<MultipartFile> files) throws Exception;
    public File uploadToDB(MultipartFile multipartFile);
    public File downloadFileId(String fileId);
    public File downloadFileName(String fileName);
}
