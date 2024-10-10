package ma.zyn.app.service.impl.admin.student;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.student.Education;
import ma.zyn.app.dao.criteria.core.student.EducationCriteria;
import ma.zyn.app.dao.facade.core.student.EducationDao;
import ma.zyn.app.dao.specification.core.student.EducationSpecification;
import ma.zyn.app.service.facade.admin.student.EducationAdminService;
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
public class EducationAdminServiceImpl implements EducationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Education update(Education t) {
        Education loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Education.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Education findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Education findOrSave(Education t) {
        if (t != null) {
            Education result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Education> findAll() {
        return dao.findAll();
    }

    public List<Education> findByCriteria(EducationCriteria criteria) {
        List<Education> content = null;
        if (criteria != null) {
            EducationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EducationSpecification constructSpecification(EducationCriteria criteria) {
        EducationSpecification mySpecification =  (EducationSpecification) RefelexivityUtil.constructObjectUsingOneParam(EducationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Education> findPaginatedByCriteria(EducationCriteria criteria, int page, int pageSize, String order, String sortField) {
        EducationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EducationCriteria criteria) {
        EducationSpecification mySpecification = constructSpecification(criteria);
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
    public List<Education> delete(List<Education> list) {
		List<Education> result = new ArrayList();
        if (list != null) {
            for (Education t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Education create(Education t) {
        Education loaded = findByReferenceEntity(t);
        Education saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Education findWithAssociatedLists(Long id){
        Education result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Education> update(List<Education> ts, boolean createIfNotExist) {
        List<Education> result = new ArrayList<>();
        if (ts != null) {
            for (Education t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Education loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Education t, Education loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Education findByReferenceEntity(Education t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }



    public List<Education> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Education>> getToBeSavedAndToBeDeleted(List<Education> oldList, List<Education> newList) {
        List<List<Education>> result = new ArrayList<>();
        List<Education> resultDelete = new ArrayList<>();
        List<Education> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Education> oldList, List<Education> newList, List<Education> resultUpdateOrSave, List<Education> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Education myOld = oldList.get(i);
                Education t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Education myNew = newList.get(i);
                Education t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EducationAdminServiceImpl(EducationDao dao) {
        this.dao = dao;
    }

    private EducationDao dao;
}
