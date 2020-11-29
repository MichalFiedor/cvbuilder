package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.EducationDetails;
import pl.michalfiedor.cvbuilder.repository.EducationDetailsRepository;

@Service
@RequiredArgsConstructor
public class EducationDetailsService {
    private final EducationDetailsRepository educationDetailsRepository;

    public EducationDetails findById(long id){
        return educationDetailsRepository.findById(id).orElseThrow();
    }

    public void save (EducationDetails educationDetails){
        educationDetailsRepository.save(educationDetails);
    }

    public void delete(long id){
        educationDetailsRepository.deleteById(id);
    }
}
