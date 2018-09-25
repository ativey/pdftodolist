package com.github.ativey.pdftodolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Clock;

@Controller
class MainTaskController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


    // Task
    @GetMapping("/addTask")
    public String addTask() {
        return "addTask";
    }

    @PostMapping("/addTask")
    public String addCategory(String taskName, Category category) {
        return index();
    }

    @GetMapping("/editTask")
    public String editTask(String taskName) {
        return "editTask";
    }

    @PostMapping("/editTask")
    public String editTask(String taskName, String newCategoryName) {
        return index();
    }


    @GetMapping("/importTasks")
    public String importTask() {
        return "importTask";
    }

    @PostMapping("/importTasks")
    public String importTasks() {
        return index();
    }

}
