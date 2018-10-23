package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.PdfColor;
import com.github.ativey.pdftodolist.pdf.ToDoItem;
import com.github.ativey.pdftodolist.pdf.ToDoList;
import com.itextpdf.kernel.pdf.PdfDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;

@Controller
class PdfController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;


    @GetMapping("/generatePdf")
    public String generatePdf(String taskName, String newCategoryName) {
        return "generatePdf";
    }

    @PostMapping("/generatePdf")
    public String generatePdf(String outputFile) throws IOException {
        boolean showLabels = true;
        System.err.println("Generate PDF");

        ToDoList toDoList = new ToDoList("/home/work/Desktop/todo.pdf");

        Map<String, PdfColor> map = Map.of("CategoryA", INDIAN_RED, "CategoryB", LIME_GREEN,
                "CategoryC", DARK_VIOLET, "CategoryD", MEDIUM_VIOLET_RED,
                "CategoryE", CHOCOLATE, "CategoryF", DARK_ORANGE,
                "CategoryG", MIDNIGHT_BLUE);
        int taskCount = 0;

        var list = new ArrayList<Pair<PdfColor, ToDoItem>>();


        List<Category> categories = categoryRepository.findAllByOrderByDisplayAsc();

        for (Category category: categories) {
            if (showLabels) {
                list.add(Pair.of(map.getOrDefault(category.getName(), REBECCA_PURPLE),
                        new ToDoItem(false, "", false, category.getName(), false, false)));
            }
            //var tasks = taskRepository.findByCategory(category);
            var tasks = taskRepository.findAllByCategoryOrderByDisplayAsc(category);
            taskCount += tasks.size();
            for (Task task : tasks) {
                ToDoItem item = new ToDoItem(true, "", true, task.getName(), false, false);
                list.add(Pair.of(map.getOrDefault(task.getCategory().getName(), REBECCA_PURPLE), item));
            }
        }

        System.err.println("The number of tasks is " + taskCount);

        PdfDocument pdfDocument = toDoList.setup();

        toDoList.drawFromList(pdfDocument, list);
        pdfDocument.close();


        return "index";
    }

}
