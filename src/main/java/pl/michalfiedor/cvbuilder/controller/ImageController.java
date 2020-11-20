package pl.michalfiedor.cvbuilder.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.michalfiedor.cvbuilder.model.Cv;
import pl.michalfiedor.cvbuilder.model.User;
import pl.michalfiedor.cvbuilder.repository.CvRepository;
import pl.michalfiedor.cvbuilder.repository.UserRepository;
import pl.michalfiedor.cvbuilder.service.ImageService;
import pl.michalfiedor.cvbuilder.service.UserGetter;


import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final CvRepository cvRepository;

    @GetMapping("/show")
    public String showImageForm(Model model) {
        model.addAttribute("cv", new Cv());
        return "imageForm";
    }

    @PostMapping("/add")
    public String addImage(HttpSession session, @RequestParam MultipartFile image, Model model) throws IOException {
        User user = UserGetter.getUserFromSession(session, userRepository);
        Cv cv = user.getCv();
        String fileName = "userPhoto_id_" + user.getId();
        String uploadDir = "user_id_" + user.getId();
        String extension = FilenameUtils.getExtension(image.getOriginalFilename());
        String imgPath = uploadDir + "/" + fileName + "." + extension;
        Path filePath = Paths.get(imgPath);
        cv.setImagePath(filePath.toAbsolutePath().toString());
        cvRepository.save(cv);
        imageService.saveImageFile(uploadDir, fileName, image, extension);
        model.addAttribute("imgName", image.getOriginalFilename());
        model.addAttribute("imgPath", cv.getImagePath());
        return "imageForm";
    }
}
