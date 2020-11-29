package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.service.CvService;
import pl.michalfiedor.cvbuilder.service.ImageService;
import pl.michalfiedor.cvbuilder.service.UserService;


import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Pattern;
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

    @GetMapping("/show")
    public String showImageForm(Model model) {
        model.addAttribute("cv", new Cv());
        return "imageForm";
    }

    @PostMapping("/add")
    public String addImage(HttpSession session, MultipartFile image,
                           Model model, Principal principal) throws IOException {
        User user = userService.getUser(principal.getName());
        Set<ConstraintViolation<MultipartFile>> violations = validator.validate(image);
        if(!violations.isEmpty()){
            return "imageForm";
        }
        Cv cv = cvService.getCvById(cvService.getCvIdFromSession(session));
        String fileName = "userPhoto_id_" + user.getId();
        String uploadDir = "user_id_" + user.getId();
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        String imgPath = uploadDir + "/" + fileName + "." + extension;
        Path filePath = Paths.get(imgPath);
        cv.setImagePath(filePath.toAbsolutePath().toString());
        cvService.save(cv);
        imageService.saveImageFile(uploadDir, fileName, image, extension);
        model.addAttribute("imgName", image.getOriginalFilename());
        model.addAttribute("imgPath", cv.getImagePath());
        return "imageForm";
    }
}
