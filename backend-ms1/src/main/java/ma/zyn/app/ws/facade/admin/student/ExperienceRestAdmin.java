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

import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.dao.criteria.core.student.ExperienceCriteria;
import ma.zyn.app.service.facade.admin.student.ExperienceAdminService;
import ma.zyn.app.ws.converter.student.ExperienceConverter;
import ma.zyn.app.ws.dto.student.ExperienceDto;
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
@RequestMapping("/api/admin/experience/")
public class ExperienceRestAdmin {




    @Operation(summary = "Finds a list of all experiences")
    @GetMapping("")
    public ResponseEntity<List<ExperienceDto>> findAll() throws Exception {
        ResponseEntity<List<ExperienceDto>> res = null;
        List<Experience> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ExperienceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }


    @Operation(summary = "Finds a experience by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExperienceDto> findById(@PathVariable Long id) {
        Experience t = service.findById(id);
        if (t != null) {
            ExperienceDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @Operation(summary = "Saves the specified  experience")
    @PostMapping("")
    public ResponseEntity<ExperienceDto> save(@RequestBody ExperienceDto dto) throws Exception {
        if(dto!=null){
            Experience myT = converter.toItem(dto);
            Experience t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ExperienceDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  experience")
    @PutMapping("")
    public ResponseEntity<ExperienceDto> update(@RequestBody ExperienceDto dto) throws Exception {
        ResponseEntity<ExperienceDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            Experience t = service.findById(dto.getId());
            converter.copy(dto,t);
            Experience updated = service.update(t);
            ExperienceDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of experience")
    @PostMapping("multiple")
    public ResponseEntity<List<ExperienceDto>> delete(@RequestBody List<ExperienceDto> dtos) throws Exception {
        ResponseEntity<List<ExperienceDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<Experience> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified experience")
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


    @Operation(summary = "Finds a experience and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ExperienceDto> findWithAssociatedLists(@PathVariable Long id) {
        Experience loaded =  service.findWithAssociatedLists(id);
        ExperienceDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds experiences by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ExperienceDto>> findByCriteria(@RequestBody ExperienceCriteria criteria) throws Exception {
        ResponseEntity<List<ExperienceDto>> res = null;
        List<Experience> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ExperienceDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated experiences by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ExperienceCriteria criteria) throws Exception {
        List<Experience> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ExperienceDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets experience data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ExperienceCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ExperienceDto> findDtos(List<Experience> list){
        List<ExperienceDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ExperienceDto> getDtoResponseEntity(ExperienceDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }




   public ExperienceRestAdmin(ExperienceAdminService service, ExperienceConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ExperienceAdminService service;
    private final ExperienceConverter converter;





}
