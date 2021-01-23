package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.ImageData;
import pl.michalfiedor.cvbuilder.model.User;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final CvService cvService;

    public void saveImageFile(ImageData imageData, MultipartFile image) throws IOException {
        Path uploadPath = Paths.get(imageData.getUploadDir());

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream input = image.getInputStream()) {
            Path filePath = uploadPath.resolve(imageData.getFileName() + "." + imageData.getExtension());
            Files.copy(input, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw new IOException("Could not save image file: " + imageData.getFileName(), exception);
        }
    }

    public void saveImagePathInCv(ImageData imageData, Cv cv){
        cv.setImagePath(imageData.getFilePath().toAbsolutePath().toString());
        cvService.save(cv);
    }

    public ImageData createImageData(User user, MultipartFile image){
        ImageData imageData = ImageData.builder()
                .fileName("userPhoto_id_" + user.getId())
                .uploadDir("user_id_" + user.getId())
                .extension(FilenameUtils.getExtension(image.getOriginalFilename()))
                .build();
        imageData.setImgPath(imageData.getUploadDir() + "/" + imageData.getFileName()
                + "." + imageData.getExtension());
        imageData.setFilePath(Paths.get(imageData.getImgPath()));
        return imageData;
    }
}
