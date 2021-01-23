package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.michalfiedor.cvbuilder.exception.InvalidFileExtensionException;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.ImageData;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.service.*;
import pl.michalfiedor.cvbuilder.service.UserService;
import pl.michalfiedor.cvbuilder.validator.FileValidator;


import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Set;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final UserService userService;
    private final ImageService imageService;
    private final Validator validator;
    private final CvService cvService;
    private final FileValidator fileValidator;

    @GetMapping("/show")
    public String showImageForm(Model model) {
        model.addAttribute("cv", new Cv());
        return "imageForm";
    }

    @PostMapping("/add")
    public String addImage(HttpSession session, MultipartFile image,
                           Model model, Principal principal) throws IOException {
        try {
            fileValidator.validateExtension(image);
        } catch (InvalidFileExtensionException e) {
            model.addAttribute("validationMessage", "Only jpg/jpeg and png files are accepted");
            return "imageForm";
        }
        Set<ConstraintViolation<MultipartFile>> violations = validator.validate(image);
        if(!violations.isEmpty()){
            return "imageForm";
        }
        User user = userService.get(principal.getName());
        ImageData imageData = imageService.createImageData(user, image);
        Cv cv = cvService.getById(cvService.getCvIdFromSession(session));
        imageService.saveImagePathInCv(imageData, cv);
        imageService.saveImageFile(imageData, image);
        model.addAttribute("imgName", image.getOriginalFilename());
        model.addAttribute("imgPath", cv.getImagePath());
        return "imageForm";
    }
}
