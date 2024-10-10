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

import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.dao.criteria.core.student.EducationCriteria;
import ma.zyn.app.service.facade.admin.student.EducationAdminService;
import ma.zyn.app.ws.converter.student.EducationConverter;
import ma.zyn.app.ws.dto.student.EducationDto;
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
@RequestMapping("/api/admin/education/")
public class EducationRestAdmin {




    @Operation(summary = "Finds a list of all educations")
    @GetMapping("")
    public ResponseEntity<List<EducationDto>> findAll() throws Exception {
        ResponseEntity<List<EducationDto>> res = null;
        List<Education> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EducationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a education by id")
    @GetMapping("id/{id}")
    public ResponseEntity<EducationDto> findById(@PathVariable Long id) {
        Education t = service.findById(id);
        if (t != null) {
            EducationDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  education")
    @PostMapping("")
    public ResponseEntity<EducationDto> save(@RequestBody EducationDto dto) throws Exception {
        if(dto!=null){
            Education myT = converter.toItem(dto);
            Education t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                EducationDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  education")
    @PutMapping("")
    public ResponseEntity<EducationDto> update(@RequestBody EducationDto dto) throws Exception {
        ResponseEntity<EducationDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Education t = service.findById(dto.getId());
            converter.copy(dto,t);
            Education updated = service.update(t);
            EducationDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of education")
    @PostMapping("multiple")
    public ResponseEntity<List<EducationDto>> delete(@RequestBody List<EducationDto> dtos) throws Exception {
        ResponseEntity<List<EducationDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Education> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified education")
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


    @Operation(summary = "Finds a education and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<EducationDto> findWithAssociatedLists(@PathVariable Long id) {
        Education loaded =  service.findWithAssociatedLists(id);
        EducationDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds educations by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<EducationDto>> findByCriteria(@RequestBody EducationCriteria criteria) throws Exception {
        ResponseEntity<List<EducationDto>> res = null;
        List<Education> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<EducationDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated educations by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody EducationCriteria criteria) throws Exception {
        List<Education> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<EducationDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets education data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody EducationCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<EducationDto> findDtos(List<Education> list){
        List<EducationDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<EducationDto> getDtoResponseEntity(EducationDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public EducationRestAdmin(EducationAdminService service, EducationConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final EducationAdminService service;
    private final EducationConverter converter;





}
