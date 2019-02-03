package cz.zcu.kiv.matyasj.dp.web.controllers.student;

import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
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
 * Objects of this class represent controllers which serve user requests related to Student unenrolled subjects.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/student-view/otherSubjects")
public class SubjectListController {
    /** Service object providing functions for manipulation with data related to students.*/
    @Autowired
    StudentService studentService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;

    /** Shared system logger */
    private final Logger log = Logger.getLogger(getClass());

    /**
     * This method serves user GET requests to getting list of unenrolled subjects.
     *
     * @param model Model to be sent to view
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showOtherSubjectList(Model model){
        log.info("Show studied subject list");
        List<Subject> listOfSubjects = studentService.getOtherSubjectsList(studentService.getCurrentUser().getId());

        ModelAndView retModel = new ModelAndView("/WEB-INF/pages/student-view.jsp");
        retModel.addObject("subjectList", listOfSubjects);
        retModel.addObject("view", "otherSubjects");

        return  retModel;
    }

    /**
     * This method serves user POST requests for enroll single subject.
     *
     * @param locale System locale object
     * @param model Model to be sent to view
     * @param subjectId id of subject to enroll
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView enrollSubject(Locale locale, Model model, @RequestParam("subjectId") Long subjectId){
        Long studentId = studentService.getCurrentUser().getId();
        log.info("Adding subject id["+subjectId+"] userId["+studentId + "]");

        boolean success = studentService.setStudiedSubject(studentId, subjectId);
        if(success){
            model.addAttribute("successMessage", messageSource.getMessage("stu.otherSubjects.successMessage", null, locale));
        }else{
            model.addAttribute("errorMessage", messageSource.getMessage("stu.otherSubjects.errorMessage", null, locale));
        }

        return showOtherSubjectList(model);
    }
}