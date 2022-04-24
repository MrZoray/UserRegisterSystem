package com.zoray.UserRegister.controller;

import com.zoray.UserRegister.dto.fileDto.FileViewDTO;
import com.zoray.UserRegister.service.Impl.FileServiceImpl;
import com.zoray.UserRegister.shared.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {


    @Autowired
    FileServiceImpl fileService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(file.getOriginalFilename())
                .toUriString();
        fileService.saveFile(file, fileDownloadUri);
        FileViewDTO fileViewDTO = new FileViewDTO();
        return fileViewDTO.toString();
    }

    @PutMapping("/updateFile/{id}")
    public ResponseEntity<?> updateFile(@Valid @PathVariable("id") Long id,
                                        @RequestBody MultipartFile file) {
        try{
            fileService.updateFile(file, id);
            return ResponseEntity.ok(new GenericResponse("File Updated!"));
        }catch (Exception e){
            return ResponseEntity.ok(new GenericResponse("File Update Error!"));
        }

    }

    @GetMapping("/getFiles")
    public ResponseEntity<List<FileViewDTO>> getFiles() {
        final List<FileViewDTO> fileList = fileService.getFiles();
        return ResponseEntity.ok(fileList);
    }

    @DeleteMapping("/deleteFile/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable Long fileId) {
       try{
           fileService.deleteFile(fileId);
           return ResponseEntity.ok(new GenericResponse("File Deleted!"));
       }catch (Exception e) {
           return ResponseEntity.ok(new GenericResponse("File Delete Error!"));
       }

    }



}

