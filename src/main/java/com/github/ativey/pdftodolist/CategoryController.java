package com.github.ativey.pdftodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Category
    @GetMapping("/category")
    public String listCategory(ModelMap modelMap) {
        List<Category> categories = categoryRepository.findAllByOrderByDisplayDesc();
        modelMap.addAttribute("categories", categories);
        return "category";
    }

    @GetMapping("/addCategory")
    public String addCategory() {
        return "addCategory";
    }

    @PostMapping("/addCategory")
    public String addCategory(String newCategory) {
        return "redirect:category";
    }

    @GetMapping("/renameCategory")
    public String renameCategory(String oldCategoryName) {
        return "renameCategory";
    }

    @PostMapping("/renameCategory")
    public String renameCategory(String oldCategoryName, String newCategoryName) {
        return "redirect:category";
    }

}
