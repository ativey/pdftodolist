package com.github.ativey.pdftodolist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByNameAndCategory(String name, Category category);

    List<Task> findByName(String name);

    int countTasksByNameEquals(String name);

    List<Task> findByCategory(Category category);

    Task findFirstByOrderByDisplayDesc();

    List<Task> findAllByCategoryOrderByDisplayAsc(Category category);

    //List<Category> findAllByCategoryByOrderByDisplayDesc(Category category);

    //List<Task> findByCategoryOrderByDisplayAsc(Category category);

}
