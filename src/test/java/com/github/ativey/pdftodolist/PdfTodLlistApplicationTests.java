package com.github.ativey.pdftodolist;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static com.github.ativey.pdftodolist.CategoryMapTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

//@RunWith(SpringRunner.class)
public class PdfTodLlistApplicationTests {

    private YamlLoader yamlLoader = new YamlLoader();

    @Test
    public void contextLoads() {
    }


    @Test
    public void testYamlLoader() throws IOException {
        // Read in both files and count items

        Map<Category, List<Task>> categoryMap;

        try (InputStream istream = this.getClass().getResourceAsStream("/jobs1.yaml")) {
            categoryMap = yamlLoader.load(istream);
            assertThat(countCategories(categoryMap)).isEqualTo(4);
            assertThat(countAllTasks(categoryMap)).isEqualTo(17);
            assertThat(countTasksForCategory(categoryMap, "CategoryC")).isEqualTo(3);
        }

        try (InputStream istream = this.getClass().getResourceAsStream("/jobs2.yaml")){
            categoryMap = yamlLoader.load(istream);
            assertThat(countCategories(categoryMap)).isEqualTo(5);
            assertThat(countAllTasks(categoryMap)).isEqualTo(16);
            assertThat(countTasksForCategory(categoryMap, "CategoryC")).isEqualTo(4);
        }
    }


}
