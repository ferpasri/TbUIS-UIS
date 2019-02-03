package cz.zcu.kiv.matyasj.dp.web.controllers.teacher;

import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Teacher;
import cz.zcu.kiv.matyasj.dp.service.TeacherService;
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
 * Objects of this class represent controllers which serve user requests related to Teacher's taught subjects.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/teacher-view/mySubjects")
public class ListOfTaughtSubjectController {
    /** Service object providing functions for manipulation with data related to teachers.*/
    @Autowired
    TeacherService teacherService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;

    /** Shared system logger */
    protected Logger log = Logger.getLogger(getClass());

    /**
     * This method serves user GET requests to show list of all taught subjects
     *
     * @param model Model to be sent to view
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTaughtSubjectList(Model model){
        List<Subject> listOfSubjects = teacherService.getTaughtSubjectsList((Teacher) teacherService.getCurrentUser());

        ModelAndView retModel = new ModelAndView("/WEB-INF/pages/teacher-view.jsp");
        retModel.addObject("subjectList", listOfSubjects);
        retModel.addObject("view", "mySubjects");

        log.info("Show taught subjects list");
        return  retModel;
    }

    /**
     * This method serves user POST requests to unset taught subject
     *
     * @param model Model to be sent to view
     * @param locale Locale object
     * @param subjectId Id of subject to set
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView removeStudiedSubject(Locale locale, Model model, @RequestParam("subjectId") Long subjectId){
        Teacher teacher = (Teacher) teacherService.getCurrentUser();

        log.info("Unset taught subject(id["+subjectId+"]) for teacher (id["+teacher.getId() + "])");
        boolean success = teacherService.unsetMySubject(teacher, subjectId);
        if(success){
            model.addAttribute("successMessage", messageSource.getMessage("tea.mySubjects.successMessage", null, locale));
        }else {
            model.addAttribute("errorMessage", messageSource.getMessage("tea.mySubjects.errorMessage", null, locale));
        }

        return showTaughtSubjectList(model);
    }
}