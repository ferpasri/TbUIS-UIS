package cz.zcu.kiv.matyasj.dp.dao.jpa.correct;

import cz.zcu.kiv.matyasj.dp.dao.GradeTypeDao;
import cz.zcu.kiv.matyasj.dp.domain.university.GradeType;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Objects of this class represent DAOs for manipulation with Grade types in system database.
 * Objects are able to finding/saving/deleting Grades.
 * This DAO Objects uses JPA Criteria API.
 *
 * @author Jiri Matyas
 */
//@Repository
public class GradeTypeDaoCriteria extends GenericDaoJpa<GradeType, Long> implements GradeTypeDao {

    /**
     *  GradeTypeDaoCriteria constructor
     *
     * @param em Entity Manager for communication with database
     */
    public GradeTypeDaoCriteria(EntityManager em) {
        super(em, GradeType.class);
    }

    /**
     *  Base GradeDaoCriteria constructor
     *
     */
    public GradeTypeDaoCriteria(){
        super(GradeType.class);
    }

    /**
     * This method returns all type of grades in system (e.g. typically A, B, C, D, E, F).
     *
     * @return list of grade types
     */
    @Override
    public List<GradeType> getAllGradeTypes() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<GradeType> query = cb.createQuery(GradeType.class);
        Root<GradeType> root = query.from(GradeType.class);

        query.select(root);

        return findAll();
    }
}
