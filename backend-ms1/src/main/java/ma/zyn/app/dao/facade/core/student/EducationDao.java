package ma.zyn.app.dao.facade.core.student;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.Education;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EducationDao extends AbstractRepository<Education,Long>  {



}
