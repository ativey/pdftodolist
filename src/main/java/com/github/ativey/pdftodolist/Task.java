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

}
