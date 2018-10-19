package com.github.ativey.pdftodolist;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryid;

    @Size(min = 1, max = 20)
    private String name;

    @Column(unique = true)
    private int display;

    public Category() {
        // Deliberately empty
    }

    public Category(String name, int display) {
        this.name = name;
        this.display = display;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getDisplay() {
        return  display;
    }
}
