package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michalfiedor.cvbuilder.model.City;
import pl.michalfiedor.cvbuilder.repository.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public List<City> get(){
        return cityRepository.findAll();
    }

    public City findById(long id){
        return cityRepository.findById(id).orElseThrow();
    }
}
