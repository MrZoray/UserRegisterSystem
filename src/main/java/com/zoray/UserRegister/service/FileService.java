package com.zoray.UserRegister.service;

import com.zoray.UserRegister.dto.fileDto.FileViewDTO;
import com.zoray.UserRegister.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {


    void init();

    File saveFile(MultipartFile file, String uri);

    List<FileViewDTO> getFiles();

    void updateFile(MultipartFile file, Long fileId);

    void deleteFile(Long fileId);


}
