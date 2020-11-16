package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.PdfPrinter;
import pl.michalfiedor.cvbuilder.service.UserGetter;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pdf")
public class PdfController {
    private final UserRepository userRepository;
    private final PdfPrinter pdfPrinter;

    @GetMapping("/print")
    @ResponseBody
    public String createPdf(HttpSession session) throws Exception {
        User userFromSession = UserGetter.getUserFromSession(session, userRepository);
        Cv cv = userFromSession.getCv();
        PDDocument pdDocument = PDDocument.load(new File("cvTemplate.pdf"));
        PDPage page = pdDocument.getPage(0);
        pdfPrinter.addAboutMeToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addBasicDataToPdfSheet(cv, pdDocument, page);
        pdfPrinter.addFirstAndLastNameToPdfSheet(cv, pdDocument, page);
        pdDocument.save("test.pdf");
        pdDocument.close();
        return "PDF saved";
    }

}


