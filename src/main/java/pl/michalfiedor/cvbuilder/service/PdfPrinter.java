package pl.michalfiedor.cvbuilder.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;
import pl.michalfiedor.cvbuilder.model.Cv;

import java.io.IOException;

@Component
public class PdfPrinter {

    public static PDPageContentStream getContentStream(PDDocument pdDocument, PDPage page) {
        try {
            return new PDPageContentStream(pdDocument, page,
                    PDPageContentStream.AppendMode.APPEND, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addAboutMeToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);

        String aboutMe = cv.getAboutMe();
        aboutMe = aboutMe.replace("\n", "").replace("\r", "");
        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(56.7f, 600f);
            contentStream.setFont(PDType1Font.HELVETICA, 8);
            contentStream.setLeading(14.5f);
            for (int i = 0; i < aboutMe.length(); i += 40) {
                contentStream.showText(StringUtils.truncate(aboutMe, i, 40));
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBasicDataToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);

        String city = cv.getCity().getName();
        String email = cv.getEmail();
        String phoneNumber = cv.getPhoneNumber();
        try {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 9);
            contentStream.newLineAtOffset(107.2f, 688.5f);
            contentStream.showText(phoneNumber);
            contentStream.newLineAtOffset(135.2f, 0);
            contentStream.showText(email);
            contentStream.newLineAtOffset(201.5f, 0);
            contentStream.showText(city);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFirstAndLastNameToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);

        String firstName = cv.getFirstName();
        String lastName = cv.getLastName();
        try {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 25);
            contentStream.newLineAtOffset(220.1f, 712.4f);
            contentStream.showText(firstName);
            contentStream.newLineAtOffset(80.3f, 0);
            contentStream.showText(lastName);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
