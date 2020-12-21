package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.Experience;

public interface DateService {

    boolean isStartBeforeEndDate(Experience experience);
}
