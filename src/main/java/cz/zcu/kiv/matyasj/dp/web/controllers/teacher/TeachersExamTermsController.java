package cz.zcu.kiv.matyasj.dp.web.controllers.teacher;

import cz.zcu.kiv.matyasj.dp.domain.university.ExaminationDate;
import cz.zcu.kiv.matyasj.dp.domain.university.Subject;
import cz.zcu.kiv.matyasj.dp.domain.users.Teacher;
import cz.zcu.kiv.matyasj.dp.service.TeacherService;
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
 * Objects of this class represent controllers which serve user requests related to Teacher's examination dates.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/teacher-view/myExamDates")
public class TeachersExamTermsController {
    /** Service object providing functions for manipulation with data related to teachers.*/
    @Autowired
    TeacherService teacherService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;

    /** Shared system logger */
    protected Logger log = LogManager.getLogger();

    /**
     * This method serves user GET requests to show list of Teacher's exam dates.
     *
     * @param model Model to be sent to view
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showTeachersExamDatesList(Model model) {
        List<ExaminationDate> listOfExamDates = teacherService.getMyExaminationDatesWithoutGraduateParticipants((Teacher) teacherService.getCurrentUser());
        List<Subject> taughtSubjectList = teacherService.getTaughtSubjectsList((Teacher) teacherService.getCurrentUser());

        ModelAndView retModel = new ModelAndView("/WEB-INF/pages/teacher-view.jsp");
        retModel.addObject("listOfExamDates", listOfExamDates);
        retModel.addObject("taughtSubjectList", taughtSubjectList);
        retModel.addObject("view", "myExamDates");

        log.info("Show teaching subject list");
        return retModel;
    }

    /**
     * This method serves user POST requests to remove created ExamDate
     *
     * @param locale Locale object
     * @param model Model to be sent to view
     * @param examDateId id of existent ExamDate to cancel
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView cancelExamDate(Locale locale, Model model, @RequestParam("examDateId") Long examDateId) {
        Teacher teacher = (Teacher) teacherService.getCurrentUser();

        log.info("Canceling examination date id[" + examDateId + "] of userId[" + teacher.getId() + "]");
        boolean success = teacherService.removeExaminationTerm(teacher, examDateId);
        if (success) {
            model.addAttribute("successMessage", messageSource.getMessage("tea.myExamDates.successMessage", null, locale));
        }else {
            model.addAttribute("errorMessage", messageSource.getMessage("tea.myExamDates.errorMessage", null, locale));
        }
        return showTeachersExamDatesList(model);
    }
}