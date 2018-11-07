package com.github.ativey.pdftodolist.pdf;

import com.github.ativey.pdftodolist.CategoryRepository;
import com.github.ativey.pdftodolist.TaskRepository;
import com.itextpdf.io.font.FontMetrics;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;


// find . -path ./.git -prune -o -type f -name '*.java' -print0 | xargs -0 sed -i 's/core.simple.ParameterizedRowMapper/core.RowMapper/g'

@Component
public class ToDoList {

    @Autowired
    private ApplicationContext applicationContext;


    final static double MM_TO_POINT = 2.83465;

    public static final long checkBoxSize = mmToPoint(2.5f);
    public static final long verySmallGap = mmToPoint(0.7);
    public static final long smallGap = mmToPoint(1.0f);
    public static final long widthOfBox = mmToPoint(6.0);
    public static final long checkBoxLeftFromRightEdge = checkBoxSize + smallGap;

    // TODO Better font handling
    // TODO Change transform matrix so item 0 top left
    // TODO Remove all hard-coded constants - mainly small gaps

    @Autowired
    private FontProgram bodyFontProgram;

    @Autowired
    public FontProgram boldFontProgram;

    @Autowired
    private FontProgram italicFontProgram;

    @Autowired
    public FontProgram boldItalicFontProgram;

    public static final String DEFAULT_DEST = "/home/work/Desktop/todo.pdf";

    public static final long page_x_border = mmToPoint(9.0);
    public static final long page_y_border = mmToPoint(9.0);
    public static final long width = mmToPoint(54.0);
    public static final long height = mmToPoint(7.0);
    public static final long xOffset = mmToPoint(56.0);
    public static final long yOffset = mmToPoint(7.6);
    public static final long radius = mmToPoint(1.76);
    final double curv = 0.4477f;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    //@Autowired
    //private String bodyFontName;


    //@Autowired
    //private String boldFontName;

private String destination;


//    public static final String REGULAR =
//            "/fonts/junicode-1.001/Junicode-RegularCondensed.ttf";
//    public static final String BOLD =
//            "/fonts/junicode-1.001/Junicode-BoldCondensed.ttf";
    //public static final String ITALIC =
     //       "src/main/resources/fonts/Cardo-Italic.ttf";





    private static final long mmToPoint(double mm) {
        double point = mm * MM_TO_POINT;
        return (long) point;
    }

//    public ToDoList(String destination) {
//        this.destination = destination;
//        //createFonts();
//    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

//    public void createFonts() {
//        try {
//
////            System.err.println("bodyFont is '"+bodyFont+"'");
//
////            System.err.println(ToDoList.class);
////            System.err.println(ToDoList.class.getResourceAsStream(BOLD));
////            byte[] fontData = ToDoList.class.getResourceAsStream(BOLD).readAllBytes();
//
//
//
////                    FontProgram fontProgram =
////                    FontProgramFactory.createFont(REGULAR);
////
////
////                    bodyFont = PdfFontFactory.createFont(
////                    fontProgram, PdfEncodings.WINANSI, true);
//            byte[] fontData = this.getClass().getResourceAsStream(REGULAR).readAllBytes();
//            bodyFont = PdfFontFactory.createFont(fontData, true);
//
//            FontMetrics fontMetrics = bodyFont.getFontProgram().getFontMetrics();
//            float textHeight = 1000.0f * fontMetrics.getAscender() - fontMetrics.getDescender();
//
//
//            //boldFont = PdfFontFactory.createFont(BOLD, true);
//            fontData = this.getClass().getResourceAsStream(BOLD).readAllBytes();
//                    boldFont = PdfFontFactory.createFont(fontData, true);
//            //PdfFont italicFont = PdfFontFactory.createFont(ITALIC, true);
//
//
//
//
//
//
//
////            bodyFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
////            boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
//        } catch (IOException exp) {
//            exp.printStackTrace();
//        }
//    }

    public static void main(String... args) throws IOException {


        ToDoList toDoList = new ToDoList();
        //toDoList.createFonts();
        toDoList.setDestination(DEFAULT_DEST);

        toDoList.drawRandom();
    }

    public float transform(float y) {
        return PageSize.A4.rotate().getTop() - y;
    }


    private PdfDocument makePdfDocument() throws IOException {

        PdfDocument pdfDoc;

        try {
            pdfDoc = new PdfDocument(new PdfWriter(destination));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        PageSize pageSize = PageSize.A4.rotate();
        pdfDoc.setDefaultPageSize(pageSize);

        PdfViewerPreferences viewerPreferences = new PdfViewerPreferences();
        pdfDoc.getCatalog().setViewerPreferences(viewerPreferences);
        viewerPreferences.setDuplex(PdfViewerPreferences.PdfViewerPreferencesConstants.DUPLEX_FLIP_SHORT_EDGE);

        //drawRandom(pdfDoc);
        return pdfDoc;
    }

    private void drawRandom() throws IOException {
        PdfDocument pdfDoc = makePdfDocument();
        var colours = List.of(RED, BLUE, GREEN, PURPLE, AQUAMARINE, INDIGO);
        var texts = List.of("Earth", "Gallifrey", "Skaro", "Mondas", "Raxacoricofallapatorius");
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            final PdfPage page = pdfDoc.addNewPage();
            PdfCanvas canvas = new PdfCanvas(page);
            int count = 0;
            for (int x = 0; x < 5; x++) {
                for (int y = 1; y < 27; y++) {
                    float xPoints = x * xOffset + page_x_border;
                    float yPoints = transform(y * yOffset + page_y_border);
                    drawBox(canvas,
                            colours.get(random.nextInt(colours.size() - 1)).getColour(),
                            xPoints,
                            yPoints,
                            random.nextBoolean(),
                            Optional.of(Integer.toString(count)),
                            random.nextBoolean(),
                            Optional.of(texts.get(count % texts.size())),
                            random.nextBoolean(),
                            random.nextBoolean(),
                            random.nextBoolean());
                    count++;
                }
            }
        }
        pdfDoc.close();
    }

    public void drawFromList(List<Pair<PdfColor, ToDoItem>> listOfPairs) throws IOException {
        PdfDocument pdfDoc = makePdfDocument();
        //System.err.println("bodyFontName is '"+bodyFont+"'");
        //System.err.println("boldFontName is '"+boldFont+"'");

        int count = 0;
        int pageCount=0;
        boolean completed = false;
        //for (int i = 0; i < 2; i++) {
        do {
            do {
                final PdfPage page = pdfDoc.addNewPage();
                pageCount++;
                PdfCanvas canvas = new PdfCanvas(page);
                for (int x = 0; x < 5; x++) {
                    for (int y = 1; y < 27; y++) {
                        float xPoints = x * xOffset + page_x_border;
                        float yPoints = transform(y * yOffset + page_y_border);
                        if (count < listOfPairs.size()) {

                            ToDoItem item = listOfPairs.get(count).getSecond();
                            drawBox(canvas,
                                    listOfPairs.get(count).getFirst().getColour(),
                                    xPoints,
                                    yPoints,
                                    item.isBox(),
                                    item.getBoxText(),
                                    item.isCheckBox(),
                                    Optional.of(item.getName()),
                                    item.isComplete(),
                                    item.isImportant(),
                                    item.isSmall());
                        } else {
                            completed = true;
                            drawBox(canvas, GRAY.getColour(), xPoints, yPoints, true, Optional.empty(), false, Optional.empty(), false, false, false);
                        }
                        count++;
                    }
                }
            } while (!completed);
        } while ((pageCount % 2) == 1);
        pdfDoc.close();
    }


    void drawBox(PdfCanvas canvas, Color colour, double x, double y, boolean box, Optional<String> boxText, boolean checkbox, Optional<String> text, boolean complete, boolean important, boolean small) throws IOException {

        double effectiveStartX = x + smallGap;
        double effectiveEndX = x + width;

        canvas.saveState();
        canvas.setStrokeColor(colour);
        canvas.setFillColor(colour);
        canvas.roundRectangle(x, y, width, height, radius);
        canvas.stroke();
        if (box) {
            drawBox(canvas, colour, x, x + widthOfBox, y, boxText, important, small);
            effectiveStartX = x + widthOfBox + smallGap;
        }
        if (checkbox) {
            drawCheckBox(canvas, colour, x + width - checkBoxLeftFromRightEdge, y+(height / 2), complete);
            effectiveEndX = x + width - mmToPoint(5.5);
        }
        if (text.isPresent()) {
            drawText(canvas, colour, effectiveStartX, effectiveEndX, y, text.get(), 10, complete, important, small);
        }
        canvas.restoreState();
    }

    private void drawText(PdfCanvas canvas, Color colour, double effectiveStartX, double effectiveEndX, double y, String text, int fontSize, boolean complete, boolean important, boolean small) throws IOException {
        canvas.saveState();
        canvas.moveTo(effectiveStartX, y)
                .lineTo(effectiveEndX, y)
                .lineTo(effectiveEndX, y +height)
                .lineTo(effectiveStartX, y + height)
                .closePath()
                .clip();
        canvas.newPath();
        double yTextOffset = mmToPoint(1.1);

        String encoding = null;
        PdfFont current = PdfFontFactory.createFont(bodyFontProgram, encoding, true);
        if (important && small) {
            current = PdfFontFactory.createFont(boldItalicFontProgram, encoding, true);
        } else {
            if (important) {
                current = PdfFontFactory.createFont(boldFontProgram, encoding, true);
            }
            if (small) {
                current = PdfFontFactory.createFont(italicFontProgram, encoding, true);
            }
        }

        canvas.beginText()
                .setFontAndSize(current, fontSize)
                .setColor(colour, true)
                .moveText(effectiveStartX, y + ((height - fontSize)/2) + yTextOffset)
                .showText(text)
                .endText();
        //canvas.restoreState();
        double width = current.getWidth(text, fontSize);


        // TODO Strike-through if complete, or otherwise indicate
        if (complete) {
            System.err.println("Completing text '"+text+"'");
            canvas.newPath();
            canvas.setStrokeColor(colour)
                    .setLineWidth(canvas.getGraphicsState().getLineWidth()/2)
            .moveTo(effectiveStartX, y+height/2)
                    //.lineTo(effectiveEndX, y+height/2)
                    .lineTo(effectiveStartX + width + mmToPoint(1), y+height/2)
                    .stroke();
        }
        canvas.restoreState();
    }

    private void drawBox(PdfCanvas canvas, Color colour, double boxFarLeftX, double x, double y, Optional<String> boxText, boolean important, boolean small) throws IOException {
        // FIXME Check join type


        canvas
                .moveTo(x - radius, y)
                .curveTo(x - radius * curv, y, x, y + radius * curv, x, y + radius)
                .lineTo(x, y + height - radius)
                .curveTo(x, y + height - radius * curv, x - radius * curv, y + height, x - radius, y + height);
        canvas.stroke();

        if (boxText.isPresent()){
            drawText(canvas, colour, boxFarLeftX + verySmallGap, x, y, boxText.get(), 9, false, important, small);
        }
    }

    void drawCheckBox(PdfCanvas canvas, Color colour, double x, double y, boolean complete) {
        float box = checkBoxSize;
        canvas.roundRectangle(x, y-(checkBoxSize/2), box, box, radius/2);
        if (complete) {
            canvas.fillStroke();
        } else {
            canvas.stroke();
        }
    }


}
