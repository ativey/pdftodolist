package com.github.ativey.pdftodolist.pdf;

import java.util.Optional;

public class ToDoItem {
    String name;
    private boolean box;
    private boolean complete;
    private boolean important;
    private boolean checkBox;
    private Optional<String> boxText;

    public ToDoItem(boolean box, String boxText, boolean checkbox, String name, boolean complete, boolean important) {
        this.box = box;
        this.boxText = Optional.ofNullable(boxText);
        this.checkBox = checkbox;
        this.name = name;
        this.complete = complete;
        this.important = important;
    }


    public String getName() {
        return name;
    }

    public boolean isBox() {
        return box;
    }

    public Optional<String> getBoxText() {
        return boxText;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public boolean isComplete() {
        return complete;
    }

    public boolean isImportant() {
        return important;
    }
}
