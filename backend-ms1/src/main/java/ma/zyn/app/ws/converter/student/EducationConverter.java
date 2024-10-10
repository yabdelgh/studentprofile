package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.ws.dto.student.EducationDto;

@Component
public class EducationConverter {



    public Education toItem(EducationDto dto) {
        if (dto == null) {
            return null;
        } else {
        Education item = new Education();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getDegree()))
                item.setDegree(dto.getDegree());
            if(StringUtil.isNotEmpty(dto.getMajor()))
                item.setMajor(dto.getMajor());
            if(StringUtil.isNotEmpty(dto.getInstitution()))
                item.setInstitution(dto.getInstitution());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));



        return item;
        }
    }


    public EducationDto toDto(Education item) {
        if (item == null) {
            return null;
        } else {
            EducationDto dto = new EducationDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getDegree()))
                dto.setDegree(item.getDegree());
            if(StringUtil.isNotEmpty(item.getMajor()))
                dto.setMajor(item.getMajor());
            if(StringUtil.isNotEmpty(item.getInstitution()))
                dto.setInstitution(item.getInstitution());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));


        return dto;
        }
    }


	
    public List<Education> toItem(List<EducationDto> dtos) {
        List<Education> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (EducationDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<EducationDto> toDto(List<Education> items) {
        List<EducationDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Education item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(EducationDto dto, Education t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Education> copy(List<EducationDto> dtos) {
        List<Education> result = new ArrayList<>();
        if (dtos != null) {
            for (EducationDto dto : dtos) {
                Education instance = new Education();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
