package ma.zyn.app.unit.dao.facade.core.student;

import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.dao.facade.core.student.StudentProfileDao;

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

import ma.zyn.app.bean.core.student.Education ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StudentProfileDaoTest {

@Autowired
    private StudentProfileDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        StudentProfile entity = new StudentProfile();
        entity.setId(id);
        underTest.save(entity);
        StudentProfile loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        StudentProfile entity = new StudentProfile();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        StudentProfile loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<StudentProfile> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<StudentProfile> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        StudentProfile given = constructSample(1);
        StudentProfile saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private StudentProfile constructSample(int i) {
		StudentProfile given = new StudentProfile();
        given.setFirstName("firstName-"+i);
        given.setLastName("lastName-"+i);
        given.setEmail("email-"+i);
        given.setPhoneNumber("phoneNumber-"+i);
        given.setImageUrl("imageUrl-"+i);
        given.setBio("bio-"+i);
        given.setInterests("interests-"+i);
        given.setSkills("skills-"+i);
        given.setEducation(new Education(1L));
        return given;
    }

}
