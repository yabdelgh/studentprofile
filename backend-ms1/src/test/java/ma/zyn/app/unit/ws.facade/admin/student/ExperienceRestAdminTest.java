package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.service.impl.admin.student.ExperienceAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.ExperienceRestAdmin;
import ma.zyn.app.ws.converter.student.ExperienceConverter;
import ma.zyn.app.ws.dto.student.ExperienceDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExperienceRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ExperienceAdminServiceImpl service;
    @Mock
    private ExperienceConverter converter;

    @InjectMocks
    private ExperienceRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllExperienceTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ExperienceDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ExperienceDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveExperienceTest() throws Exception {
        // Mock data
        ExperienceDto requestDto = new ExperienceDto();
        Experience entity = new Experience();
        Experience saved = new Experience();
        ExperienceDto savedDto = new ExperienceDto();

        // Mock the converter to return the experience object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved experience DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ExperienceDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ExperienceDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved experience DTO
        assertEquals(savedDto.getTitle(), responseBody.getTitle());
        assertEquals(savedDto.getCompany(), responseBody.getCompany());
        assertEquals(savedDto.getStartDate(), responseBody.getStartDate());
        assertEquals(savedDto.getEndDate(), responseBody.getEndDate());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
