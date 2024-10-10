package ma.zyn.app.service.impl.admin.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.dao.criteria.core.student.StudentProfileCriteria;
import ma.zyn.app.dao.facade.core.student.StudentProfileDao;
import ma.zyn.app.dao.specification.core.student.StudentProfileSpecification;
import ma.zyn.app.service.facade.admin.student.StudentProfileAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.student.ExperienceAdminService ;
import ma.zyn.app.bean.core.student.Experience ;
import ma.zyn.app.service.facade.admin.student.EducationAdminService ;
import ma.zyn.app.bean.core.student.Education ;

import java.util.List;
@Service
public class StudentProfileAdminServiceImpl implements StudentProfileAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StudentProfile update(StudentProfile t) {
        StudentProfile loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StudentProfile.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public StudentProfile findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StudentProfile findOrSave(StudentProfile t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            StudentProfile result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StudentProfile> findAll() {
        return dao.findAll();
    }

    public List<StudentProfile> findByCriteria(StudentProfileCriteria criteria) {
        List<StudentProfile> content = null;
        if (criteria != null) {
            StudentProfileSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StudentProfileSpecification constructSpecification(StudentProfileCriteria criteria) {
        StudentProfileSpecification mySpecification =  (StudentProfileSpecification) RefelexivityUtil.constructObjectUsingOneParam(StudentProfileSpecification.class, criteria);
        return mySpecification;
    }

    public List<StudentProfile> findPaginatedByCriteria(StudentProfileCriteria criteria, int page, int pageSize, String order, String sortField) {
        StudentProfileSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StudentProfileCriteria criteria) {
        StudentProfileSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<StudentProfile> findByEducationId(Long id){
        return dao.findByEducationId(id);
    }
    public int deleteByEducationId(Long id){
        return dao.deleteByEducationId(id);
    }
    public long countByEducationId(Long id){
        return dao.countByEducationId(id);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        experienceService.deleteByStudentProfileId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StudentProfile> delete(List<StudentProfile> list) {
		List<StudentProfile> result = new ArrayList();
        if (list != null) {
            for (StudentProfile t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StudentProfile create(StudentProfile t) {
        StudentProfile loaded = findByReferenceEntity(t);
        StudentProfile saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getExperience() != null) {
                t.getExperience().forEach(element-> {
                    element.setStudentProfile(saved);
                    experienceService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public StudentProfile findWithAssociatedLists(Long id){
        StudentProfile result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setExperience(experienceService.findByStudentProfileId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StudentProfile> update(List<StudentProfile> ts, boolean createIfNotExist) {
        List<StudentProfile> result = new ArrayList<>();
        if (ts != null) {
            for (StudentProfile t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StudentProfile loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StudentProfile t, StudentProfile loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(StudentProfile studentProfile){
    if(studentProfile !=null && studentProfile.getId() != null){
        List<List<Experience>> resultExperience= experienceService.getToBeSavedAndToBeDeleted(experienceService.findByStudentProfileId(studentProfile.getId()),studentProfile.getExperience());
            experienceService.delete(resultExperience.get(1));
        emptyIfNull(resultExperience.get(0)).forEach(e -> e.setStudentProfile(studentProfile));
        experienceService.update(resultExperience.get(0),true);
        }
    }








    public StudentProfile findByReferenceEntity(StudentProfile t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(StudentProfile t){
        if( t != null) {
            t.setEducation(educationService.findOrSave(t.getEducation()));
        }
    }



    public List<StudentProfile> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<StudentProfile>> getToBeSavedAndToBeDeleted(List<StudentProfile> oldList, List<StudentProfile> newList) {
        List<List<StudentProfile>> result = new ArrayList<>();
        List<StudentProfile> resultDelete = new ArrayList<>();
        List<StudentProfile> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<StudentProfile> oldList, List<StudentProfile> newList, List<StudentProfile> resultUpdateOrSave, List<StudentProfile> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StudentProfile myOld = oldList.get(i);
                StudentProfile t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StudentProfile myNew = newList.get(i);
                StudentProfile t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private ExperienceAdminService experienceService ;
    @Autowired
    private EducationAdminService educationService ;

    public StudentProfileAdminServiceImpl(StudentProfileDao dao) {
        this.dao = dao;
    }

    private StudentProfileDao dao;
}
