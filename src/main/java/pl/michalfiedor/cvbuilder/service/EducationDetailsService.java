package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.repository.EducationDetailsRepository;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationDetailsService  {
    private final EducationDetailsRepository educationDetailsRepository;
    private final CvService cvService;
    private final UniversityService universityService;
    private final CityService cityService;

    public EducationDetails findById(long id){
        return educationDetailsRepository.findById(id).orElseThrow();
    }

    public void save (EducationDetails educationDetails){
        educationDetailsRepository.save(educationDetails);
    }

    public void delete(long id){
        educationDetailsRepository.deleteById(id);
    }

    public void getEducationsList(HttpSession session, Model model){
        Cv userCv = cvService.getById(cvService.getCvIdFromSession(session));
        model.addAttribute("educationList", userCv.getEducationDetailsList());
    }

    public void setEndDateAsAStill(EducationDetails educationDetails) {
        if(educationDetails.getEnd().length()==0){
            educationDetails.setEnd("Still");
        }
    }

    public void passUniversitiesListForChosenCity(HttpSession session, long cityId, Model model, Boolean passEducationDetailsObjectToView){
        session.removeAttribute("showNextButton");
        List<University> universitiesList = universityService.findAllByCityId(cityId);
        model.addAttribute("universitiesPerCity", universitiesList);
        if(passEducationDetailsObjectToView){
            model.addAttribute("educationDetails", new EducationDetails());
        }
        model.addAttribute("selectedCity", cityService.findById(cityId));
        getEducationsList(session, model);
    }

    public void addEducationToCv(HttpSession session, EducationDetails educationDetails){
        Cv userCv = cvService.getById(cvService.getCvIdFromSession(session));
        setEndDateAsAStill(educationDetails);
        save(educationDetails);
        userCv.addEducationDetailToCollection(educationDetails);
        cvService.save(userCv);
    }
}
