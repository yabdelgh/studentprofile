package  ma.zyn.app.dao.criteria.core.student;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class EducationCriteria extends  BaseCriteria  {

    private String degree;
    private String degreeLike;
    private String major;
    private String majorLike;
    private String institution;
    private String institutionLike;
    private LocalDateTime startDate;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDate;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;



    public String getDegree(){
        return this.degree;
    }
    public void setDegree(String degree){
        this.degree = degree;
    }
    public String getDegreeLike(){
        return this.degreeLike;
    }
    public void setDegreeLike(String degreeLike){
        this.degreeLike = degreeLike;
    }

    public String getMajor(){
        return this.major;
    }
    public void setMajor(String major){
        this.major = major;
    }
    public String getMajorLike(){
        return this.majorLike;
    }
    public void setMajorLike(String majorLike){
        this.majorLike = majorLike;
    }

    public String getInstitution(){
        return this.institution;
    }
    public void setInstitution(String institution){
        this.institution = institution;
    }
    public String getInstitutionLike(){
        return this.institutionLike;
    }
    public void setInstitutionLike(String institutionLike){
        this.institutionLike = institutionLike;
    }

    public LocalDateTime getStartDate(){
        return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate){
        this.startDate = startDate;
    }
    public LocalDateTime getStartDateFrom(){
        return this.startDateFrom;
    }
    public void setStartDateFrom(LocalDateTime startDateFrom){
        this.startDateFrom = startDateFrom;
    }
    public LocalDateTime getStartDateTo(){
        return this.startDateTo;
    }
    public void setStartDateTo(LocalDateTime startDateTo){
        this.startDateTo = startDateTo;
    }
    public LocalDateTime getEndDate(){
        return this.endDate;
    }
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    public LocalDateTime getEndDateFrom(){
        return this.endDateFrom;
    }
    public void setEndDateFrom(LocalDateTime endDateFrom){
        this.endDateFrom = endDateFrom;
    }
    public LocalDateTime getEndDateTo(){
        return this.endDateTo;
    }
    public void setEndDateTo(LocalDateTime endDateTo){
        this.endDateTo = endDateTo;
    }

}
