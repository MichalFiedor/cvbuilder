package pl.michalfiedor.cvbuilder.service;

import org.apache.pdfbox.contentstream.PDContentStream;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TextService {

    public static void addSingleText(PDPageContentStream pdPageContentStream, String text, float x, float y){
        try {
            pdPageContentStream.newLineAtOffset(x,y);
            pdPageContentStream.showText(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNewLineInMultilineText(PDPageContentStream pdPageContentStream, PDFont font, float fontSize,
                                  float leading, String text){
        try {
            pdPageContentStream.setFont(font, fontSize);
            pdPageContentStream.showText(text);
            pdPageContentStream.setLeading(leading);
            pdPageContentStream.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
