package ma.zyn.app.bean.core.student;

import java.util.List;







import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_profile")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="student_profile_seq",sequenceName="student_profile_seq",allocationSize=1, initialValue = 1)
public class StudentProfile  extends BaseEntity     {




    @Column(length = 500)
    private String firstName;

    @Column(length = 500)
    private String lastName;

    @Column(length = 500)
    private String email;

    @Column(length = 500)
    private String phoneNumber;

    @Column(length = 500)
    private String imageUrl;

    private String bio;

    @Column(length = 500)
    private String interests;

    @Column(length = 500)
    private String skills;

    private Education education ;

    private List<Experience> experience ;

    public StudentProfile(){
        super();
    }

    public StudentProfile(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="student_profile_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
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
      @Column(columnDefinition="TEXT")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education")
    public Education getEducation(){
        return this.education;
    }
    public void setEducation(Education education){
        this.education = education;
    }
    @OneToMany(mappedBy = "studentProfile")
    public List<Experience> getExperience(){
        return this.experience;
    }

    public void setExperience(List<Experience> experience){
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentProfile studentProfile = (StudentProfile) o;
        return id != null && id.equals(studentProfile.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

