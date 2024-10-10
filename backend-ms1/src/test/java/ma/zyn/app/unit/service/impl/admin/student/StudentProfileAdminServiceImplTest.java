package ma.zyn.app.unit.service.impl.admin.student;

import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.dao.facade.core.student.StudentProfileDao;
import ma.zyn.app.service.impl.admin.student.StudentProfileAdminServiceImpl;

import ma.zyn.app.bean.core.student.Experience ;
import ma.zyn.app.bean.core.student.Education ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class StudentProfileAdminServiceImplTest {

    @Mock
    private StudentProfileDao repository;
    private AutoCloseable autoCloseable;
    private StudentProfileAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new StudentProfileAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllStudentProfile() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveStudentProfile() {
        // Given
        StudentProfile toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteStudentProfile() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetStudentProfileById() {
        // Given
        Long idToRetrieve = 1L; // Example StudentProfile ID to retrieve
        StudentProfile expected = new StudentProfile(); // You need to replace StudentProfile with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        StudentProfile result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
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
        List<Experience> experience = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Experience element = new Experience();
                                                element.setId((long)id);
                                                element.setTitle("title"+id);
                                                element.setCompany("company"+id);
                                                element.setStartDate(LocalDateTime.now());
                                                element.setEndDate(LocalDateTime.now());
                                                element.setDescription("description"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setExperience(experience);
        return given;
    }

}
