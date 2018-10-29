package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.PdfColor;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;
import static com.github.ativey.pdftodolist.pdf.PdfColor.DARK_KHAKI;

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

    @Autowired
    DbDrivenToDoListGenerator dbDrivenToDoListGenerator;

    @GetMapping("/")
    public String index(ModelMap modelMap) {

        Map<String, PdfColor> map = new HashMap<>();
        map.put("Home", SALMON);
        map.put("Home_Kitchen", RED);
        map.put("Home_Garden", INDIAN_RED);
        map.put("Home_FrontRoom", ORCHID);
        map.put("Home_Office", DARK_RED);
        map.put("Pdftodolist", DARK_ORANGE);
        map.put("GeneDB", ORANGE);
        map.put("Laptop", PERU);
        map.put("MainPC", GOLDENROD);
        map.put("Computing", SANDY_BROWN);
        map.put("Career", OLIVE);
        map.put("Personal", CHARTREUSE);
        map.put("Financial", DARK_GREEN);
        map.put("Car", YELLOW_GREEN);
        map.put("Media", MEDIUM_SLATE_BLUE);
        map.put("Dad", CYAN);
        map.put("Unsorted", DARK_KHAKI);


        var list = dbDrivenToDoListGenerator.generateToDoItemList(map, true, CompletedDisplayStrategy.END_OF_CATEGORY);
        modelMap.addAttribute("list", list);
        return "index";
    }


    // Task
    @GetMapping("/addTask")
    public String addTask(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryRepository.findAllByOrderByDisplayAsc());
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
