package pl.michalfiedor.cvbuilder.service;

import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Experience;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class DateService implements IDateService {

    public boolean isStartBeforeEndDate(Experience experience){
        YearMonth start = parseStringToYearMonth(experience.getStart());
        YearMonth end;
        if(experience.getEnd().length()==0 || experience.getEnd().equals("Still")){
            return true;
        } else {
             end = parseStringToYearMonth(experience.getEnd());
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
