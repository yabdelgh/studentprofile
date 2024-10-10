package ma.zyn.app.bean.core.student;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "education")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="education_seq",sequenceName="education_seq",allocationSize=1, initialValue = 1)
public class Education  extends BaseEntity     {




    @Column(length = 500)
    private String degree;

    @Column(length = 500)
    private String major;

    @Column(length = 500)
    private String institution;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;



    public Education(){
        super();
    }

    public Education(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="education_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getDegree(){
        return this.degree;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public String getMajor(){
        return this.major;
    }
    public void setMajor(String major){
        this.major = major;
    }
    public String getInstitution(){
        return this.institution;
    }
    public void setInstitution(String institution){
        this.institution = institution;
    }
    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return id != null && id.equals(education.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

