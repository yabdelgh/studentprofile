package ma.zyn.app.service.facade.admin.student;

import java.util.List;
import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.dao.criteria.core.student.ExperienceCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ExperienceAdminService {







	Experience create(Experience t);

    Experience update(Experience t);

    List<Experience> update(List<Experience> ts,boolean createIfNotExist);

    Experience findById(Long id);

    Experience findOrSave(Experience t);

    Experience findByReferenceEntity(Experience t);

    Experience findWithAssociatedLists(Long id);

    List<Experience> findAllOptimized();

    List<Experience> findAll();

    List<Experience> findByCriteria(ExperienceCriteria criteria);

    List<Experience> findPaginatedByCriteria(ExperienceCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ExperienceCriteria criteria);

    List<Experience> delete(List<Experience> ts);

    boolean deleteById(Long id);

    List<List<Experience>> getToBeSavedAndToBeDeleted(List<Experience> oldList, List<Experience> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
