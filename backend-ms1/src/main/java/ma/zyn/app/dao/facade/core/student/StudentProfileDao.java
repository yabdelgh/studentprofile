package ma.zyn.app.dao.facade.core.student;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.student.StudentProfile;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StudentProfileDao extends AbstractRepository<StudentProfile,Long>  {

    List<StudentProfile> findByEducationId(Long id);
    int deleteByEducationId(Long id);
    long countByEducationId(Long id);


}
