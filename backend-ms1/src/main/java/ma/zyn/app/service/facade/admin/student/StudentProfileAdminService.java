package ma.zyn.app.service.facade.admin.student;

import java.util.List;
import ma.zyn.app.bean.core.student.StudentProfile;
import ma.zyn.app.dao.criteria.core.student.StudentProfileCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface StudentProfileAdminService {



    List<StudentProfile> findByEducationId(Long id);
    int deleteByEducationId(Long id);
    long countByEducationId(Long id);




	StudentProfile create(StudentProfile t);

    StudentProfile update(StudentProfile t);

    List<StudentProfile> update(List<StudentProfile> ts,boolean createIfNotExist);

    StudentProfile findById(Long id);

    StudentProfile findOrSave(StudentProfile t);

    StudentProfile findByReferenceEntity(StudentProfile t);

    StudentProfile findWithAssociatedLists(Long id);

    List<StudentProfile> findAllOptimized();

    List<StudentProfile> findAll();

    List<StudentProfile> findByCriteria(StudentProfileCriteria criteria);

    List<StudentProfile> findPaginatedByCriteria(StudentProfileCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StudentProfileCriteria criteria);

    List<StudentProfile> delete(List<StudentProfile> ts);

    boolean deleteById(Long id);

    List<List<StudentProfile>> getToBeSavedAndToBeDeleted(List<StudentProfile> oldList, List<StudentProfile> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
