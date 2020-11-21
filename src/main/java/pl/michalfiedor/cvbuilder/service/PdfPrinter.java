package pl.michalfiedor.cvbuilder.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Component;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;

import java.io.IOException;
import java.util.List;

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
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
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
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 9);
            TextService.addSingleText(contentStream, phoneNumber, 107.2f, 688.5f);
            TextService.addSingleText(contentStream, email, 135.2f, 0);
            TextService.addSingleText(contentStream, city, 201.5f, 0);
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
            TextService.addSingleText(contentStream, firstName, 220.1f, 712.4f);
            TextService.addSingleText(contentStream, lastName, 80.3f, 0);
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
            contentStream.newLineAtOffset(245.8f, 600f);
            for (Experience experience : experiences) {
                String experienceDescription = experience.getDescription();
                experienceDescription = experienceDescription.replace("\n", "").replace("\r", "");
                String workingPeriod = experience.getStart() + " - " + experience.getEnd();
                TextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD, 11f, 10.5f,
                        experience.getPosition());
                TextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD_ITALIC, 9f, 10.5f,
                        experience.getCompanyName());
                TextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_ROMAN, 7f, 14.5f,
                        workingPeriod);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);

                for (int i = 0; i < experienceDescription.length(); i += 70) {
                    contentStream.setLeading(10.5f);
                    contentStream.showText(StringUtils.truncate(experienceDescription, i, 70));
                    contentStream.newLine();
                }
                contentStream.setLeading(14.5f);
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
                String degree = educationDetails.getDegree();
                degree = degree.replace("\n", "").replace("\r", "");
                String studyPeriod = educationDetails.getStart() + " - " + educationDetails.getEnd();
                TextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD, 11f, 10.5f,
                        educationDetails.getUniversity().getName());
                TextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_ROMAN, 7f, 14.5f,
                        studyPeriod);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
                for (int i = 0; i < degree.length(); i += 70) {
                    contentStream.setLeading(10.5f);
                    contentStream.showText(StringUtils.truncate(degree, i, 70));
                    contentStream.newLine();
                }
                contentStream.setLeading(14.5f);
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
