package ma.zyn.app.service.impl.admin.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.Experience;
import ma.zyn.app.dao.criteria.core.student.ExperienceCriteria;
import ma.zyn.app.dao.facade.core.student.ExperienceDao;
import ma.zyn.app.dao.specification.core.student.ExperienceSpecification;
import ma.zyn.app.service.facade.admin.student.ExperienceAdminService;
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


import java.util.List;
@Service
public class ExperienceAdminServiceImpl implements ExperienceAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Experience update(Experience t) {
        Experience loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Experience.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Experience findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Experience findOrSave(Experience t) {
        if (t != null) {
            Experience result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Experience> findAll() {
        return dao.findAll();
    }

    public List<Experience> findByCriteria(ExperienceCriteria criteria) {
        List<Experience> content = null;
        if (criteria != null) {
            ExperienceSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ExperienceSpecification constructSpecification(ExperienceCriteria criteria) {
        ExperienceSpecification mySpecification =  (ExperienceSpecification) RefelexivityUtil.constructObjectUsingOneParam(ExperienceSpecification.class, criteria);
        return mySpecification;
    }

    public List<Experience> findPaginatedByCriteria(ExperienceCriteria criteria, int page, int pageSize, String order, String sortField) {
        ExperienceSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ExperienceCriteria criteria) {
        ExperienceSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Experience> delete(List<Experience> list) {
		List<Experience> result = new ArrayList();
        if (list != null) {
            for (Experience t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Experience create(Experience t) {
        Experience loaded = findByReferenceEntity(t);
        Experience saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Experience findWithAssociatedLists(Long id){
        Experience result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Experience> update(List<Experience> ts, boolean createIfNotExist) {
        List<Experience> result = new ArrayList<>();
        if (ts != null) {
            for (Experience t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Experience loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Experience t, Experience loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Experience findByReferenceEntity(Experience t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Experience> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Experience>> getToBeSavedAndToBeDeleted(List<Experience> oldList, List<Experience> newList) {
        List<List<Experience>> result = new ArrayList<>();
        List<Experience> resultDelete = new ArrayList<>();
        List<Experience> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Experience> oldList, List<Experience> newList, List<Experience> resultUpdateOrSave, List<Experience> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Experience myOld = oldList.get(i);
                Experience t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Experience myNew = newList.get(i);
                Experience t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public ExperienceAdminServiceImpl(ExperienceDao dao) {
        this.dao = dao;
    }

    private ExperienceDao dao;
}
