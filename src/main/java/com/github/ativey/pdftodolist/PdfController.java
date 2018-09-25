package com.github.ativey.pdftodolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class PdfController {

    @GetMapping("/generatePdf")
    public String generatePdf(String taskName, String newCategoryName) {
        return "generatePdf";
    }

    @PostMapping("/generatePdf")
    public String generatePdf(String outputFile) {
        System.err.println("Generate PDF");
        return "index";
    }

}
