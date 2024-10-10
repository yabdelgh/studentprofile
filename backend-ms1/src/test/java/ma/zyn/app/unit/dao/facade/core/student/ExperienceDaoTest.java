package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.dao.facade.core.student.ExperienceDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ExperienceDaoTest {

@Autowired
    private ExperienceDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Experience entity = new Experience();
        entity.setId(id);
        underTest.save(entity);
        Experience loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Experience entity = new Experience();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Experience loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Experience> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Experience> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Experience given = constructSample(1);
        Experience saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Experience constructSample(int i) {
		Experience given = new Experience();
        given.setTitle("title-"+i);
        given.setCompany("company-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        given.setDescription("description-"+i);
        return given;
    }

}
