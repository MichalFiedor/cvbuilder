package pl.michalfiedor.cvbuilder.service;

import org.davidmoten.text.utils.WordWrap;
import org.springframework.stereotype.Service;

@Service
public class TextService {

    public static String [] splitStringByLine(String text, int length){
        String wrappedText = WordWrap.from(text)
                .maxWidth(length)
                .wrap();
        return wrappedText.split(System.getProperty("line.separator"));
    }
}
