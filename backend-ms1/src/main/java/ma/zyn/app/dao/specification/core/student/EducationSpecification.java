package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.EducationCriteria;
import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class EducationSpecification extends  AbstractSpecification<EducationCriteria, Education>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("degree", criteria.getDegree(),criteria.getDegreeLike());
        addPredicate("major", criteria.getMajor(),criteria.getMajorLike());
        addPredicate("institution", criteria.getInstitution(),criteria.getInstitutionLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
    }

    public EducationSpecification(EducationCriteria criteria) {
        super(criteria);
    }

    public EducationSpecification(EducationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
