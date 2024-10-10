package ma.zyn.app.service.facade.admin.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.dao.criteria.core.student.EducationCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EducationAdminService {







	Education create(Education t);

    Education update(Education t);

    List<Education> update(List<Education> ts,boolean createIfNotExist);

    Education findById(Long id);

    Education findOrSave(Education t);

    Education findByReferenceEntity(Education t);

    Education findWithAssociatedLists(Long id);

    List<Education> findAllOptimized();

    List<Education> findAll();

    List<Education> findByCriteria(EducationCriteria criteria);

    List<Education> findPaginatedByCriteria(EducationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EducationCriteria criteria);

    List<Education> delete(List<Education> ts);

    boolean deleteById(Long id);

    List<List<Education>> getToBeSavedAndToBeDeleted(List<Education> oldList, List<Education> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
