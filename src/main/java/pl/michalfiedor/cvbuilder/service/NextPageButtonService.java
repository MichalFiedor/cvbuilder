package pl.michalfiedor.cvbuilder.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class NextPageButtonService {
    public void showNextPageButtonOnPage(HttpSession session, Model model, String buttonName){
        if(session.getAttribute(buttonName)!=null){
            model.addAttribute(buttonName, true);
        }
    }
}
