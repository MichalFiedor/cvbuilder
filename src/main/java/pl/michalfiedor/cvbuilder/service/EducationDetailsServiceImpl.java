package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.*;
import pl.michalfiedor.cvbuilder.repository.EducationDetailsRepository;
import pl.michalfiedor.cvbuilder.validationGroup.EducationDetailValidationGroup;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EducationDetailsServiceImpl implements EducationDetailsService {
    private final EducationDetailsRepository educationDetailsRepository;
    private final CvService cvService;
    private final Validator validator;
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

    public void getEducationList(HttpSession session, Model model){
        Cv userCv = cvService.getCvById(cvService.getCvIdFromSession(session));
        model.addAttribute("educationList", userCv.getEducationDetailsList());
    }

    @Override
    public void setEndDateAsAStill(EducationDetails educationDetails) {
        if(educationDetails.getEnd().length()==0){
            educationDetails.setEnd("Still");
        }
    }
}
