package pl.michalfiedor.cvbuilder.service;

import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.model.Experience;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class DateServiceForEducation {

    public boolean isStartBeforeEndDate(EducationDetails educationDetails){
        YearMonth start;
        if(educationDetails.getStart().length()==0){
            return false;
        } else {
            start= parseStringToYearMonth(educationDetails.getStart());
        }
        YearMonth end;
        if(educationDetails.getEnd().length()==0 || educationDetails.getEnd().equals("Still")){
            return true;
        } else {
             end = parseStringToYearMonth(educationDetails.getEnd());
        }
        if(start.isBefore(end)){
            return true;
        }
        return false;
    }

    private YearMonth parseStringToYearMonth(String date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
        return YearMonth.parse(date, dtf);
    }
}
