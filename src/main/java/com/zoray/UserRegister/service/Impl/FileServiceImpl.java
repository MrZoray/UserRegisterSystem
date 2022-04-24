package com.zoray.UserRegister.service.Impl;

import com.zoray.UserRegister.dto.fileDto.FileViewDTO;
import com.zoray.UserRegister.execption.NotFoundException;
import com.zoray.UserRegister.execption.StorageException;
import com.zoray.UserRegister.model.File;
import com.zoray.UserRegister.repository.FileRepository;
import com.zoray.UserRegister.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private final Path root = Paths.get("uploads");

    @Autowired
    private FileRepository fileRepository;


    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Transactional
    public File saveFile(MultipartFile file, String uri) {
        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if ((fileName.contains(".png")) || (fileName.contains(".jpeg")) || (fileName.contains(".jpg")) || (fileName.contains(".docx")) || (fileName.contains(".pdf")) || (fileName.contains(".xlsx"))) {
                File modelFile = new File(fileName.getBytes(), file.getContentType(), uri);
                Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                return fileRepository.save(modelFile);
            } else throw new StorageException("Sorry! Filename contains invalid path sequenth " + fileName);

        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    @Transactional
    public void updateFile(MultipartFile file, Long fileId) {
        fileRepository.findById(fileId)
                .map(value -> {
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/files/")
                            .path(file.getOriginalFilename())
                            .toUriString();
                    value.setFileName(file.getOriginalFilename());
                    value.setFileType(file.getContentType());
                    value.setFileUrl(fileDownloadUri);
                    try {
                        Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
                    } catch (IOException e) {
                        throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                    }
                    return fileRepository.save(value);
                }).orElseThrow(() -> new NotFoundException("File not found with id " + fileId));
    }

    @Transactional
    public List<FileViewDTO> getFiles() {
        return fileRepository.findAll().stream().map(FileViewDTO::of).collect(Collectors.toList());
    }

    @Transactional
    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }


}
