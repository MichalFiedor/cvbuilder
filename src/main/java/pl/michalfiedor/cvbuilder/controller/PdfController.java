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
import pl.michalfiedor.cvbuilder.service.CvService;
import pl.michalfiedor.cvbuilder.service.PdfService;
import pl.michalfiedor.cvbuilder.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {
    private final UserService userService;
    private final PdfService pdfService;
    private final CvService cvService;

    @GetMapping("/print")
    public String createPdf(HttpSession session, Model model, Principal principal) throws IOException {

        User user = userService.getUser(principal.getName());
        Cv cv = cvService.getCvById(cvService.getCvIdFromSession(session));
        Path dirPath = Paths.get("user_id_" + user.getId());
        if(!Files.exists(dirPath)){
            Files.createDirectories(dirPath);
        }
        String fileName = "user_" + user.getId() + "_cv_" + cv.getId() + ".pdf";
        Path filePath = dirPath.resolve(fileName);
        cv.setCvPath(filePath.toAbsolutePath().toString());
        cv.setCvFileName(fileName);
        cvService.save(cv);
        PDDocument pdDocument = PDDocument.load(new File("cvTemplate.pdf"));
        PDPage page = pdDocument.getPage(0);
        pdfService.addAboutMeToPdfSheet(cv, pdDocument, page);
        pdfService.addBasicDataToPdfSheet(cv, pdDocument, page);
        pdfService.addFirstAndLastNameToPdfSheet(cv, pdDocument, page);
        pdfService.addExperienceToPdfSheet(cv, pdDocument, page);
        pdfService.addEducationToPdfSheet(cv, pdDocument, page);
        pdfService.addPhotoToPdfSheet(cv, pdDocument, page);
        pdDocument.save(filePath.toString());
        pdDocument.close();
        model.addAttribute("cvId", cv.getId());
        return "dashboard";
    }

    @GetMapping("/get-file/{id}")
    public void getCvFile(@PathVariable long id, HttpServletResponse response){
        Cv cv = cvService.getCvById(id);
        Path file = Paths.get(cv.getCvPath());
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename=" + cv.getCvFileName());
            try {
                InputStream in = new DataInputStream(new FileInputStream(cv.getCvPath()));
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException("Error writing file to output stream.");
            }
        }else {
            System.out.println("File not found.");
        }
    }
    @ModelAttribute("cvs")
    public List<Cv> getCvsList(){
        return cvService.findAll();
    }
}


