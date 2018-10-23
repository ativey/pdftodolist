package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.ToDoItem;

import java.util.List;
import java.util.Map;

class CategoryMapTestUtils {

    static int countTasksForCategoryName(Map<String, List<ToDoItem>> categoryListMap, String categoryName) {
        return categoryListMap.get(categoryName).size();
    }

    static int countCategoryNames(Map<String, List<ToDoItem>> categoryListMap) {
        return categoryListMap.size();
    }

    static int countAllTodoItems(Map<String, List<ToDoItem>> categoryListMap) {
        int ret = 0;
        for (var entry : categoryListMap.entrySet()) {
            ret += entry.getValue().size();
        }
        return ret;
    }



}
