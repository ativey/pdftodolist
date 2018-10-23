package com.github.ativey.pdftodolist;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;

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

    private boolean closed = false;

    @Column(unique = true)
    private int display;

    @DateTimeFormat(pattern = "YYYYMMDdd:HHmmss")
    private Instant creationDate;

    @DateTimeFormat(pattern = "YYYYMMDdd:HHmmss")
    @Nullable
    private Instant closeDate;




    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Task(@Size(min = 1, max = 80) String name, Category category, boolean important, int display) {
        this.name = name;
        this.category = category;
        this.important = important;
        this.display = display;
        this.creationDate = Instant.now();
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

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getDisplay() {
        return display;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

//    public void setCreationDate(Instant creationDate) {
//        this.creationDate = creationDate;
//    }
}
