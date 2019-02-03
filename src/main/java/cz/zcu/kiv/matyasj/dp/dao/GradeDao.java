package cz.zcu.kiv.matyasj.dp.dao;

import cz.zcu.kiv.matyasj.dp.domain.university.ExaminationDate;
import cz.zcu.kiv.matyasj.dp.domain.university.Grade;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Student;

import java.util.Date;
import java.util.List;

/**
 * This interface defines methods of DAOs for manipulation with Grade data in database.
 *
 * @author Jiri Matyas
 */
public interface GradeDao extends GenericDao<Grade, Long>{
    /**
     * Finds and returns list of grades created in one subject.
     *
     * @param subject For this subject will be grades found.
     * @return List of grades
     */
    List<Grade> findGradesBySubject(Subject subject);

    /**
     * Finds and returns list of grades of one student.
     *
     * @param student For this Student will be grades found.
     * @return List of grades
     */
    List<Grade> findGradesByStudent(Student student);

    Grade findGradeByStudentAndSubjectAndDate(Student student, Subject subject, Date date);
}
