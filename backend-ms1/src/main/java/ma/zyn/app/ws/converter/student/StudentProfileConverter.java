package  ma.zyn.app.ws.converter.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;
import ma.zyn.app.zynerator.util.ListUtil;

import ma.zyn.app.ws.converter.student.ExperienceConverter;
import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.ws.converter.student.EducationConverter;
import ma.zyn.app.bean.core.student.Education;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.ws.dto.student.StudentProfileDto;

@Component
public class StudentProfileConverter {

    @Autowired
    private ExperienceConverter experienceConverter ;
    @Autowired
    private EducationConverter educationConverter ;
    private boolean education;
    private boolean experience;

    public  StudentProfileConverter() {
        init(true);
    }

    public StudentProfile toItem(StudentProfileDto dto) {
        if (dto == null) {
            return null;
        } else {
        StudentProfile item = new StudentProfile();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getFirstName()))
                item.setFirstName(dto.getFirstName());
            if(StringUtil.isNotEmpty(dto.getLastName()))
                item.setLastName(dto.getLastName());
            if(StringUtil.isNotEmpty(dto.getEmail()))
                item.setEmail(dto.getEmail());
            if(StringUtil.isNotEmpty(dto.getPhoneNumber()))
                item.setPhoneNumber(dto.getPhoneNumber());
            if(StringUtil.isNotEmpty(dto.getImageUrl()))
                item.setImageUrl(dto.getImageUrl());
            if(StringUtil.isNotEmpty(dto.getBio()))
                item.setBio(dto.getBio());
            if(StringUtil.isNotEmpty(dto.getInterests()))
                item.setInterests(dto.getInterests());
            if(StringUtil.isNotEmpty(dto.getSkills()))
                item.setSkills(dto.getSkills());
            if(this.education && dto.getEducation()!=null)
                item.setEducation(educationConverter.toItem(dto.getEducation())) ;


            if(this.experience && ListUtil.isNotEmpty(dto.getExperience()))
                item.setExperience(experienceConverter.toItem(dto.getExperience()));


        return item;
        }
    }


    public StudentProfileDto toDto(StudentProfile item) {
        if (item == null) {
            return null;
        } else {
            StudentProfileDto dto = new StudentProfileDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getFirstName()))
                dto.setFirstName(item.getFirstName());
            if(StringUtil.isNotEmpty(item.getLastName()))
                dto.setLastName(item.getLastName());
            if(StringUtil.isNotEmpty(item.getEmail()))
                dto.setEmail(item.getEmail());
            if(StringUtil.isNotEmpty(item.getPhoneNumber()))
                dto.setPhoneNumber(item.getPhoneNumber());
            if(StringUtil.isNotEmpty(item.getImageUrl()))
                dto.setImageUrl(item.getImageUrl());
            if(StringUtil.isNotEmpty(item.getBio()))
                dto.setBio(item.getBio());
            if(StringUtil.isNotEmpty(item.getInterests()))
                dto.setInterests(item.getInterests());
            if(StringUtil.isNotEmpty(item.getSkills()))
                dto.setSkills(item.getSkills());
            if(this.education && item.getEducation()!=null) {
                dto.setEducation(educationConverter.toDto(item.getEducation())) ;

            }
        if(this.experience && ListUtil.isNotEmpty(item.getExperience())){
            experienceConverter.init(true);
            experienceConverter.setStudentProfile(false);
            dto.setExperience(experienceConverter.toDto(item.getExperience()));
            experienceConverter.setStudentProfile(true);

        }


        return dto;
        }
    }

    public void init(boolean value) {
        initList(value);
    }

    public void initList(boolean value) {
        this.experience = value;
    }
    public void initObject(boolean value) {
        this.education = value;
    }
	
    public List<StudentProfile> toItem(List<StudentProfileDto> dtos) {
        List<StudentProfile> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (StudentProfileDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<StudentProfileDto> toDto(List<StudentProfile> items) {
        List<StudentProfileDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (StudentProfile item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(StudentProfileDto dto, StudentProfile t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getEducation() == null  && dto.getEducation() != null){
            t.setEducation(new Education());
        }else if (t.getEducation() != null  && dto.getEducation() != null){
            t.setEducation(null);
            t.setEducation(new Education());
        }
        if (dto.getEducation() != null)
        educationConverter.copy(dto.getEducation(), t.getEducation());
        if (dto.getExperience() != null)
            t.setExperience(experienceConverter.copy(dto.getExperience()));
    }

    public List<StudentProfile> copy(List<StudentProfileDto> dtos) {
        List<StudentProfile> result = new ArrayList<>();
        if (dtos != null) {
            for (StudentProfileDto dto : dtos) {
                StudentProfile instance = new StudentProfile();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ExperienceConverter getExperienceConverter(){
        return this.experienceConverter;
    }
    public void setExperienceConverter(ExperienceConverter experienceConverter ){
        this.experienceConverter = experienceConverter;
    }
    public EducationConverter getEducationConverter(){
        return this.educationConverter;
    }
    public void setEducationConverter(EducationConverter educationConverter ){
        this.educationConverter = educationConverter;
    }
    public boolean  isEducation(){
        return this.education;
    }
    public void  setEducation(boolean education){
        this.education = education;
    }
    public boolean  isExperience(){
        return this.experience ;
    }
    public void  setExperience(boolean experience ){
        this.experience  = experience ;
    }
}
