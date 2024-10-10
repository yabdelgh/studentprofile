package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.ExperienceCriteria;
import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ExperienceSpecification extends  AbstractSpecification<ExperienceCriteria, Experience>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("title", criteria.getTitle(),criteria.getTitleLike());
        addPredicate("company", criteria.getCompany(),criteria.getCompanyLike());
        addPredicate("startDate", criteria.getStartDate(), criteria.getStartDateFrom(), criteria.getStartDateTo());
        addPredicate("endDate", criteria.getEndDate(), criteria.getEndDateFrom(), criteria.getEndDateTo());
    }

    public ExperienceSpecification(ExperienceCriteria criteria) {
        super(criteria);
    }

    public ExperienceSpecification(ExperienceCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
