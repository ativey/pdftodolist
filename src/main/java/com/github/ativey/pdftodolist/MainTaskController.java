package com.github.ativey.pdftodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.Clock;
import java.util.List;

@Controller
class MainTaskController {

    @Autowired
    private YamlLoader yamlLoader;

    @Autowired
    private YamlDbPersistence yamlDbPersistence;

    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        List<Task> tasks = taskRepository.findAll();
        modelMap.addAttribute("tasks", tasks);
        return "index";
    }


    // Task
    @GetMapping("/addTask")
    public String addTask() {
        return "addTask";
    }

    @PostMapping("/addTask")
    public String addCategory(String taskName, Category category) {
        return "redirect:/";
    }

    @GetMapping("/editTask")
    public String editTask(String taskName) {
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(String taskName, String newCategoryName) {
        return "redirect:/";
    }


    @GetMapping("/importTasks")
    public String importTask() {
        return "importTasks";
    }

    @PostMapping("/importTasks")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            var categoryListMap = yamlLoader.load(file.getInputStream());
            yamlDbPersistence.persist(categoryListMap);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

}
