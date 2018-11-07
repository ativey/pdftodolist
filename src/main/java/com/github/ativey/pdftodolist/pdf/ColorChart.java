package com.github.ativey.pdftodolist.pdf;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfViewerPreferences;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.springframework.data.util.Pair;
import org.springframework.util.ReflectionUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.github.ativey.pdftodolist.pdf.PdfColor.WHITE;


// find . -path ./.git -prune -o -type f -name '*.java' -print0 | xargs -0 sed -i 's/core.simple.ParameterizedRowMapper/core.RowMapper/g'

public class ColorChart {

    // TODO Better font handling
    // TODO Change transform matrix so item 0 top left
    // TODO Remove all hard-coded constants - mainly small gaps

    public static final String DEST = "/home/work/Desktop/colourChart.pdf";

    public static void main(String... args) throws IOException {


        ToDoList toDoList = new ToDoList();
        toDoList.setDestination(DEST);

        var list = getCssColours();
        toDoList.drawFromList(list);
    }


    static private List<Pair<PdfColor, ToDoItem>> getCssColours() throws IOException {


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
                ToDoItem item = new ToDoItem(false, "", true, description, false, false, false);
                list.add(Pair.of(colour, item));
                colorCount[0]++;
                if (endOfBlock.contains(name)) {
                    list.add(Pair.of(WHITE, new ToDoItem(false, "", true, " ", false, false, false)));
                }
            }
        });

        System.err.println("The number of colours is "+colorCount[0]);
        return list;

    }


}
