package ro.siit.mytripsapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ro.siit.mytripsapp.entity.Validator.UserCreateFormValidator;
import ro.siit.mytripsapp.entity.user.CurrentUser;
import ro.siit.mytripsapp.entity.user.UserCreateForm;
import ro.siit.mytripsapp.service.user.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/")
    public ModelAndView getHomeUserPage(CurrentUser currentUser) {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(trip_name) from mytripapp.trip WHERE user_id=?", Integer.class, currentUser.getId());
        if (count > 0) {
            LOGGER.debug("Getting user page for user={}", currentUser.getId());
            return new ModelAndView("user", "user", userService.getUserById(currentUser.getId())
                    .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", currentUser.getId()))));
        }
        return new ModelAndView("welcome", "user", userService.getUserById(currentUser.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", currentUser.getId()))));

    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user")
    public ModelAndView getUserPage(CurrentUser currentUser) {
        LOGGER.debug("Getting user page for user={}", currentUser.getId());
        return new ModelAndView("user", "user", userService.getUserById(currentUser.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", currentUser.getId()))));
    }

//    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/profile")
    public ModelAndView getProfilePage(CurrentUser currentUser) {
        LOGGER.debug("Getting user page for user={}", currentUser.getId());
        return new ModelAndView("profile", "user", userService.getUserById(currentUser.getId())
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", currentUser.getId()))));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        LOGGER.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            LOGGER.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }

    @RequestMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

}