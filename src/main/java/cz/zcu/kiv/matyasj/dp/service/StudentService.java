package cz.zcu.kiv.matyasj.dp.service;

import cz.zcu.kiv.matyasj.dp.domain.university.ExaminationDate;
import cz.zcu.kiv.matyasj.dp.domain.university.Grade;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Student;

import java.util.List;

/**
 * This interface defines methods of the service objects providing functions for manipulation with data
 * related to students.
 *
 * @author Jiri Matyas
 */
public interface StudentService extends UserService {
    /**
     *  This method returns list of subjects that are enrolled by
     *  one particular student.
     *
     * @param studentId database student id
     * @return List of studied subjects
     */
    List<Subject> getStudiedSubjectsList(Long studentId);

    /**
     *  This method returns list of subjects that one particular
     *  student absolved.
     *
     * @param studentId database student id
     * @return List of absolved subjects
     */
    List<Subject> getAbsolvedSubjectsList(Long studentId);

    /**
     * This method returns list of subjects that one particular
     * student NOT studies or did not graduated.
     *
     * @param studentId database student id
     * @return List of non-studied subjects
     */
    List<Subject> getOtherSubjectsList(Long studentId);

    /**
     *  This method returns list of subjects that one particular student
     *  studies. Students are enrolled for the
     *  date of examination for these subjects.
     *
     * @param studentId database student id
     * @return List of studied subjects
     */
    List<Subject> getSubjectsWithRegisteredExamDate(Long studentId);

    /**
     * Get list of all exam dates registered by one particular student.
     *
     * @param studentId database student id
     * @return List of students examination dates
     */
    List<ExaminationDate> getExaminationDatesList(Long studentId);

    /**
     * This method returns a list of all the exam dates not registered
     * by one particular student.
     *
     * @param studentId database student id
     * @return List of students examination dates
     */
    List<ExaminationDate> getNotRegisteredExaminationDatesList(Long studentId);

    /**
     * Get list of exam dates registered by one particular student. In list
     * are not included exam dates with evaluation for particular student.
     *
     * @param studentId database student id
     * @return List of students examination dates
     */
    List<ExaminationDate> getStudentExaminationDatesList(Long studentId);

    /**
     * This method returns list of grades of one particular student.
     *
     * @param student student object
     * @return list of student's grades/evaluation
     */
    List<Grade> getStudentGrades(Student student);

    /**
     * This method enrolls subject for the student.
     *
     * @param studentId database id of student
     * @param subjectId database id of subject
     * @return true if subject was successfully unregistered, false otherwise.
     */
    boolean setStudiedSubject(Long studentId, Long subjectId);

    /**
     * This method unenrolls subject for the student.
     *
     * @param studentId database id of student
     * @param subjectId database id of subject
     * @return true if subject was successfully unenrolled, false otherwise.
     */
    boolean unsetStudiedSubject(Long studentId, Long subjectId);

    /**
     * This method registers student on specific examination date.
     *
     * @param studentId database id of student
     * @param examDateId database id of exam date
     * @return true if examination date was successfully registered, false otherwise.
     */
    boolean setExaminationDate(Long studentId, Long examDateId);

    /**
     * This method unregisters student on specific examination date.
     *
     * @param studentId database id of student
     * @param examDateId database id of exam date
     * @return true if examination date was successfully unregistered, false otherwise.
     */
    boolean unsetExaminationDate(Long studentId, Long examDateId);

    /**
     * This method returns number of obtained credits.
     *
     * @param studentId database id of student
     * @return number of obtained credits.
     */
    int getStudentTotalCredits(Long studentId);
}
