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

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;

@Controller
class PdfController {

    @Autowired
    private DbDrivenToDoListGenerator dbDrivenToDoListGenerator;


    @Autowired
    private ToDoList toDoList;



    @GetMapping("/generatePdf")
    public String generatePdf(String taskName, String newCategoryName) {
        return "generatePdf";
    }

    @PostMapping("/generatePdf")
    public String generatePdf(boolean showLabels, Optional<CompletedDisplayStrategy> strategy) throws IOException {
        System.err.println("showLabels is '"+showLabels+"'");
        return generatePdf(showLabels, strategy.orElse(CompletedDisplayStrategy.END_OF_LIST));
    }



    public String generatePdf(boolean showLabels, CompletedDisplayStrategy completedDisplayStrategy) throws IOException {
        System.err.println("Generate PDF");

//        ToDoList toDoList = new ToDoList("/home/work/Desktop/todo.pdf");


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

        ArrayList<Pair<PdfColor, ToDoItem>> list = dbDrivenToDoListGenerator.generateToDoItemList(map, showLabels, completedDisplayStrategy);

        toDoList.createFonts();
        toDoList.setDestination("/home/work/Desktop/todo.pdf");
        PdfDocument pdfDocument = toDoList.setup();


        toDoList.drawFromList(pdfDocument, list);
        pdfDocument.close();


        return "index";
    }

}
