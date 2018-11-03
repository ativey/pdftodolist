package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.PdfColor;
import com.github.ativey.pdftodolist.pdf.ToDoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.ativey.pdftodolist.pdf.PdfColor.REBECCA_PURPLE;
import static com.github.ativey.pdftodolist.pdf.PdfColor.SILVER;

@Component
class DbDrivenToDoListGenerator {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    //@Transactional
    public ArrayList<Pair<PdfColor, ToDoItem>> generateToDoItemList(Map<String, PdfColor> map, boolean showLabels, CompletedDisplayStrategy completedDisplayStrategy) {

        var ret = new ArrayList<Pair<PdfColor, ToDoItem>>();

        List<Category> categories = categoryRepository.findAllByOrderByDisplayAsc();

        for (Category category : categories) {

            List<Task> tasks;

            if (completedDisplayStrategy.displayCompletedInLine) {
                tasks = taskRepository.findAllByCategoryOrderByDisplayAsc(category);
            } else {
                tasks = taskRepository.findTasksByCategoryAndCompleteOrderByDisplayAsc(category, false);
            }

            if (showLabels && tasks.size() > 0) {
                ret.add(Pair.of(map.getOrDefault(category.getName(), SILVER),
                        new ToDoItem(false, "", false, "  " + category.getName(), false, false)));
            }

            addToDoToList(ret, tasks, map);

            if (completedDisplayStrategy.displayCompletedAtEndOfCategory) {
                tasks = taskRepository.findTasksByCategoryAndCompleteOrderByDisplayAsc(category, true);
                addToDoToList(ret, tasks, map);
            }
        }

        if (completedDisplayStrategy.displayCompletedAtEndOfList) {
            if (showLabels) {
                ret.add(Pair.of(map.getOrDefault("Completed", SILVER),
                        new ToDoItem(false, "", false, "  Completed", false, false)));
            }
            for (Category category : categories) {
                var tasks = taskRepository.findTasksByCategoryAndCompleteOrderByDisplayAsc(category, true);
                addToDoToList(ret, tasks, map);

            }
        }

        return ret;
    }


    public void addToDoToList(ArrayList<Pair<PdfColor, ToDoItem>> ret, List<Task> tasks, Map<String, PdfColor> map) {
        for (Task task : tasks) {
            ToDoItem item = new ToDoItem(true, (task.isImportant() ? "  *" : ""), true, " " + task.getName(), task.isComplete(), task.isImportant());
            ret.add(Pair.of(map.getOrDefault(task.getCategory().getName(), REBECCA_PURPLE), item));
        }
    }


}
