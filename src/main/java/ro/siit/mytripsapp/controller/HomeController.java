package ro.siit.mytripsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.mytripsapp.entity.Validator.UserCreateFormValidator;
import ro.siit.mytripsapp.entity.user.CurrentUser;
import ro.siit.mytripsapp.entity.user.User;
import ro.siit.mytripsapp.service.user.UserService;

import java.util.NoSuchElementException;

@Controller
public class HomeController {


    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);


    @RequestMapping("/")
    public String getHomePage(CurrentUser user) {

        LOGGER.info(String.valueOf(user.getId()));

        LOGGER.debug("Getting home page");

        return "user";
    }

}