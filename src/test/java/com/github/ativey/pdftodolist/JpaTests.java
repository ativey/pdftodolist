package com.github.ativey.pdftodolist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Category c1 = new Category("Category1", 0);
        entityManager.persist(c1);
        Category c2 = new Category("Category2", 1);
        entityManager.persist(c1);
        entityManager.flush();

        // when
        Optional<Category> found = categoryRepository.findByName(c1.getName());
        long count = categoryRepository.count();

        // then

        assertThat(categoryRepository.count()).isEqualTo(1);

        assertThat(found.isPresent()).isEqualTo(true);

        //noinspection OptionalGetWithoutIsPresent
        assertThat(found.get().getName()).isEqualTo(c1.getName());
    }

}