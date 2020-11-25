package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.CvGetter;
import pl.michalfiedor.cvbuilder.service.PdfPrinter;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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
        Cv cv = CvGetter.getCvFromSession(session, cvRepository);
        Path dirPath = Paths.get("user_id_" + user.getId());
        if(!Files.exists(dirPath)){
            Files.createDirectories(dirPath);
        }
        Path filePath = dirPath.resolve("user_" + user.getId() + "_cv_" + cv.getId() + ".pdf");
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

    @GetMapping("/get-file/{id}")
    public void getCvFile(@PathVariable long id, HttpServletResponse response){
        Cv cv = cvRepository.findById(id).orElseThrow();
        try{
            InputStream in = new DataInputStream(new FileInputStream(cv.getCvPath()));
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e){
            throw new RuntimeException("Error writing file to output stream.");
        }
    }
    @ModelAttribute("cvs")
    public List<Cv> getCvsList(){
        return cvRepository.findAll();
    }
}


