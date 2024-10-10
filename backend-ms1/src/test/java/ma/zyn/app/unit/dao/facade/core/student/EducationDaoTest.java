package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.dao.facade.core.student.EducationDao;

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
public class EducationDaoTest {

@Autowired
    private EducationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Education entity = new Education();
        entity.setId(id);
        underTest.save(entity);
        Education loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Education entity = new Education();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Education loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Education> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Education> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Education given = constructSample(1);
        Education saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Education constructSample(int i) {
		Education given = new Education();
        given.setDegree("degree-"+i);
        given.setMajor("major-"+i);
        given.setInstitution("institution-"+i);
        given.setStartDate(LocalDateTime.now());
        given.setEndDate(LocalDateTime.now());
        return given;
    }

}
