package pl.michalfiedor.cvbuilder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Builder
@Getter
@Setter
public class ImageData {
    private String fileName;
    private String uploadDir;
    private String extension;
    private String imgPath;
    private Path filePath;
}
