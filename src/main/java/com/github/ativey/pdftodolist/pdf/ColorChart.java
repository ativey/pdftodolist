package com.github.ativey.pdftodolist.pdf;

import com.github.ativey.pdftodolist.CategoryRepository;
import com.github.ativey.pdftodolist.Task;
import com.github.ativey.pdftodolist.TaskRepository;
import com.itextpdf.io.font.constants.StandardFonts;
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
import org.springframework.data.util.Pair;
import org.springframework.util.ReflectionUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static com.github.ativey.pdftodolist.pdf.PdfColor.*;


// find . -path ./.git -prune -o -type f -name '*.java' -print0 | xargs -0 sed -i 's/core.simple.ParameterizedRowMapper/core.RowMapper/g'

public class ColorChart {

    // TODO Better font handling
    // TODO Change transform matrix so item 0 top left
    // TODO Remove all hard-coded constants - mainly small gaps

    public static final String DEST = "/home/work/Desktop/colourChart.pdf";

    public static void main(String... args) throws IOException {


        ToDoList toDoList = new ToDoList(DEST);
        PdfDocument pdfDocument = toDoList.setup();

        var list = getCssColours(pdfDocument);
        toDoList.drawFromList(pdfDocument, list);
        pdfDocument.close();
    }


    static private List<Pair<PdfColor, ToDoItem>> getCssColours(PdfDocument pdfDoc) throws IOException {


        Set<String> endOfBlock = Set.of("DARK_RED", "DARK_ORANGE", "YELLOW", "OLIVE_DRAB", "TEAL", "DARK_SLATE_BLUE", "INDIGO", "MEDIUM_VIOLET_RED", "MISTY_ROSE", "BLACK");
        final int[] colorCount = {0};

        var list = new ArrayList<Pair<PdfColor, ToDoItem>>();

        ReflectionUtils.doWithLocalFields(PdfColor.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                int modifiers = field.getModifiers();
                if (!Modifier.isStatic(modifiers)) {
                    return;
                }
                PdfColor colour = (PdfColor) field.get(PdfColor.class);
                String name = field.getName();
                String description = String.format("#%s %s", colour.getHexString(), name);
                //System.err.println(description);
                ToDoItem item = new ToDoItem(false, "", true, description, false, false);
                list.add(Pair.of(colour, item));
                colorCount[0]++;
                if (endOfBlock.contains(name)) {
                    list.add(Pair.of(WHITE, new ToDoItem(false, "", true, " ", false, false)));
                }
            }
        });

        System.err.println("The number of colours is "+colorCount[0]);
        return list;

    }


}
