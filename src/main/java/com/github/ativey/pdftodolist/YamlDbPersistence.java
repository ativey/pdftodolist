package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.ToDoItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Component
public class YamlDbPersistence {

    private static final Logger logger = LoggerFactory.getLogger(YamlDbPersistence.class);


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public void persist(LinkedHashMap<String, List<ToDoItem>> categoryListMap) {
        for (var entry: categoryListMap.entrySet()) {
            String categoryName = entry.getKey();
            Category category = findOrPersistCategory(categoryName);

            List<ToDoItem> todoItems = entry.getValue();

            for (ToDoItem toDoItem : todoItems) {
                findOrPersistTask(toDoItem, category);
            }
            long count = categoryRepository.count();
        }

    }


    public Category findOrPersistCategory(String categoryName) {
        Optional<Category> found = categoryRepository.findByName(categoryName);
        //System.err.print("Category name is " + category.getName());
        if (found.isPresent()) {
            //System.err.println("  : Found");
            return found.get();
        }

        //System.err.println("  : Not found. Need to update order");
        Category current = categoryRepository.findFirstByOrderByDisplayDesc();
        //System.err.println((current==null) ? "null" : "Max display count is " + current.getDisplay());
        int currentMax = 0;
        if (current != null) {
            currentMax = current.getDisplay();
        }
        Category category = new Category(categoryName, currentMax +1);
        //System.err.println("Persisting " + category.getName() + " : " + category.getDisplay());
        entityManager.persist(category);
        return category;
    }


    public Task findOrPersistTask(ToDoItem toDoItem, Category category) {
        Optional<Task> found = taskRepository.findByNameAndCategory(toDoItem.getName(), category);
        if (found.isPresent()) {
            return found.get();
        }


        if (taskRepository.countTasksByNameEquals(toDoItem.getName()) > 0) {
            logger.warn("Storing task '%s' in category '%s'. Note this task name is used in other categories",
                            toDoItem.getName(), category.getName());
        }

        // Not found. Need to update order
        Task current = taskRepository.findFirstByOrderByDisplayDesc();
        int currentMax = 0;
        if (current != null) {
            currentMax = current.getDisplay();
        }
        Task task = new Task(toDoItem.getName(), category, toDoItem.isImportant(), toDoItem.isComplete(), currentMax + 1);
        logger.debug("Persisting %s%s%s : %d",
                (toDoItem.isImportant() ? "=" : ""), (toDoItem.isComplete() ? "^" : ""), task.getName(), task.getDisplay());
        entityManager.persist(task);
        return task;
    }

}


