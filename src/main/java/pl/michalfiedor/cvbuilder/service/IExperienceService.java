package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Experience;

public interface IExperienceService {

    Experience findById(long id);

    void save(Experience experience);

    void delete(Experience experience);

    boolean checkIfExist(long id);
}
