package com.github.ativey.pdftodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.*;

@Component
public class YamlDbPersistence {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public void persist(Map<Category, List<Task>> categoryListMap) {
        for (var entry: categoryListMap.entrySet()) {
            Category category = entry.getKey();
            List<Task> tasks = entry.getValue();

            category = findOrPersistCategory(category);

            for (Task task : tasks) {
                findOrPersistTask(task, category);
            }
            long count = categoryRepository.count();
        }

    }

//    @Transactional
    public Category findOrPersistCategory(Category category) {
        Optional<Category> found = categoryRepository.findByName(category.getName());
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
        category.setDisplay(currentMax+1);
        //System.err.println("Persisting " + category.getName() + " : " + category.getDisplay());
        entityManager.persist(category);
        return category;
    }

    //    @Transactional
    public Task findOrPersistTask(Task task, Category category) {
        Optional<Task> found = taskRepository.findByName(task.getName());
        //System.err.print("Task name is " + task.getName());
        if (found.isPresent()) {
            //System.err.println("  : Found");
            return found.get();
        }

        //System.err.println("  : Not found. Need to update order");
        Task current = taskRepository.findFirstByOrderByDisplayDesc();
        //System.err.println((current==null) ? "null" : "Max display count is " + current.getDisplay());
        int currentMax = 0;
        if (current != null) {
            currentMax = current.getDisplay();
        }
        task.setDisplay(currentMax+1);
        task.setCategory(category);
        System.err.println("Persisting " + task.getName() + " : " + task.getDisplay());
        entityManager.persist(task);
        return task;
    }

}


