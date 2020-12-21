package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.University;
import pl.michalfiedor.cvbuilder.repository.UniversityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public List<University> findAllByCityId(long id){
        return universityRepository.findAllByCityId(id);
    }

}
