package pl.michalfiedor.cvbuilder.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.davidmoten.text.utils.WordWrap;
import org.springframework.stereotype.Component;
import org.w3c.dom.Text;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;

import java.io.IOException;
import java.util.List;

@Component
public class PdfService {

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
        String lines[]= TextService.splitStringByLine(cv.getAboutMe(), 50);
        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(56.7f, 610f);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
            contentStream.setLeading(14.5f);
            for (int i = 0; i < lines.length; i ++) {
                contentStream.showText(lines[i]);
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
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 9);
            PdfTextService.addSingleText(contentStream, phoneNumber, 107.2f, 688.5f);
            PdfTextService.addSingleText(contentStream, email, 135.2f, 0);
            PdfTextService.addSingleText(contentStream, city, 201.5f, 0);
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
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 25);
            PdfTextService.addSingleText(contentStream, firstName, 220.1f, 712.4f);
            PdfTextService.addSingleText(contentStream, lastName, 80.3f, 0);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addExperienceToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);
        List<Experience> experiences = cv.getExperiences();

        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(245.8f, 610f);
            for (Experience experience : experiences) {

                String lines[] = TextService.splitStringByLine(experience.getDescription(), 85);
                String workingPeriod = experience.getStart() + " - " + experience.getEnd();
                PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD, 11f, 10.5f,
                        experience.getPosition());
                PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD_ITALIC, 9f, 10.5f,
                        experience.getCompanyName());
                PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_ROMAN, 7f, 12.5f,
                        workingPeriod);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);

                for (int i = 0; i < lines.length; i ++) {
                    contentStream.setLeading(10.5f);
                    contentStream.showText(lines[i]);
                    contentStream.newLine();
                }
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEducationToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);
        List<EducationDetails> educationDetailsList = cv.getEducationDetailsList();

        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(245.8f, 415f);
            for (EducationDetails educationDetails : educationDetailsList) {
                String lines[]=TextService.splitStringByLine(educationDetails.getDegree(), 85);
                String studyPeriod = educationDetails.getStart() + " - " + educationDetails.getEnd();
                PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD, 11f, 10.5f,
                        educationDetails.getUniversity().getName());
                PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_ROMAN, 7f, 12.5f,
                        studyPeriod);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
                for (int i = 0; i < lines.length; i++) {
                    contentStream.setLeading(10.5f);
                    contentStream.showText(lines[i]);
                    contentStream.newLine();
                }
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPhotoToPdfSheet(Cv cv, PDDocument pdDocument, PDPage page) {

        PDPageContentStream contentStream = getContentStream(pdDocument, page);
        String imgPath = cv.getImagePath();
        try {
            PDImageXObject pdImageXObject = PDImageXObject.createFromFile(imgPath, pdDocument);
            float widthFactor = pdImageXObject.getWidth()/70f;
            float heightFactor = pdImageXObject.getHeight()/90f;
                    contentStream.drawImage(pdImageXObject, 258, 735,
                            pdImageXObject.getWidth()/widthFactor  ,
                            pdImageXObject.getHeight()/heightFactor );
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
