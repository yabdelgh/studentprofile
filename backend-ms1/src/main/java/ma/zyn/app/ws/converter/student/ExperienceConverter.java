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
import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.ws.dto.student.ExperienceDto;

@Component
public class ExperienceConverter {



    public Experience toItem(ExperienceDto dto) {
        if (dto == null) {
            return null;
        } else {
        Experience item = new Experience();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getTitle()))
                item.setTitle(dto.getTitle());
            if(StringUtil.isNotEmpty(dto.getCompany()))
                item.setCompany(dto.getCompany());
            if(StringUtil.isNotEmpty(dto.getStartDate()))
                item.setStartDate(DateUtil.stringEnToDate(dto.getStartDate()));
            if(StringUtil.isNotEmpty(dto.getEndDate()))
                item.setEndDate(DateUtil.stringEnToDate(dto.getEndDate()));
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());



        return item;
        }
    }


    public ExperienceDto toDto(Experience item) {
        if (item == null) {
            return null;
        } else {
            ExperienceDto dto = new ExperienceDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getTitle()))
                dto.setTitle(item.getTitle());
            if(StringUtil.isNotEmpty(item.getCompany()))
                dto.setCompany(item.getCompany());
            if(item.getStartDate()!=null)
                dto.setStartDate(DateUtil.dateTimeToString(item.getStartDate()));
            if(item.getEndDate()!=null)
                dto.setEndDate(DateUtil.dateTimeToString(item.getEndDate()));
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());


        return dto;
        }
    }


	
    public List<Experience> toItem(List<ExperienceDto> dtos) {
        List<Experience> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ExperienceDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ExperienceDto> toDto(List<Experience> items) {
        List<ExperienceDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Experience item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ExperienceDto dto, Experience t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<Experience> copy(List<ExperienceDto> dtos) {
        List<Experience> result = new ArrayList<>();
        if (dtos != null) {
            for (ExperienceDto dto : dtos) {
                Experience instance = new Experience();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
