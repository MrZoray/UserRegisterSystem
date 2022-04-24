package com.zoray.UserRegister.dto.fileDto;

import com.zoray.UserRegister.dto.customerDto.CustomerViewDTO;
import com.zoray.UserRegister.model.Customer;
import com.zoray.UserRegister.model.File;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileViewDTO {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private Long size;

    public FileViewDTO(String fileName, String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "FileViewDTO{" +
                "fileName='" + fileName + '\'' +
                ", fileDownloadUri='" + fileDownloadUri + '\'' +
                ", fileType='" + fileType + '\'' +
                ", size=" + size +
                '}';
    }

    public static FileViewDTO of(File file) {
        return new FileViewDTO(file.getFileName(), file.getFileType());
    }

}
