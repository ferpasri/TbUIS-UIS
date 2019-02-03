package cz.zcu.kiv.matyasj.dp.web.controllers;

import cz.zcu.kiv.matyasj.dp.service.RestoreDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Objects of this class represent controllers which serve restoreDB user requests.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/restoreDB")
public class RestoreDbController {
    /** Service object of the service for database restoring */
    @Autowired
    RestoreDBService restoreDBService;
    /** Object for resolving messages, with support for the parameterization and internationalization of such messages.*/
    @Autowired
    MessageSource messageSource;

    /**
     * This method serves restoreDB user GET request.
     *
     * @param session HttpSession object
     * @param locale System locale
     * @param redirectAttributes RedirectAttributes object for redirecting attributes
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView restoreDB(HttpSession session, Locale locale, RedirectAttributes redirectAttributes){

        boolean success = restoreDBService.restoreDB();
        if(success){
            redirectAttributes.addAttribute("successMessage", messageSource.getMessage("restoreDBPage.successMessage", null, locale));
            // invalidate session for logout user
            session.invalidate();
        }else{
            redirectAttributes.addAttribute("errorMessage", messageSource.getMessage("restoreDBPage.errorMessage", null, locale));
        }

        return  new ModelAndView("redirect:/");
    }
}