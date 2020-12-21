package pl.michalfiedor.cvbuilder.service;

import pl.michalfiedor.cvbuilder.model.City;

import java.util.List;

public interface CityService {
    List<City> getCities();
    City findById(long id);
}
