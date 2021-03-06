package com.github.ativey.pdftodolist;

import com.github.ativey.pdftodolist.pdf.ToDoItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest({"SpringBootTest.WebEnvironment.MOCK"}) //,
//        classes = Application.class)
@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@ContextConfiguration(classes = {YamlLoader.class, YamlDbPersistence.class})
public class YamlDbPersistenceTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private YamlLoader yamlLoader = new YamlLoader();

    @Autowired
    private YamlDbPersistence yamlDbPersistence = new YamlDbPersistence();

    @Test
    public void whenFindByName_thenReturnEmployee() throws IOException {

        LinkedHashMap<String, List<ToDoItem>> categoryListMap;

        try (InputStream istream = this.getClass().getResourceAsStream("/jobs1.yaml")) {
            categoryListMap = yamlLoader.load(istream);
            yamlDbPersistence.persist(categoryListMap);

            assertThat(categoryRepository.count()).isEqualTo(4);
            assertThat(taskRepository.count()).isEqualTo(17);

            //Optional<Category> found = categoryRepository.findByName(c1.getName());
            //long count = categoryRepository.count();

            // then



            //assertThat(found.isPresent()).isEqualTo(true);

            //noinspection OptionalGetWithoutIsPresent
            //assertThat(found.get().getName()).isEqualTo(c1.getName());

//            assertThat(countCategoryNames(categoryListMap)).isEqualTo(4);
//            assertThat(countAllTasks(categoryListMap)).isEqualTo(20);
//            assertThat(countTasksForCategoryName(categoryListMap, "CategoryC")).isEqualTo(5);
        }


        try (InputStream istream = this.getClass().getResourceAsStream("/jobs2.yaml")){
            categoryListMap = yamlLoader.load(istream);
            yamlDbPersistence.persist(categoryListMap);

            assertThat(categoryRepository.count()).isEqualTo(7);
            assertThat(taskRepository.count()).isEqualTo(29);
        }





//
//        // given
//        Category c1 = new Category("Category1");
//        entityManager.persist(c1);
//        Category c2 = new Category("Category2");
//        entityManager.persist(c1);
//        entityManager.flush();
//
//        // when
//        Optional<Category> found = categoryRepository.findByName(c1.getName());
//        long count = categoryRepository.count();
//
//        // then
//
//        assertThat(categoryRepository.count()).isEqualTo(1);
//
//        assertThat(found.isPresent()).isEqualTo(true);
//
//        //noinspection OptionalGetWithoutIsPresent
//        assertThat(found.get().getName()).isEqualTo(c1.getName());
    }

}