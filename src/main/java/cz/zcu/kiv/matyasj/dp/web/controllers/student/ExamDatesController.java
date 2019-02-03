package cz.zcu.kiv.matyasj.dp.web.controllers.student;

import cz.zcu.kiv.matyasj.dp.domain.university.ExaminationDate;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.User;
import cz.zcu.kiv.matyasj.dp.service.StudentService;
import org.apache.log4j.Logger;
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
 * Objects of this class represent controllers which serve user requests related to Student not registered Examination Dates.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/student-view/otherExamDates")
public class ExamDatesController {
    /** Service object providing functions for manipulation with data related to students.*/
    @Autowired
    StudentService studentService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;

    /** Shared system logger */
    private final Logger log = Logger.getLogger(getClass());

    /**
     * This method serves user GET requests for getting list of Examination Dates.
     *
     * @param model Model to be sent to view
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showExamDateList(Model model){
        log.info("Show Exam dates list");
        Long currentUserId = studentService.getCurrentUser().getId();

        List<ExaminationDate> notRegisteredExaminationDatesList = studentService.getNotRegisteredExaminationDatesList(currentUserId);
        List<Subject> subjectsWithExamDates = studentService.getSubjectsWithRegisteredExamDate(currentUserId);
        List<Subject> studiedSubjects = studentService.getStudiedSubjectsList(currentUserId);

        ModelAndView retModel = new ModelAndView("/WEB-INF/pages/student-view.jsp");
        retModel.addObject("notRegisteredExaminationDatesList", notRegisteredExaminationDatesList);
        retModel.addObject("subjectsWithExamDates", subjectsWithExamDates);
        retModel.addObject("studiedSubjects", studiedSubjects);
        retModel.addObject("view", "otherExamDates");

        return  retModel;
    }

    /**
     * This method serves user POST requests for registering student on Examination Date.
     *
     * @param locale System locale object
     * @param model Model to be sent to view
     * @param examDateId id of Examination date to register
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView registerExamDate(Locale locale, Model model, @RequestParam("examDateId") Long examDateId){
        log.info("Try to register exam date " + examDateId);
        User currentUser = studentService.getCurrentUser();

        boolean success = studentService.setExaminationDate(currentUser.getId(), examDateId);

        if(success){
            model.addAttribute("successMessage", messageSource.getMessage("stu.otherExamDates.successMessage", null, locale));
        }else {
            model.addAttribute("errorMessage", messageSource.getMessage("stu.otherExamDates.errorMessage", null, locale));
        }

        return showExamDateList(model);
    }
}