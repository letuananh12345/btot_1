package controller;

import model.ClassRoom;
import model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.IClassRoomService;
import service.IStudentService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    IStudentService iStudentService;
    @Autowired
    IClassRoomService iClassRoomService;

    @ModelAttribute("classRooms")
    public ArrayList<ClassRoom> classRoomArrayList() {
        return iClassRoomService.findAll();
    }

    @GetMapping("/show")
    public ModelAndView showHome(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("page", iStudentService.findAll(PageRequest.of(page, 3, Sort.by("name"))));
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile fileUpload, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            return modelAndView;
        }

        String nameFile = fileUpload.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileUpload.getBytes(), new File("E:\\Module4\\btot_1\\src\\main\\webapp\\img\\" + nameFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        student.setAvatar("\\i\\" + nameFile);
        iStudentService.save(student);
        ModelAndView modelAndView = new ModelAndView("redirect:/show");
        return modelAndView;
    }
}



