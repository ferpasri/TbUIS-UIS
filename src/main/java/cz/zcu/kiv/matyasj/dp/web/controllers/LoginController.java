package cz.zcu.kiv.matyasj.dp.web.controllers;

import cz.zcu.kiv.matyasj.dp.dao.UserDao;
import cz.zcu.kiv.matyasj.dp.domain.users.Student;
import cz.zcu.kiv.matyasj.dp.domain.users.Teacher;
import cz.zcu.kiv.matyasj.dp.domain.users.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * Objects of this class represent controllers which serve user requests for login.
 *
 * @author Jiri Matyas
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    /** Shared system logger */
    private final Logger log = LogManager.getLogger();
    /** DAO object for manipulation with user data in database */
    @Autowired
    UserDao userDao;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showLoginForm(Model model){
        return  new ModelAndView("login.jsp");
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView loginUser(HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) {
        log.info("Try to login user=" + username + ":" + password);
        User loggedInUser = userDao.findByUsername(username);

        if(loggedInUser != null){
            if(loggedInUser instanceof Student){
                return  new ModelAndView("redirect:/student-view/overview");
            }else if(loggedInUser instanceof Teacher){
                return  new ModelAndView("redirect:/teacher-view/overview");
            }
        }
        log.error("Forbidden access for user " + username + "!");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return null;
    }
}