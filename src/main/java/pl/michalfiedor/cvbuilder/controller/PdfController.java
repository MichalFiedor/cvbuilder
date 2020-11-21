package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.apache.pdfbox.tools.PDFBox;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.PdfPrinter;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {
    private final UserRepository userRepository;
    private final PdfPrinter pdfPrinter;
    private final CvRepository cvRepository;

    @GetMapping("/print")
    public String createPdf(HttpSession session, Model model) throws IOException {
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv cv = user.getCv();
        Path dirPath = Paths.get("user_id_" + user.getId());
        if(!Files.exists(dirPath)){
            Files.createDirectories(dirPath);
        }
        Path filePath = dirPath.resolve("user_" + user.getId() + "_cv.pdf");
        cv.setCvPath(filePath.toAbsolutePath().toString());
        cvRepository.save(cv);
        PDDocument pdDocument = PDDocument.load(new File("cvTemplate.pdf"));
        PDPage page = pdDocument.getPage(0);
        pdfPrinter.addAboutMeToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addBasicDataToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addFirstAndLastNameToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addExperienceToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addEducationToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addPhotoToPdfSheet(cv, pdDocument, page);
        pdDocument.save(filePath.toString());
        pdDocument.close();
        model.addAttribute("cvId", cv.getId());
        return "dashboard";
    }

    @GetMapping(
            value = "/get-file/{id}",
            produces = MediaType.APPLICATION_PDF_VALUE
    )

    public @ResponseBody byte [] getFile(@PathVariable long id) throws IOException{
        Cv cv = cvRepository.findById(id).orElseThrow();
        InputStream inputStream= Files.class
                .getResourceAsStream(cv.getCvPath());
        return IOUtils.toByteArray(inputStream);

    }
}


