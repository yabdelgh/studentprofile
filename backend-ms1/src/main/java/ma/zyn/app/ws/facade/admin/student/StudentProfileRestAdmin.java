package  ma.zyn.app.ws.facade.admin.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.dao.criteria.core.student.StudentProfileCriteria;
import ma.zyn.app.service.facade.admin.student.StudentProfileAdminService;
import ma.zyn.app.ws.converter.student.StudentProfileConverter;
import ma.zyn.app.ws.dto.student.StudentProfileDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/studentProfile/")
public class StudentProfileRestAdmin {




    @Operation(summary = "Finds a list of all studentProfiles")
    @GetMapping("")
    public ResponseEntity<List<StudentProfileDto>> findAll() throws Exception {
        ResponseEntity<List<StudentProfileDto>> res = null;
        List<StudentProfile> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
            converter.initObject(true);
        List<StudentProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a studentProfile by id")
    @GetMapping("id/{id}")
    public ResponseEntity<StudentProfileDto> findById(@PathVariable Long id) {
        StudentProfile t = service.findById(id);
        if (t != null) {
            converter.init(true);
            StudentProfileDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  studentProfile")
    @PostMapping("")
    public ResponseEntity<StudentProfileDto> save(@RequestBody StudentProfileDto dto) throws Exception {
        if(dto!=null){
            converter.init(true);
            StudentProfile myT = converter.toItem(dto);
            StudentProfile t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                StudentProfileDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  studentProfile")
    @PutMapping("")
    public ResponseEntity<StudentProfileDto> update(@RequestBody StudentProfileDto dto) throws Exception {
        ResponseEntity<StudentProfileDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            StudentProfile t = service.findById(dto.getId());
            converter.copy(dto,t);
            StudentProfile updated = service.update(t);
            StudentProfileDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of studentProfile")
    @PostMapping("multiple")
    public ResponseEntity<List<StudentProfileDto>> delete(@RequestBody List<StudentProfileDto> dtos) throws Exception {
        ResponseEntity<List<StudentProfileDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            converter.init(false);
            List<StudentProfile> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified studentProfile")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a studentProfile and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<StudentProfileDto> findWithAssociatedLists(@PathVariable Long id) {
        StudentProfile loaded =  service.findWithAssociatedLists(id);
        converter.init(true);
        StudentProfileDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds studentProfiles by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<StudentProfileDto>> findByCriteria(@RequestBody StudentProfileCriteria criteria) throws Exception {
        ResponseEntity<List<StudentProfileDto>> res = null;
        List<StudentProfile> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        converter.initList(false);
        converter.initObject(true);
        List<StudentProfileDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated studentProfiles by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody StudentProfileCriteria criteria) throws Exception {
        List<StudentProfile> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        converter.initList(false);
        converter.initObject(true);
        List<StudentProfileDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets studentProfile data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody StudentProfileCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<StudentProfileDto> findDtos(List<StudentProfile> list){
        converter.initList(false);
        converter.initObject(true);
        List<StudentProfileDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<StudentProfileDto> getDtoResponseEntity(StudentProfileDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public StudentProfileRestAdmin(StudentProfileAdminService service, StudentProfileConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final StudentProfileAdminService service;
    private final StudentProfileConverter converter;





}
