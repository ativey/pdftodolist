package com.github.ativey.pdftodolist;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskid;

    @Size(min = 1, max = 40)
    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="categoryid")
    private Category category;

    private boolean important;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Task() {
        // Deliberately empty
    }

    public Task(@Size(min = 1, max = 40) String name, Category category, boolean important) {
        this.name = name;
        this.category = category;
        this.important = important;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
