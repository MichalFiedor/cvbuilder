package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.University;

import java.util.List;

public interface IUniversityService {

    List<University> findAllByCityId(long id);
}
