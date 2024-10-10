package ma.zyn.app.bean.core.student;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;




import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "experience")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="experience_seq",sequenceName="experience_seq",allocationSize=1, initialValue = 1)
public class Experience  extends BaseEntity     {




    @Column(length = 500)
    private String title;

    @Column(length = 500)
    private String company;

    private LocalDateTime startDate ;

    private LocalDateTime endDate ;

    private String description;



    public Experience(){
        super();
    }

    public Experience(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="experience_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getCompany(){
        return this.company;
    }
    public void setCompany(String company){
        this.company = company;
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
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience experience = (Experience) o;
        return id != null && id.equals(experience.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

