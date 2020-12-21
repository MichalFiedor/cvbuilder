package pl.michalfiedor.cvbuilder.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void saveImageFile(String uploadDir, String fileName,
                       MultipartFile file, String extension) throws IOException;
}
