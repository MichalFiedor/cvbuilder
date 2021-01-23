package pl.michalfiedor.cvbuilder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.michalfiedor.cvbuilder.model.Cv;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final CvService cvService;

    public void showCvsList(Model model){
        List<Cv> cvs = cvService.findAll();
        for(Cv cv : cvs){
            cvService.deleteCvIfDoesNotFinished(cv);
        }
        cvs=cvService.findAll();
        cvService.passCvsListToView(cvs, model);
    }
}
