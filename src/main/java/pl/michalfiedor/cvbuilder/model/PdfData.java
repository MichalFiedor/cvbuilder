package pl.michalfiedor.cvbuilder.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.nio.file.Path;

@Builder
@Getter
@Setter
public class PdfData {
    private Path dirPath;
    private String fileName;
    private Path filePath;
    private PDDocument pdDocument;
    private PDPage page;
}
