package  ma.zyn.app.ws.dto.student;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentProfileDto  extends AuditBaseDto {

    private String firstName  ;
    private String lastName  ;
    private String email  ;
    private String phoneNumber  ;
    private String imageUrl  ;
    private String bio  ;
    private String interests  ;
    private String skills  ;

    private EducationDto education ;

    private List<ExperienceDto> experience ;


    public StudentProfileDto(){
        super();
    }




    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }


    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }


    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }


    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }


    public String getImageUrl(){
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }


    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }


    public String getInterests(){
        return this.interests;
    }
    public void setInterests(String interests){
        this.interests = interests;
    }


    public String getSkills(){
        return this.skills;
    }
    public void setSkills(String skills){
        this.skills = skills;
    }


    public EducationDto getEducation(){
        return this.education;
    }

    public void setEducation(EducationDto education){
        this.education = education;
    }



    public List<ExperienceDto> getExperience(){
        return this.experience;
    }

    public void setExperience(List<ExperienceDto> experience){
        this.experience = experience;
    }



}
