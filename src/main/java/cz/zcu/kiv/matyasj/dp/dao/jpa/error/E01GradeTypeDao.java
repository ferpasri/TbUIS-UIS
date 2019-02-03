package cz.zcu.kiv.matyasj.dp.dao.jpa.error;

import cz.zcu.kiv.matyasj.dp.annotations.ErrorMethod;
import cz.zcu.kiv.matyasj.dp.dao.GradeTypeDao;
import cz.zcu.kiv.matyasj.dp.dao.jpa.correct.GenericDaoJpa;
import cz.zcu.kiv.matyasj.dp.domain.university.GradeType;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Objects of this class represent <b>ERROR</b> DAOs for manipulation with Grade types in system database.
 * Objects are able to finding/saving/deleting Grades.
 * This DAO Objects uses JPA Criteria API.
 *
 * @author Jiri Matyas
 */
//@Repository
public class E01GradeTypeDao extends GenericDaoJpa<GradeType, Long> implements GradeTypeDao {
    /** Shared system logger */
    private final Logger log = Logger.getLogger(getClass());

    /**
     *  E01GradeTypeDao constructor
     *
     * @param em Entity Manager for communication with database
     */
    public E01GradeTypeDao(EntityManager em) {
        super(em, GradeType.class);
    }

    /**
     *  Base E01GradeTypeDao constructor
     *
     */
    public E01GradeTypeDao(){
        super(GradeType.class);
    }

    /**
     * DELIBERATE ERROR
     *
     * This error method returns always type of grades 1, 2, 3, 4
     *
     * @return list of grade types
     */
    @Override
    @ErrorMethod(errorMessage = "This method return bad grade types")
    public List<GradeType> getAllGradeTypes() {
        GradeType A = new GradeType();
        A.setName("1");
        A.setId(100L);

        GradeType B = new GradeType();
        B.setName("2");
        B.setId(101L);

        GradeType C = new GradeType();
        C.setName("3");
        C.setId(102L);

        GradeType D = new GradeType();
        D.setName("4");
        D.setId(103L);

        List<GradeType> resultList = new ArrayList<>();
        resultList.add(A);
        resultList.add(B);
        resultList.add(C);
        resultList.add(D);

        log.error("Deliberate error: GetAllGradeTypes method returns always static list of grade types: 1, 2, 3, 4!");
        return resultList;
    }
}

