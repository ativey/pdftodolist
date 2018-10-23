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
import java.util.HashMap;
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

        Map<String, PdfColor> map = new HashMap<>();
        map.put("Home", SALMON);
        map.put("Home_Kitchen", RED);
        map.put("Home_Garden", INDIAN_RED);
        map.put("Home_FrontRoom", ORCHID);
        map.put("Home_Office", DARK_RED);
        map.put("Pdftodolist", DARK_ORANGE);
        map.put("GeneDB", ORANGE);
        map.put("Laptop", PERU);
        map.put("MainPC", GOLDENROD);
        map.put("Computing", SANDY_BROWN);
        map.put("Career", OLIVE);
        map.put("Personal", CHARTREUSE);
        map.put("Financial", DARK_GREEN);
        map.put("Car", YELLOW_GREEN);
        map.put("Media", MEDIUM_SLATE_BLUE);
        map.put("Dad", CYAN);
        map.put("Unsorted", DARK_KHAKI);

        int taskCount = 0;

        var list = new ArrayList<Pair<PdfColor, ToDoItem>>();


        List<Category> categories = categoryRepository.findAllByOrderByDisplayAsc();

        for (Category category: categories) {
            var tasks = taskRepository.findAllByCategoryOrderByDisplayAsc(category);
            if ( showLabels && tasks.size() > 0 ) {
                list.add(Pair.of(map.getOrDefault(category.getName(), SILVER),
                        new ToDoItem(false, "", false, "  " + category.getName(), false, false)));
            }

            taskCount += tasks.size();
            for (Task task : tasks) {
                ToDoItem item = new ToDoItem(true, "", true, " " + task.getName(), false, false);
                list.add(Pair.of(map.getOrDefault(task.getCategory().getName(), REBECCA_PURPLE), item));
            }
        }

        System.err.println("The number of tasks is " + taskCount);

        toDoList.createFonts();
        PdfDocument pdfDocument = toDoList.setup();

        toDoList.drawFromList(pdfDocument, list);
        pdfDocument.close();


        return "index";
    }

}
