package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.service.impl.admin.student.EducationAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.EducationRestAdmin;
import ma.zyn.app.ws.converter.student.EducationConverter;
import ma.zyn.app.ws.dto.student.EducationDto;
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
public class EducationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EducationAdminServiceImpl service;
    @Mock
    private EducationConverter converter;

    @InjectMocks
    private EducationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEducationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EducationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EducationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEducationTest() throws Exception {
        // Mock data
        EducationDto requestDto = new EducationDto();
        Education entity = new Education();
        Education saved = new Education();
        EducationDto savedDto = new EducationDto();

        // Mock the converter to return the education object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved education DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EducationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EducationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved education DTO
        assertEquals(savedDto.getDegree(), responseBody.getDegree());
        assertEquals(savedDto.getMajor(), responseBody.getMajor());
        assertEquals(savedDto.getInstitution(), responseBody.getInstitution());
        assertEquals(savedDto.getStartDate(), responseBody.getStartDate());
        assertEquals(savedDto.getEndDate(), responseBody.getEndDate());
    }

}
