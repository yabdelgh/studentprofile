package  ma.zyn.app.dao.specification.core.student;

import ma.zyn.app.dao.criteria.core.student.StudentProfileCriteria;
import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class StudentProfileSpecification extends  AbstractSpecification<StudentProfileCriteria, StudentProfile>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("firstName", criteria.getFirstName(),criteria.getFirstNameLike());
        addPredicate("lastName", criteria.getLastName(),criteria.getLastNameLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicate("phoneNumber", criteria.getPhoneNumber(),criteria.getPhoneNumberLike());
        addPredicate("imageUrl", criteria.getImageUrl(),criteria.getImageUrlLike());
        addPredicate("interests", criteria.getInterests(),criteria.getInterestsLike());
        addPredicate("skills", criteria.getSkills(),criteria.getSkillsLike());
        addPredicateFk("education","id", criteria.getEducation()==null?null:criteria.getEducation().getId());
        addPredicateFk("education","id", criteria.getEducations());
    }

    public StudentProfileSpecification(StudentProfileCriteria criteria) {
        super(criteria);
    }

    public StudentProfileSpecification(StudentProfileCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
