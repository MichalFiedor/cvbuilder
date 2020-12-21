package pl.michalfiedor.cvbuilder.service;

import lombok.Getter;
import lombok.Setter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;

import java.io.IOException;
import java.util.List;

@Setter
@Getter
@Service
public class PdfServiceImpl implements PdfService{
      PDDocument pdDocument;

    public static class Builder{
        private PDDocument pdDocument;
        private Cv cv;
        private PDPage page;

        public Builder(PDDocument pdDocument, Cv cv, PDPage page){
            this.pdDocument=pdDocument;
            this.cv=cv;
            this.page=page;
        }

        public Builder addAboutMeToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);
            String lines[] = StringSplitter.splitStringByLine(this.cv.getAboutMe(), 50);
            try {
                contentStream.beginText();
                contentStream.newLineAtOffset(56.7f, 610f);
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 8);
                contentStream.setLeading(14.5f);
                for (int i = 0; i < lines.length; i++) {
                    contentStream.showText(lines[i]);
                    contentStream.newLine();
                }
                contentStream.endText();
                contentStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public Builder addBasicDataToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);

            String city = this.cv.getCity().getName();
            String email = this.cv.getEmail();
            String phoneNumber = this.cv.getPhoneNumber();
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
            return this;
        }

        public Builder addFirstAndLastNameToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);

            String firstName = this.cv.getFirstName();
            String lastName = this.cv.getLastName();
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
            return this;
        }

        public Builder addExperienceToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);
            List<Experience> experiences = this.cv.getExperiences();

            try {
                contentStream.beginText();
                contentStream.newLineAtOffset(245.8f, 610f);
                for (Experience experience : experiences) {

                    String lines[] = StringSplitter.splitStringByLine(experience.getDescription(), 85);
                    String workingPeriod = experience.getStart() + " - " + experience.getEnd();
                    PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD, 11f, 10.5f,
                            experience.getPosition());
                    PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_BOLD_ITALIC, 9f, 10.5f,
                            experience.getCompanyName());
                    PdfTextService.addNewLineInMultilineText(contentStream, PDType1Font.TIMES_ROMAN, 7f, 12.5f,
                            workingPeriod);
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
            return this;
        }

        public Builder addEducationToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);
            List<EducationDetails> educationDetailsList = cv.getEducationDetailsList();

            try {
                contentStream.beginText();
                contentStream.newLineAtOffset(245.8f, 415f);
                for (EducationDetails educationDetails : educationDetailsList) {
                    String lines[] = StringSplitter.splitStringByLine(educationDetails.getDegree(), 85);
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
            return this;
        }

        public Builder addPhotoToPdfSheet() {

            PDPageContentStream contentStream = getContentStream(this.pdDocument, this.page);
            String imgPath = this.cv.getImagePath();
            try {
                PDImageXObject pdImageXObject = PDImageXObject.createFromFile(imgPath, this.pdDocument);
                float widthFactor = pdImageXObject.getWidth() / 70f;
                float heightFactor = pdImageXObject.getHeight() / 90f;
                contentStream.drawImage(pdImageXObject, 258, 735,
                        pdImageXObject.getWidth() / widthFactor,
                        pdImageXObject.getHeight() / heightFactor);
                contentStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this;
        }

        public PdfServiceImpl build(){
            PdfServiceImpl pdfService = new PdfServiceImpl();
            pdfService.setPdDocument(this.pdDocument);
            return pdfService;
        }

        private static PDPageContentStream getContentStream(PDDocument pdDocument, PDPage page) {
            try {
                return new PDPageContentStream(pdDocument, page,
                        PDPageContentStream.AppendMode.APPEND, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
