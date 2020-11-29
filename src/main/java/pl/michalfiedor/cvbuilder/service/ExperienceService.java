package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.Experience;
import pl.michalfiedor.cvbuilder.repository.ExperienceRepository;

@Service
@RequiredArgsConstructor
public class ExperienceService {
    private final ExperienceRepository experienceRepository;

    public Experience findById(long id) {
        return experienceRepository.findById(id).orElseThrow();
    }

    public void save(Experience experience) {
        experienceRepository.save(experience);
    }

    public void delete(Experience experience) {
        experienceRepository.delete(experience);
    }

    public boolean checkIfExist(long id) {
        return experienceRepository.existsById(id);
    }
}
