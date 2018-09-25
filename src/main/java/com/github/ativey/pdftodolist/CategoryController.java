package com.github.ativey.pdftodolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class CategoryController {

    // Category
    @GetMapping("/category")
    public String listCategory() {
        return "category";
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(String newCategory) {
        return listCategory();
    }

    @GetMapping("/renameCategory")
    public String renameCategory(String oldCategoryName) {
        return "renameCategory";
    }

    @PostMapping("/renameCategory")
    public String renameCategory(String oldCategoryName, String newCategoryName) {
        return listCategory();
    }

}
