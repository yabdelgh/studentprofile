package ma.zyn.app.unit.ws.facade.admin.student;

import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.service.impl.admin.student.StudentProfileAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.student.StudentProfileRestAdmin;
import ma.zyn.app.ws.converter.student.StudentProfileConverter;
import ma.zyn.app.ws.dto.student.StudentProfileDto;
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
public class StudentProfileRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StudentProfileAdminServiceImpl service;
    @Mock
    private StudentProfileConverter converter;

    @InjectMocks
    private StudentProfileRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStudentProfileTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StudentProfileDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StudentProfileDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStudentProfileTest() throws Exception {
        // Mock data
        StudentProfileDto requestDto = new StudentProfileDto();
        StudentProfile entity = new StudentProfile();
        StudentProfile saved = new StudentProfile();
        StudentProfileDto savedDto = new StudentProfileDto();

        // Mock the converter to return the studentProfile object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved studentProfile DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StudentProfileDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StudentProfileDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved studentProfile DTO
        assertEquals(savedDto.getFirstName(), responseBody.getFirstName());
        assertEquals(savedDto.getLastName(), responseBody.getLastName());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getPhoneNumber(), responseBody.getPhoneNumber());
        assertEquals(savedDto.getImageUrl(), responseBody.getImageUrl());
        assertEquals(savedDto.getBio(), responseBody.getBio());
        assertEquals(savedDto.getInterests(), responseBody.getInterests());
        assertEquals(savedDto.getSkills(), responseBody.getSkills());
    }

}
