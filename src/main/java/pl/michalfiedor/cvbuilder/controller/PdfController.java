package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.PdfData;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.service.*;
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
    private final CvService cvService;
    private final PdfService pdfService;

    @GetMapping("/print")
    public String createPdf(HttpSession session, Model model, Principal principal)   {
        User user = userService.get(principal.getName());
        Cv cv = cvService.getById(cvService.getCvIdFromSession(session));
        PdfData pdfData = pdfService.createPdfData(user, cv);
        pdfService.createDirectories(pdfData);
        pdfService.savePdfPathAndNameInCv(cv, pdfData);
        pdfService.createCvPdf(pdfData, cv);
        model.addAttribute("cvId", cv.getId());
        return "dashboard";
    }

    @GetMapping("/get-file/{id}")
    public void getCvFile(@PathVariable long id, HttpServletResponse response) {
        try {
            pdfService.downloadPdf(id, response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @ModelAttribute("cvs")
    public List<Cv> getCvsList(){
        return cvService.findAll();
    }
}


