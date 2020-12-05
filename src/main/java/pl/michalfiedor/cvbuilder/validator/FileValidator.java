package pl.michalfiedor.cvbuilder.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.michalfiedor.cvbuilder.exception.InvalidFileExtensionException;

@Component
public class FileValidator {

    public void validateExtension(MultipartFile file) throws InvalidFileExtensionException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!"png".equals(extension) && !"jpeg".equals(extension) && !"jpg".equals(extension)) {
            throw new InvalidFileExtensionException("Only jpg/jpeg and png files are accepted");
        }
    }
}
