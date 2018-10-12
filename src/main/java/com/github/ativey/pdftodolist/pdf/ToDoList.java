package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import org.springframework.data.util.Pair;
import org.springframework.util.ReflectionUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;


// find . -path ./.git -prune -o -type f -name '*.java' -print0 | xargs -0 sed -i 's/core.simple.ParameterizedRowMapper/core.RowMapper/g'

public class ToDoList {


    final static double MM_TO_POINT = 2.83465;

    public static final long checkBoxSize = mmToPoint(2.5f);
    public static final long verySmallGap = mmToPoint(0.7);
    public static final long smallGap = mmToPoint(1.0f);
    public static final long widthOfBox = mmToPoint(6.0);
    public static final long checkBoxLeftFromRightEdge = checkBoxSize + smallGap;

    // TODO Better font handling
    // TODO Change transform matrix so item 0 top left
    // TODO Remove all hard-coded constants - mainly small gaps

    private static PdfFont bodyFont;

    private static PdfFont boldFont;




    public static final String DEST = "/home/work/Desktop/todo.pdf";


    public static final long page_x_border = mmToPoint(9.0);
    public static final long page_y_border = mmToPoint(9.0);
    public static final long width = mmToPoint(54.0);
    public static final long height = mmToPoint(7.0);
    public static final long xOffset = mmToPoint(56.0);
    public static final long yOffset = mmToPoint(7.6);
    public static final long radius = mmToPoint(1.76);
    final double curv = 0.4477f;


    private static final long mmToPoint(double mm) {
        double point = mm * MM_TO_POINT;
        return (long) point;
    }

    public static void main(String... args) throws IOException {

        bodyFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
        boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        ToDoList toDoList = new ToDoList();
        toDoList.drawIt();
    }

    void drawIt() throws IOException {

        PdfDocument pdfDoc;
        try {
            pdfDoc = new PdfDocument(new PdfWriter(DEST));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        PageSize pageSize = PageSize.A4.rotate();
        pdfDoc.setDefaultPageSize(pageSize);


        drawCssColours(pdfDoc);
        //drawRandom(pdfDoc);


        pdfDoc.close();

    }


    private void drawCssColours(PdfDocument pdfDoc) throws IOException {

        var list = new ArrayList<Pair<PdfColor, String>>();

        ReflectionUtils.doWithLocalFields(PdfColor.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    return;
                }
                PdfColor colour = (PdfColor) field.get(PdfColor.class);
                String description = String.format("#%s %s", colour.getHexString(), field.getName());
                System.err.println(description);
                list.add(Pair.of(colour, description));
            }
        });

        Random random = new Random();

        int count = 0;
        for (int i = 0; i < 2; i++) {
            final PdfPage page = pdfDoc.addNewPage();
            PdfCanvas canvas = new PdfCanvas(page);
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 26; y++) {
                    float xPoints = x * xOffset + page_x_border;
                    float yPoints = y * yOffset + page_y_border;
                    if (count < list.size()) {
                        drawBox(canvas,
                                list.get(count).getFirst().getColour(),
                                xPoints,
                                yPoints,
                                false,
                                Optional.of(Integer.toString(count)),
                                true,
                                Optional.of(list.get(count).getSecond()),
                                true,
                                false);
                    } else {
                        drawBox(canvas, GRAY.getColour(), xPoints, yPoints, false, Optional.empty(), true, Optional.empty(), false, false);
                    }
                    count++;
                }
            }
        }
    }

    private void drawRandom(PdfDocument pdfDoc) throws IOException {
        var colours = List.of(RED, BLUE, GREEN, PURPLE, AQUAMARINE, INDIGO);
        var texts = List.of("Earth", "Gallifrey", "Skaro", "Mondas", "Raxacoricofallapatorius");
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            final PdfPage page = pdfDoc.addNewPage();
            PdfCanvas canvas = new PdfCanvas(page);
            int count = 0;
            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 26; y++) {
                    float xPoints = x * xOffset + page_x_border;
                    float yPoints = y * yOffset + page_y_border;
                    drawBox(canvas,
                            colours.get(random.nextInt(colours.size() - 1)).getColour(),
                            xPoints,
                            yPoints,
                            random.nextBoolean(),
                            Optional.of(Integer.toString(count)),
                            random.nextBoolean(),
                            Optional.of(texts.get(count % texts.size())),
                            random.nextBoolean(),
                            random.nextBoolean());
                    count++;
                }
            }
        }
    }


    void drawBox(PdfCanvas canvas, Color colour, double x, double y, boolean box, Optional<String> boxText, boolean checkbox, Optional<String> text, boolean complete, boolean important) throws IOException {

        double effectiveStartX = x + smallGap;
        double effectiveEndX = x + width;

        canvas.saveState();
        canvas.setStrokeColor(colour);
        canvas.setFillColor(colour);
        canvas.roundRectangle(x, y, width, height, radius);
        canvas.stroke();
        if (box) {
            drawBox(canvas, colour, x, x + widthOfBox, y, boxText, important);
            effectiveStartX = x + widthOfBox + smallGap;
        }
        if (checkbox) {
            drawCheckBox(canvas, colour, x + width - checkBoxLeftFromRightEdge, y+(height / 2), complete);
            effectiveEndX = x + width - mmToPoint(5.5);
        }
        if (text.isPresent()) {
            drawText(canvas, colour, effectiveStartX, effectiveEndX, y, text.get(), 10, complete, important);
        }
        canvas.restoreState();
    }

    private void drawText(PdfCanvas canvas, Color colour, double effectiveStartX, double effectiveEndX, double y, String text, int fontSize, boolean complete, boolean important) throws IOException {
        canvas.saveState();
        canvas.moveTo(effectiveStartX, y)
                .lineTo(effectiveEndX, y)
                .lineTo(effectiveEndX, y +height)
                .lineTo(effectiveStartX, y + height)
                .closePath()
                .clip();
        canvas.newPath();
        canvas.beginText()
                .setFontAndSize(important ? boldFont : bodyFont, fontSize)
                .setColor(colour, true)
                .moveText(effectiveStartX, y + ((height - fontSize)/2) )
                .showText(text)
                .endText();
        canvas.restoreState();
        // TODO Strike-through if complete, or otherwise indicate
    }

    private void drawBox(PdfCanvas canvas, Color colour, double boxFarLeftX, double x, double y, Optional<String> boxText, boolean important) throws IOException {
        // FIXME Check join type


        canvas
                .moveTo(x - radius, y)
                .curveTo(x - radius * curv, y, x, y + radius * curv, x, y + radius)
                .lineTo(x, y + height - radius)
                .curveTo(x, y + height - radius * curv, x - radius * curv, y + height, x - radius, y + height);
        canvas.stroke();

        if (boxText.isPresent()){
            drawText(canvas, colour, boxFarLeftX + verySmallGap, x, y, boxText.get(), 9, false, important);
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
