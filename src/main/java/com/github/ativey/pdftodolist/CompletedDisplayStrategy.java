package com.github.ativey.pdftodolist;

public enum CompletedDisplayStrategy {

    NONE(false, false, false),
    IN_LINE(true, false, false),
    END_OF_CATEGORY(false, true, false),
    END_OF_LIST(false, false, true);

    public final boolean displayCompletedInLine;
    public final boolean displayCompletedAtEndOfCategory;
    public final boolean displayCompletedAtEndOfList;

    private CompletedDisplayStrategy(boolean displayCompletedInLine, boolean displayCompletedAtEndOfCategory, boolean displayCompletedAtEndOfList) {
        this.displayCompletedInLine = displayCompletedInLine;
        this.displayCompletedAtEndOfCategory = displayCompletedAtEndOfCategory;
        this.displayCompletedAtEndOfList = displayCompletedAtEndOfList;
    }

}
