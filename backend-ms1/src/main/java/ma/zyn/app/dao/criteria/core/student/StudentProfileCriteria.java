package  ma.zyn.app.dao.criteria.core.student;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class StudentProfileCriteria extends  BaseCriteria  {

    private String firstName;
    private String firstNameLike;
    private String lastName;
    private String lastNameLike;
    private String email;
    private String emailLike;
    private String phoneNumber;
    private String phoneNumberLike;
    private String imageUrl;
    private String imageUrlLike;
    private String bio;
    private String bioLike;
    private String interests;
    private String interestsLike;
    private String skills;
    private String skillsLike;

    private EducationCriteria education ;
    private List<EducationCriteria> educations ;


    public String getFirstName(){
        return this.firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstNameLike(){
        return this.firstNameLike;
    }
    public void setFirstNameLike(String firstNameLike){
        this.firstNameLike = firstNameLike;
    }

    public String getLastName(){
        return this.lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastNameLike(){
        return this.lastNameLike;
    }
    public void setLastNameLike(String lastNameLike){
        this.lastNameLike = lastNameLike;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEmailLike(){
        return this.emailLike;
    }
    public void setEmailLike(String emailLike){
        this.emailLike = emailLike;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getPhoneNumberLike(){
        return this.phoneNumberLike;
    }
    public void setPhoneNumberLike(String phoneNumberLike){
        this.phoneNumberLike = phoneNumberLike;
    }

    public String getImageUrl(){
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageUrlLike(){
        return this.imageUrlLike;
    }
    public void setImageUrlLike(String imageUrlLike){
        this.imageUrlLike = imageUrlLike;
    }

    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
    }
    public String getBioLike(){
        return this.bioLike;
    }
    public void setBioLike(String bioLike){
        this.bioLike = bioLike;
    }

    public String getInterests(){
        return this.interests;
    }
    public void setInterests(String interests){
        this.interests = interests;
    }
    public String getInterestsLike(){
        return this.interestsLike;
    }
    public void setInterestsLike(String interestsLike){
        this.interestsLike = interestsLike;
    }

    public String getSkills(){
        return this.skills;
    }
    public void setSkills(String skills){
        this.skills = skills;
    }
    public String getSkillsLike(){
        return this.skillsLike;
    }
    public void setSkillsLike(String skillsLike){
        this.skillsLike = skillsLike;
    }


    public EducationCriteria getEducation(){
        return this.education;
    }

    public void setEducation(EducationCriteria education){
        this.education = education;
    }
    public List<EducationCriteria> getEducations(){
        return this.educations;
    }

    public void setEducations(List<EducationCriteria> educations){
        this.educations = educations;
    }
}
