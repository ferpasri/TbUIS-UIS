package cz.zcu.kiv.matyasj.dp.service.users;

import cz.zcu.kiv.matyasj.dp.dao.ExaminationDateDao;
import cz.zcu.kiv.matyasj.dp.dao.GradeDao;
import cz.zcu.kiv.matyasj.dp.dao.SubjectDao;
import cz.zcu.kiv.matyasj.dp.dao.UserDao;
import cz.zcu.kiv.matyasj.dp.domain.university.ExaminationDate;
import cz.zcu.kiv.matyasj.dp.domain.university.Grade;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Student;
import cz.zcu.kiv.matyasj.dp.domain.users.Teacher;
import cz.zcu.kiv.matyasj.dp.service.StudentService;
import cz.zcu.kiv.matyasj.dp.utils.properties.PropertyLoader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * StudentService test suite (tests all student business logic of application)
 *
 * @author Jiri Matyas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class StudentServiceTest {
    @Autowired
    StudentService studentService;

    @Autowired
    UserDao userDao;

    @Autowired
    SubjectDao subjectDao;

    @Autowired
    ExaminationDateDao examinationDateDao;

    @Autowired
    GradeDao gradeDao;

    @Autowired
    PropertyLoader propertyLoader;

    private Teacher testTeacher1;
    private Student testStudent1;
    private Student testStudent2;

    private Subject testSubject1;
    private Subject testSubject2;

    private ExaminationDate testExaminationDate1;

    private Grade testGrade1;


    /**
     * SetUp method creates all needed testing data.
     */
    @Before
    public void setUp() {
        testTeacher1 = new Teacher("Peter", "Pedant", "testTeacher", "password", "test@mail.com");
        testStudent1 = new Student("John", "Doe", "testStudent", "password", "testS@mail.com");
        testStudent2 = new Student("Johnathan", "Does", "jdoes", "password", "testS@mail.com");

        testSubject1 = new Subject("Test subject 1", 1);
        testSubject2 = new Subject("Test subject 2", 1);

        testExaminationDate1 = new ExaminationDate(new Date(new Date().getTime() + 500000), 5);

        testGrade1 = new Grade();
        testGrade1.setDayOfGrant(new Date());


        testStudent1 = (Student) userDao.save(testStudent1);
        testStudent2 = (Student) userDao.save(testStudent2);
        testTeacher1 = (Teacher) userDao.save(testTeacher1);

        testSubject1 = subjectDao.save(testSubject1);
        testSubject2 = subjectDao.save(testSubject2);

        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        testGrade1 = gradeDao.save(testGrade1);
    }

    /**
     * SetUp method removes all testing data.
     */
    @After
    public void tearDown() {
        userDao.delete(testTeacher1.getId());
        userDao.delete(testStudent1.getId());
        userDao.delete(testStudent2.getId());

        subjectDao.delete(testSubject1.getId());
        subjectDao.delete(testSubject2.getId());

        examinationDateDao.delete(testExaminationDate1.getId());

        gradeDao.delete(testGrade1.getId());
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student.
     */
    @Test
    public void getStudiedSubjectsList() {
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        List<Subject> subjectList = studentService.getStudiedSubjectsList(testStudent1.getId());

        assertNotNull(subjectList);
        assertTrue(subjectList instanceof List);
        assertEquals(1, subjectList.size());

        testStudent1.getListOfAbsolvedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        subjectList = studentService.getStudiedSubjectsList(testStudent1.getId());
        assertEquals(1, subjectList.size());
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student (no results returned - empty list).
     */
    @Test
    public void getStudiedSubjectsListNoSubjects() {
        List<Subject> subjectList = studentService.getStudiedSubjectsList(testStudent1.getId());

        assertNotNull(subjectList);
        assertTrue(subjectList instanceof List);
        assertTrue(subjectList.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student (non existent student).
     */
    @Test
    public void getStudiedSubjectsListNonExistentStudent() {
        List<Subject> subjectList = studentService.getStudiedSubjectsList(-1L);

        assertNull(subjectList);
    }

    /**
     * This method tests StudentService function - Getting list of absolved subjects for one particular student.
     */
    @Test
    public void getAbsolvedSubjectsList() {
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        List<Subject> subjectList = studentService.getAbsolvedSubjectsList(testStudent1.getId());

        assertNotNull(subjectList);
        assertTrue(subjectList instanceof List);
        assertEquals(0, subjectList.size());

        testStudent1.getListOfAbsolvedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        subjectList = studentService.getAbsolvedSubjectsList(testStudent1.getId());
        assertEquals(1, subjectList.size());
    }

    /**
     * This method tests StudentService function - Getting list of absolved subjects for one particular student (no results returned - empty list).
     */
    @Test
    public void getAbsolvedSubjectsListNoSubjects() {
        List<Subject> subjectList = studentService.getAbsolvedSubjectsList(testStudent1.getId());

        assertNotNull(subjectList);
        assertTrue(subjectList instanceof List);
        assertTrue(subjectList.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of absolved subjects for one particular student (non existent student).
     */
    @Test
    public void getAbsolvedSubjectsListNonExistentStudent() {
        List<Subject> subjectList = studentService.getAbsolvedSubjectsList(-1L);
        assertNull(subjectList);
    }

    /**
     * This method tests StudentService function - Getting list of other subjects for one particular student.
     */
    @Test
    public void getOtherSubjectsList() {
        List<Subject> otherSubjects = subjectDao.findAll();
        List<Subject> otherSubjectsTested = studentService.getOtherSubjectsList(testStudent1.getId());

        assertEquals(otherSubjects.size(), otherSubjectsTested.size());

        // Add study subject
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        otherSubjectsTested = studentService.getOtherSubjectsList(testStudent1.getId());

        assertEquals(otherSubjects.size(), otherSubjectsTested.size() + 1);

        // Add graduated subject
        testStudent1.getListOfAbsolvedSubjects().add(testSubject2);
        testStudent1 = (Student) userDao.save(testStudent1);

        otherSubjectsTested = studentService.getOtherSubjectsList(testStudent1.getId());

        assertEquals(otherSubjects.size(), otherSubjectsTested.size() + 2);
    }

    /**
     * This method tests StudentService function - Getting list of absolved subjects for one particular student (non existent student).
     */
    @Test
    public void getOtherSubjectsListNonExistentStudent() {
        List<Subject> subjectList = studentService.getOtherSubjectsList(-1L);
        assertNull(subjectList);
    }

    /**
     * This method tests StudentService function - Getting list of absolved subjects for one particular student (no results returned - empty list).
     */
    @Test
    public void getOtherSubjectsListNoOtherSubjects() {
        List<Subject> subjectList = subjectDao.findAll();

        for(Subject s : subjectList){
            testStudent1.getListOfLearnedSubjects().add(s);
        }
        testStudent1 = (Student) userDao.save(testStudent1);

        subjectList = studentService.getOtherSubjectsList(testStudent1.getId());

        assertNotNull(subjectList);
        assertTrue(subjectList instanceof List);
        assertTrue(subjectList.isEmpty());

        while(!testStudent1.getListOfLearnedSubjects().isEmpty()){
            testStudent1.getListOfLearnedSubjects().remove(0);
        }
        userDao.save(testStudent1);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates for one particular student.
     */
    @Test
    public void getExaminationTermsList() {
        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        List<ExaminationDate> examinationDateList = studentService.getExaminationDatesList(testStudent1.getId());

        assertNotNull(examinationDateList);
        assertTrue(examinationDateList instanceof List);
        assertEquals(1, examinationDateList.size());

        testExaminationDate1.getParticipants().remove(0);
        examinationDateDao.save(testExaminationDate1);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates for one particular student (no results returned - empty list).
     */
    @Test
    public void getExaminationTermsListNoExamTerms() {
        List<ExaminationDate> examinationDateList = studentService.getExaminationDatesList(testStudent1.getId());

        assertNotNull(examinationDateList);
        assertTrue(examinationDateList instanceof List);
        assertTrue(examinationDateList.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of examination dates for one particular student (teacher instead of student in function parameter).
     */
    @Test
    public void getExaminationTermsListForTeacher() {
        List<ExaminationDate> examinationDateList = studentService.getExaminationDatesList(testTeacher1.getId());

        assertNull(examinationDateList);
    }

    /**
     * This method tests StudentService function - Getting list of non registered examination dates for one particular student.
     */
    @Test
    public void getNotRegisteredExaminationTermsListTest(){
        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        List<ExaminationDate> notRegisteredExaminationTermsOfStudentBefore = studentService.getNotRegisteredExaminationDatesList(testStudent1.getId());

        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        List<ExaminationDate> notRegisteredExaminationTermsOfStudentAfter = studentService.getNotRegisteredExaminationDatesList(testStudent1.getId());

        assertNotNull(notRegisteredExaminationTermsOfStudentBefore);
        assertNotNull(notRegisteredExaminationTermsOfStudentAfter);

        assertTrue(notRegisteredExaminationTermsOfStudentBefore instanceof List);
        assertTrue(notRegisteredExaminationTermsOfStudentAfter instanceof List);

        int beforeListSize = notRegisteredExaminationTermsOfStudentBefore.size();
        int afterListSize = notRegisteredExaminationTermsOfStudentAfter.size();

        testExaminationDate1.setSubject(null);
        testExaminationDate1.getParticipants().remove(0);
        examinationDateDao.save(testExaminationDate1);

        assertEquals(beforeListSize, (afterListSize + 1));
    }

    /**
     * This method tests StudentService function - Getting list of non registered examination dates for one particular student (non existent student).
     */
    @Test
    public void getNotRegisteredExaminationTermsListTestNotExistentStudent(){
        List<ExaminationDate> notRegisteredExaminationTermsOfStudent = studentService.getNotRegisteredExaminationDatesList(-1L);

        assertNull(notRegisteredExaminationTermsOfStudent);
    }

    /**
     * This method tests StudentService function - Getting list of non registered examination dates for one particular student (teacher  instead of student).
     */
    @Test
    public void getNotRegisteredExaminationTermsListTestForTeacher(){
        List<ExaminationDate> notRegisteredExaminationTermsOfStudent = studentService.getNotRegisteredExaminationDatesList(testTeacher1.getId());

        assertNull(notRegisteredExaminationTermsOfStudent);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates (without examination dates of
     * already evaluated subject) for one particular student.
     */
    @Test
    public void getStudentExaminationTermsList() {
        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        List<ExaminationDate> examinationDateList = studentService.getStudentExaminationDatesList(testStudent1.getId());

        assertNotNull(examinationDateList);
        assertTrue(examinationDateList instanceof List);
        assertTrue(examinationDateList.size() == 1);
        assertEquals(testExaminationDate1.getId(), examinationDateList.get(0).getId());

        testExaminationDate1.getParticipants().remove(0);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates (without examination dates of
     * already evaluated subject) for one particular student with absolved subject.
     */
    @Test
    public void getStudentExaminationTermsListGraduatedSubject() {
        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);


        Grade newGrade = new Grade();
        newGrade = gradeDao.save(newGrade);
        newGrade.setOwner(testStudent1);
        newGrade.setSubject(testSubject1);
        newGrade = gradeDao.save(newGrade);

        List<ExaminationDate> examinationDateList = studentService.getStudentExaminationDatesList(testStudent1.getId());

        assertNotNull(examinationDateList);
        assertTrue(examinationDateList instanceof List);
        assertTrue(examinationDateList.isEmpty());

        testExaminationDate1.getParticipants().remove(0);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        newGrade.setSubject(null);
        newGrade.setOwner(null);
        gradeDao.save(newGrade);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates (without examination dates of
     * already evaluated subject) for one particular student (non existent student).
     */
    @Test
    public void getStudentExaminationTermsListNonExistentStudent() {
        List<ExaminationDate> examinationDateList = studentService.getStudentExaminationDatesList(-1L);

        assertNull(examinationDateList);
    }

    /**
     * This method tests StudentService function - Getting list of examination dates (without examination dates of
     * already evaluated subject) for one particular student (no results returned - empty list).
     */
    @Test
    public void getStudentExaminationTermsListEmptyList() {
        List<ExaminationDate> examinationDateList = studentService.getStudentExaminationDatesList(testStudent1.getId());

        assertNotNull(examinationDateList);
        assertTrue(examinationDateList instanceof List);
        assertTrue(examinationDateList.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of grades for one particular student.
     */
    @Test
    public void getStudentGrades() {
        testGrade1.setOwner(testStudent1);
        testGrade1 = gradeDao.save(testGrade1);

        List<Grade> studentGrades = studentService.getStudentGrades(testStudent1);

        assertNotNull(studentGrades);
        assertTrue(studentGrades instanceof List);
        assertEquals(1, studentGrades.size());
        assertEquals(testGrade1.getId(), studentGrades.get(0).getId());

        testGrade1.setOwner(null);
        testGrade1 = gradeDao.save(testGrade1);
    }

    /**
     * This method tests StudentService function - Getting list of grades for one particular student (non existent student).
     */
    @Test
    public void getStudentGradesNonExistentStudent() {
        List<Grade> studentGrades = studentService.getStudentGrades(null);

        assertNull(studentGrades);
    }

    /**
     * This method tests StudentService function - Getting list of grades for one particular student (no results returned - empty list).
     */
    @Test
    public void getStudentGradesEmptyList() {
        List<Grade> studentGrades = studentService.getStudentGrades(testStudent1);

        assertNotNull(studentGrades);
        assertTrue(studentGrades instanceof List);
        assertTrue(studentGrades.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student.
     */
    @Test
    public void getSubjectWithRegisteredExamTerm() {
        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        List<Subject> registeredExamTermsSubjects = studentService.getSubjectsWithRegisteredExamDate(testStudent1.getId());

        assertNotNull(registeredExamTermsSubjects);
        assertTrue(registeredExamTermsSubjects instanceof List);
        assertEquals(1, registeredExamTermsSubjects.size());
        assertEquals(testSubject1.getId(), registeredExamTermsSubjects.get(0).getId());

        testExaminationDate1.setSubject(null);
        testExaminationDate1.getParticipants().remove(0);
        examinationDateDao.save(testExaminationDate1);
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student (no results returned - empty list).
     */
    @Test
    public void getSubjectWithRegisteredExamTermEmptyList() {
        List<Subject> registeredExamTermsSubjects = studentService.getSubjectsWithRegisteredExamDate(testStudent1.getId());

        assertNotNull(registeredExamTermsSubjects);
        assertTrue(registeredExamTermsSubjects instanceof List);
        assertTrue(registeredExamTermsSubjects.isEmpty());
    }

    /**
     * This method tests StudentService function - Getting list of studied subjects for one particular student (non existent student - null returned).
     */
    @Test
    public void getSubjectWithRegisteredExamTermNonExistentStudent() {
        List<Subject> registeredExamTermsSubjects = studentService.getSubjectsWithRegisteredExamDate(-1L);

        assertNull(registeredExamTermsSubjects);
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student.
     */
    @Test
    public void setStudiedSubject() {
        testStudent1 = (Student) userDao.findOne(testStudent1.getId());
        assertEquals(0, testStudent1.getListOfLearnedSubjects().size());

        boolean success = studentService.setStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertTrue(success);
        assertEquals(1, testStudent1.getListOfLearnedSubjects().size());
        assertEquals(testSubject1.getId(), testStudent1.getListOfLearnedSubjects().get(0).getId());
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (non existent subject).
     */
    @Test
    public void setStudiedSubjectNonExistentSubject() {
        boolean success = studentService.setStudiedSubject(testStudent1.getId(), -1L);

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertFalse(success);
        assertTrue(testStudent1.getListOfLearnedSubjects().isEmpty());
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (non existent student).
     */
    @Test
    public void setStudiedSubjectNonExistentStudent() {
        boolean success = studentService.setStudiedSubject(-1L, testSubject1.getId());

        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (trying to set subject twice).
     */
    @Test
    public void setStudiedSubjectSetSubjectTwice() {
        boolean success1 = studentService.setStudiedSubject(testStudent1.getId(), testSubject1.getId());
        boolean success2 = studentService.setStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertTrue(success1);
        assertFalse(success2);
        assertEquals(1, testStudent1.getListOfLearnedSubjects().size());
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (trying to set
     * more than max subjects - application properties attribute).
     */
    @Test
    public void setStudiedSubjectMoreThanMax() {
        boolean success1 = true;

        int maxSubjectsNumber = Integer.parseInt(propertyLoader.getProperty("studentMaxSubjects"));
        for(int i = 0; i < maxSubjectsNumber; i++){
            Subject tmpSubject = new Subject("Test subject "+i, i);
            tmpSubject = subjectDao.save(tmpSubject);
            studentService.setStudiedSubject(testStudent1.getId(), tmpSubject.getId());
        }

        success1 = studentService.setStudiedSubject(testStudent1.getId(), testSubject1.getId());

        assertFalse(success1);
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (trying to set
     * already absolved subject).
     */
    @Test
    public void setStudiedSubjectSetAbsolvedSubject() {
        testStudent1.getListOfAbsolvedSubjects().add(testSubject1);
        userDao.save(testStudent1);

        boolean success = studentService.setStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertFalse(success);
        assertTrue(testStudent1.getListOfLearnedSubjects().isEmpty());
    }

    /**
     * This method tests StudentService function - Setting studied subject for one particular student (teacher instead of student).
     */
    @Test
    public void setStudiedSubjectForTeacher() {
        boolean success = studentService.setStudiedSubject(testTeacher1.getId(), testSubject1.getId());
        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student.
     */
    @Test
    public void unsetStudiedSubject() {
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        boolean success = studentService.unsetStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertTrue(success);
        assertTrue(testStudent1.getListOfLearnedSubjects().isEmpty());
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student (trying to unset subject
     * with registered Exam Dates).
     */
    @Test
    public void unsetStudiedSubjectWithExamTerms() {
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1.getParticipants().add(testStudent1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        boolean success = studentService.unsetStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertTrue(success);
        assertTrue(testStudent1.getListOfLearnedSubjects().isEmpty());

        testExaminationDate1 = examinationDateDao.findOne(testExaminationDate1.getId());

        assertTrue(testExaminationDate1.getParticipants().isEmpty());

        testExaminationDate1.setSubject(null);
        examinationDateDao.save(testExaminationDate1);
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student (trying to unset
     * non registered subject).
     */
    @Test
    public void unsetStudiedSubjectNotSetSubject() {
        boolean success = studentService.unsetStudiedSubject(testStudent1.getId(), testSubject1.getId());

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertFalse(success);
        assertTrue(testStudent1.getListOfLearnedSubjects().isEmpty());
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student (trying to unset
     * non existent subject).
     */
    @Test
    public void unsetStudiedSubjectNonExistentSubject() {
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        boolean success = studentService.unsetStudiedSubject(testStudent1.getId(), -1L);

        testStudent1 = (Student) userDao.findOne(testStudent1.getId());

        assertFalse(success);
        assertEquals(1, testStudent1.getListOfLearnedSubjects().size());
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student (trying to unset
     * subject for non existent student).
     */
    @Test
    public void unsetStudiedSubjectNonExistentStudent() {

        boolean success = studentService.unsetStudiedSubject(-1L, testSubject1.getId());

        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Unsetting studied subject for one particular student (trying to unset
     * subject for a teacher).
     */
    @Test
    public void unsetStudiedSubjectForTeacher() {
        testTeacher1.getListOfTaughtSubjects().add(testSubject1);
        userDao.save(testStudent1);

        boolean success = studentService.unsetStudiedSubject(testTeacher1.getId(), testSubject1.getId());
        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Setting Examination date for on particular student.
     */
    @Test
    public void setExaminationTerm() {
        boolean success1 = studentService.setExaminationDate(testStudent1.getId(), testExaminationDate1.getId());
        assertFalse(success1);

        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        boolean success2 = studentService.setExaminationDate(testStudent1.getId(), testExaminationDate1.getId());
        assertTrue(success2);

        testExaminationDate1 = examinationDateDao.findOne(testExaminationDate1.getId());
        testExaminationDate1.setSubject(null);
        testExaminationDate1.getParticipants().remove(0);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        testStudent1.getListOfLearnedSubjects().remove(0);
        testStudent1 = (Student) userDao.save(testStudent1);
    }

    /**
     * This method tests StudentService function - Setting Examination date for on particular student (try to register
     * more participants than maximum number).
     */
    @Test
    public void setExaminationTermMaxParticipantsTest() {
        testExaminationDate1.setSubject(testSubject1);
        // Set max participant to 1 student
        testExaminationDate1.setMaxParticipants(1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);

        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        testStudent2.getListOfLearnedSubjects().add(testSubject1);
        testStudent2 = (Student) userDao.save(testStudent2);

        boolean success1 = studentService.setExaminationDate(testStudent1.getId(), testExaminationDate1.getId());
        boolean success2 = studentService.setExaminationDate(testStudent2.getId(), testExaminationDate1.getId());

        assertTrue(success1);
        assertFalse(success2);

        testExaminationDate1 = examinationDateDao.findOne(testExaminationDate1.getId());
        testExaminationDate1.setSubject(null);
        testExaminationDate1.getParticipants().remove(0);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        testStudent1.getListOfLearnedSubjects().remove(0);
        testStudent1 = (Student) userDao.save(testStudent1);

        testStudent2.getListOfLearnedSubjects().remove(0);
        testStudent2 = (Student) userDao.save(testStudent2);
    }

    /**
     * This method tests StudentService function - Setting Examination date for on particular student (try to register
     * one student twice).
     */
    @Test
    public void setExaminationTermTwice() {
        testExaminationDate1.setSubject(testSubject1);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        testStudent1.getListOfLearnedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        boolean success1 = studentService.setExaminationDate(testStudent1.getId(), testExaminationDate1.getId());
        boolean success2 = studentService.setExaminationDate(testStudent1.getId(), testExaminationDate1.getId());

        assertTrue(success1);
        assertFalse(success2);

        testExaminationDate1 = examinationDateDao.findOne(testExaminationDate1.getId());
        testExaminationDate1.setSubject(null);
        testExaminationDate1.getParticipants().remove(0);
        testExaminationDate1 = examinationDateDao.save(testExaminationDate1);
        testStudent1.getListOfLearnedSubjects().remove(0);
        testStudent1 = (Student) userDao.save(testStudent1);
    }

    /**
     * This method tests StudentService function - Setting Examination date for on particular student (try to register
     * non existent student).
     */
    @Test
    public void setExaminationTermNonExistentStudent() {
        boolean success = studentService.setExaminationDate(-1L, testExaminationDate1.getId());
        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Setting Examination date for on particular student (try to register
     * student on non existent Examination date).
     */
    @Test
    public void setExaminationTermNonExistentExamTerm() {
        boolean success = studentService.setExaminationDate(testStudent1.getId(), -1L);
        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Unsetting Examination date for on particular student.
     */
    @Test
    public void unsetExaminationTerm() {
        boolean success1 = studentService.unsetExaminationDate(testStudent1.getId(), testExaminationDate1.getId());

        testExaminationDate1.getParticipants().add(testStudent1);
        examinationDateDao.save(testExaminationDate1);

        boolean success2 = studentService.unsetExaminationDate(testStudent1.getId(), testExaminationDate1.getId());
        testExaminationDate1 = examinationDateDao.findOne(testExaminationDate1.getId());

        assertTrue(success2);
        assertFalse(success1);
        assertNotNull(testExaminationDate1.getParticipants());
        assertTrue(testExaminationDate1.getParticipants().isEmpty());
    }

    /**
     * This method tests StudentService function - Unsetting Examination date for on particular student (try to unset
     * non existent student).
     */
    @Test
    public void unsetExaminationTermNonExistentStudent() {
        boolean success = studentService.unsetExaminationDate(-1L, testExaminationDate1.getId());

        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Unsetting Examination date for on particular student (try to unset
     * student from non existent exam date).
     */
    @Test
    public void unsetExaminationTermNonExistentExamTerm() {
        boolean success = studentService.unsetExaminationDate(testStudent1.getId(), -1L);

        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Unsetting Examination date for on particular student (try to unset
     * teacher from exam date).
     */
    @Test
    public void unsetExaminationTermForTeacher() {
        boolean success = studentService.unsetExaminationDate(testTeacher1.getId(), testExaminationDate1.getId());

        assertFalse(success);
    }

    /**
     * This method tests StudentService function - Getting total credits sum for one particular student.
     */
    @Test
    public void getStudentTotalCredits() {
        testStudent1.getListOfAbsolvedSubjects().add(testSubject1);
        testStudent1 = (Student) userDao.save(testStudent1);

        int creditSum1 = studentService.getStudentTotalCredits(testStudent1.getId());

        testStudent1.getListOfAbsolvedSubjects().add(testSubject2);
        testStudent1 = (Student) userDao.save(testStudent1);

        int creditSum2 = studentService.getStudentTotalCredits(testStudent1.getId());

        assertEquals(testSubject1.getCreditRating(), creditSum1);
        assertEquals(testSubject1.getCreditRating() + testSubject2.getCreditRating(), creditSum2);
    }

    /**
     * This method tests StudentService function - Getting total credits sum for one particular student (non existent
     * student - return -1).
     */
    @Test
    public void getStudentTotalCreditsNonExistentStudent() {
        int creditSum1 = studentService.getStudentTotalCredits(-1L);

        assertEquals(-1, creditSum1);
    }

    /**
     * This method tests StudentService function - Getting total credits sum for one particular student (teacher instead
     * of student).
     */
    @Test
    public void getStudentTotalCreditsTeacher() {
        int creditSum1 = studentService.getStudentTotalCredits(testTeacher1.getId());

        assertEquals(-1, creditSum1);
    }

    /**
     * This method tests StudentService function - Getting total credits sum for one particular student (student without
     * subjects - return 0).
     */
    @Test
    public void getStudentTotalCreditsStudentWithoutAbsolvedSubjects() {
        int creditSum1 = studentService.getStudentTotalCredits(testStudent1.getId());

        assertEquals(0, creditSum1);
    }
}