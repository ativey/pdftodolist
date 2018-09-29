package com.github.ativey.pdftodolist;

import java.util.List;
import java.util.Map;

class CategoryMapTestUtils {

    static int countTasksForCategory(Map<Category, List<Task>> categoryListMap, String categoryName) {
        Category test = new Category(categoryName);
        return categoryListMap.get(test).size();
    }

    static int countCategories(Map<Category, List<Task>> categoryListMap) {
        return categoryListMap.size();
    }

    static int countAllTasks(Map<Category, List<Task>> categoryListMap) {
        int ret = 0;
        for (var entry : categoryListMap.entrySet()) {
            ret += entry.getValue().size();
        }
        return ret;
    }



}
