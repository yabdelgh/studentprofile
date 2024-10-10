package ma.zyn.app.unit.service.impl.admin.student;

import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.dao.facade.core.student.ExperienceDao;
import ma.zyn.app.service.impl.admin.student.ExperienceAdminServiceImpl;

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
class ExperienceAdminServiceImplTest {

    @Mock
    private ExperienceDao repository;
    private AutoCloseable autoCloseable;
    private ExperienceAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ExperienceAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllExperience() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveExperience() {
        // Given
        Experience toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteExperience() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetExperienceById() {
        // Given
        Long idToRetrieve = 1L; // Example Experience ID to retrieve
        Experience expected = new Experience(); // You need to replace Experience with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Experience result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
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
