package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.PdfData;
import pl.michalfiedor.cvbuilder.model.User;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PdfService {
    private final CvService cvService;

    public PdfData createPdfData(User user, Cv cv){
        PdfData pdfData = null;
        try {
            pdfData = PdfData.builder()
                    .dirPath(Paths.get("user_id_" + user.getId()))
                    .fileName("user_" + user.getId() + "_cv_" + cv.getId() + ".pdf")
                    .pdDocument(PDDocument.load(new File("cvTemplate.pdf")))
                    .build();
            pdfData.setFilePath(pdfData.getDirPath().resolve(pdfData.getFileName()));
            pdfData.setPage(pdfData.getPdDocument().getPage(0));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfData;

    }

    public void savePdfPathAndNameInCv(Cv cv, PdfData pdfData){
        cv.setCvPath(pdfData.getFilePath().toAbsolutePath().toString());
        cv.setCvFileName(pdfData.getFileName());
        cvService.save(cv);
    }

    public void createDirectories(PdfData pdfData) {
        if(!Files.exists(pdfData.getDirPath())){
            try {
                Files.createDirectories(pdfData.getDirPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createCvPdf(PdfData pdfData, Cv cv){
        PdfServiceBuilder pdfService = new PdfServiceBuilder.Builder(pdfData.getPdDocument(), cv, pdfData.getPage())
                .addAboutMeToPdfSheet()
                .addBasicDataToPdfSheet()
                .addFirstAndLastNameToPdfSheet()
                .addExperienceToPdfSheet()
                .addEducationToPdfSheet()
                .addPhotoToPdfSheet()
                .build();
        try {
            pdfService.getPdDocument().save(pdfData.getFilePath().toString());
            pdfService.getPdDocument().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadPdf(Long id, HttpServletResponse response) throws FileNotFoundException {
        Cv cv = cvService.getById(id);
        Path file = Paths.get(cv.getCvPath());
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + cv.getCvFileName());
            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException("Error writing file to output stream.");
            }
        }else {
            throw new FileNotFoundException("File does not exist");
        }
    }
}
