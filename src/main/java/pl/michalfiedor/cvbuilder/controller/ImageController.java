package pl.michalfiedor.cvbuilder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {

    @GetMapping("/show")
    public String showImageForm(){
        return null;
    }
}
