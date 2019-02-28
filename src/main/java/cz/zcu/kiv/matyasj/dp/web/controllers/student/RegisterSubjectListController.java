package cz.zcu.kiv.matyasj.dp.web.controllers.student;

import cz.zcu.kiv.matyasj.dp.domain.university.Grade;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Student;
import cz.zcu.kiv.matyasj.dp.service.StudentService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Locale;

/**
 * Objects of this class represent controllers which serve user requests related to enrolled subjects.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/student-view/mySubjects")
public class RegisterSubjectListController {
    /** Service object providing functions for manipulation with data related to students.*/
    @Autowired
    StudentService studentService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;
    /** Shared system logger */
    private final Logger log = LogManager.getLogger();

    /**
     * This method serves user GET requests to getting list of enrolled subjects.
     *
     * @param model Model to be sent to view
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showStudiedSubjectList(Model model) {
        log.info("Request for retrieving studied subjects view.");

        List<Subject> listOfSubject = studentService.getStudiedSubjectsList(studentService.getCurrentUser().getId());
        List<Subject> listOfAbsolvedSubject = studentService.getAbsolvedSubjectsList(studentService.getCurrentUser().getId());
        List<Grade> listOfGrades = studentService.getStudentGrades((Student) studentService.getCurrentUser());
        int totalCredits = studentService.getStudentTotalCredits(studentService.getCurrentUser().getId());

        ModelAndView retModel = new ModelAndView("/WEB-INF/pages/student-view.jsp");
        retModel.addObject("subjectList", listOfSubject);
        retModel.addObject("completedSubjectList", listOfAbsolvedSubject);
        retModel.addObject("listOfGrades", listOfGrades);
        retModel.addObject("totalCredits", totalCredits);

        retModel.addObject("view", "mySubjects");

        return retModel;
    }

    /**
     * This method serves user POST request to unenroll single subject.
     *
     * @param locale    System locale object
     * @param model     Model to be sent to view
     * @param subjectId Id of subject to unenroll
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView removeStudiedSubject(Locale locale, Model model, @RequestParam("subjectId") Long subjectId) {
        Long studentId = studentService.getCurrentUser().getId();
        log.info("Request from user with id " + studentId + " for removing subject with id " + subjectId + " from studied.");

        boolean success = studentService.unsetStudiedSubject(studentId, subjectId);
        if (success) {
            log.info("Request for removing subject with id " + subjectId + " was successful.");
            model.addAttribute("successMessage", messageSource.getMessage("stu.mySubjects.successMessage", null, locale));
        } else {
            log.error("Request for removing subject with id " + subjectId + " failed.");
            model.addAttribute("errorMessage", messageSource.getMessage("stu.mySubjects.errorMessage", null, locale));
        }

        return showStudiedSubjectList(model);
    }
}