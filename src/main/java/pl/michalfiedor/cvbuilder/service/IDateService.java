package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Experience;

public interface IDateService {

    boolean isStartBeforeEndDate(Experience experience);
}
