package com.bosonit.sa2.application.impl;

import com.bosonit.sa2.application.service.FileService;
import com.bosonit.sa2.domain.File;
import com.bosonit.sa2.infrastructure.repository.FileRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final Path rootFolder = Paths.get(".\\uploads");
    private final FileRepository fileRepository;
    private final ModelMapper modelMapper;

    // Save file in local path.
    @Override
    public void save(MultipartFile file) throws Exception {
        // A type of method where we can add files to a local path.
        Files.copy(file.getInputStream(), this.rootFolder.resolve(Objects.requireNonNull(file.getOriginalFilename())));
    }

    @Override
    public void save(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            this.save(file);
        }
    }

    // Save file in local path.
    @Override
    public void uploadToLocal(MultipartFile multipartFile) {
        // Another method to add file in local path.
        try {
            byte[] data = multipartFile.getBytes();
            String uploadFolderPath = "C:\\Users\\sergio.fuentes\\Downloads";
            Path path = Paths.get(uploadFolderPath, multipartFile.getOriginalFilename());
            Files.write(path, data);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void uploadToLocal(List<MultipartFile> files) throws Exception {
        for (MultipartFile file : files) {
            this.save(file);
        }
    }

    // Upload a file to a database.
    @Override
    public File uploadToDB(MultipartFile fileDB) {
        File file = new File();
        try {
            file.setFileData(fileDB.getBytes());
            file.setFileType(fileDB.getContentType());
            file.setFileName(fileDB.getOriginalFilename());
            file.setUploadDate(new Date());
            return fileRepository.save(file);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Download files from database.
    @Override
    public File downloadFileId(String fileId) {
        Optional<File> file = fileRepository.findById(fileId);
        return modelMapper.map(file, File.class);
    }

    @Override
    public File downloadFileName(String fileName) {
        Optional<File> file = fileRepository.findByFileName(fileName);
        return modelMapper.map(file, File.class);
    }
}
