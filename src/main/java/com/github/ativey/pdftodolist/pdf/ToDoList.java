package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import java.io.FileNotFoundException;

public class ToDoList {

    //public static final String DEST = "./target/test/resources/sandbox/objects/grid.pdf";

    public static final String DEST = "/home/work/Desktop/todo.pdf";


    public static void main(String... args) {
        ToDoList toDoList = new ToDoList();
        toDoList.drawIt();
    }


    void drawIt() {


        PdfDocument pdfDoc;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(DEST));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        PageSize pageSize = PageSize.A4.rotate();
        pdfDoc.setDefaultPageSize(pageSize);

        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());
           for (int x=0; x < 5; x++) {
            for (int y = 0; y < 26; y++) {
            float xPoints = x * xOffset;
            float yPoints = y * yOffset;
            drawBox(canvas, xPoints, yPoints, true, "*", false, false);
        }
    }


    pdfDoc.close();

}
    public static final long width= 155;
    public static final long height = 20;
    public static final long xOffset= 160;
    public static final long yOffset = 24;
    public static final long radius = 5;


    void drawBox(PdfCanvas canvas, float x, float y, boolean box, String boxText, boolean checkbox, boolean strikeThrough) {
        canvas.roundRectangle(x, y, width, height, radius);
        canvas.stroke();
    }


}
