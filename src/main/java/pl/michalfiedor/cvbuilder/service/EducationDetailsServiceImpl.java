package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.repository.EducationDetailsRepository;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class EducationDetailsServiceImpl implements EducationDetailsService {
    private final EducationDetailsRepository educationDetailsRepository;
    private final CvService cvService;

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
}
