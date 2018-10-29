package com.github.ativey.pdftodolist;

public enum CompletedDisplayStrategy {

    NONE(false, false, false),
    IN_LINE(true, false, false),
    END_OF_CATEGORY(false, true, false),
    END_OF_LIST(false, false, true);

    final boolean displayCompletedInLine;
    final boolean displayCompletedAtEndOfCategory;
    final boolean displayCompletedAtEndOfList;

    private CompletedDisplayStrategy(boolean displayCompletedInLine, boolean displayCompletedAtEndOfCategory, boolean displayCompletedAtEndOfList) {
        this.displayCompletedInLine = displayCompletedInLine;
        this.displayCompletedAtEndOfCategory = displayCompletedAtEndOfCategory;
        this.displayCompletedAtEndOfList = displayCompletedAtEndOfList;
    }

}
