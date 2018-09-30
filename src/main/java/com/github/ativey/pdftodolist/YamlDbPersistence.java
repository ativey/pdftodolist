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

            //Category category2 = category;
            Optional<Category> found = categoryRepository.findByName(category.getName());
            //Category category2 = found.g
            if (found.isPresent()) {
                category = found.get();
            } else {
                entityManager.persist(category);
            }

            for (Task task : tasks) {
                Optional<Task> foundTask = taskRepository.findByName(task.getName());
                if (foundTask.isEmpty()) {
                    task.setCategory(category);
                    entityManager.persist(task);
                }
            }
            long count = categoryRepository.count();

        }

    }


}
