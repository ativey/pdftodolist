package com.github.ativey.pdftodolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@SpringBootApplication
public class PdfTodLlistApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PdfTodLlistApplication.class, args);
    }


    @Autowired
    YamlLoader yamlLoader;


    @Autowired
    YamlDbPersistence yamlDbPersistence;

    @Autowired
    PdfController pdfController;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var categoryListMap = yamlLoader.load(new File("/home/work/Desktop/main_tasks.yaml"));
        yamlDbPersistence.persist(categoryListMap);

        pdfController.generatePdf(true, CompletedDisplayStrategy.END_OF_CATEGORY);

    }


}
