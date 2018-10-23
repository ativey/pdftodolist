package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.ToDoItem;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import static com.github.ativey.pdftodolist.CategoryMapTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;


public class PdfTodLlistApplicationTests {

    private YamlLoader yamlLoader = new YamlLoader();

    @Test
    public void contextLoads() {
    }


    @Test
    public void testYamlLoader() throws IOException {
        // Read in both files and count items

        LinkedHashMap<String, List<ToDoItem>> categoryMap;

        try (InputStream istream = this.getClass().getResourceAsStream("/jobs1.yaml")) {
            categoryMap = yamlLoader.load(istream);
            assertThat(countCategoryNames(categoryMap)).isEqualTo(4);
            assertThat(countAllTodoItems(categoryMap)).isEqualTo(17);
            assertThat(countTasksForCategoryName(categoryMap, "CategoryC")).isEqualTo(3);
        }

        try (InputStream istream = this.getClass().getResourceAsStream("/jobs2.yaml")){
            categoryMap = yamlLoader.load(istream);
            assertThat(countCategoryNames(categoryMap)).isEqualTo(5);
            assertThat(countAllTodoItems(categoryMap)).isEqualTo(16);
            assertThat(countTasksForCategoryName(categoryMap, "CategoryC")).isEqualTo(4);
        }
    }


}
