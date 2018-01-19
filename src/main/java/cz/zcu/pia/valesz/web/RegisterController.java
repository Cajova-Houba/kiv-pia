package cz.zcu.pia.valesz.web;

import cz.zcu.pia.valesz.core.service.UserManager;
import cz.zcu.pia.valesz.web.validation.UserRegistrationValidator;
import cz.zcu.pia.valesz.web.vo.UserForm;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Locale;

/**
 * Controller for register page.
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private UserManager userManager;

    @Autowired
    private UserRegistrationValidator registrationValidator;

    @InitBinder("userForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(registrationValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayPage(ModelMap modelMap) {
        modelMap.addAttribute("currDate", new Date());
        modelMap.addAttribute("userForm", new UserForm());
        return "index";
    }

    /**
     * Handles registration.
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handleRegistration(Model model,
                                     @ModelAttribute("userForm") @Validated UserForm userForm,
                                     BindingResult bindingResult,
                                     @RequestParam("turingTest") String turingTestResults) {
        if(bindingResult.hasErrors()) {
            log.warn("Error occurred while binding registration form to UserForm object.");
            model.addAttribute("currDate", new Date());
            return "index";
        }

        // turing test
        DateFormatSymbols dfs = DateFormatSymbols.getInstance(Locale.US);
        int currentMonthOfYear = new DateTime().getMonthOfYear()-1;
        String currentMonth = dfs.getMonths()[currentMonthOfYear].toLowerCase();
        if(!turingTestResults.equals(currentMonth)) {
            log.warn("Turing test failed! {} is not current month {}!", turingTestResults, currentMonth);
            model.addAttribute("userForm", userForm);
            model.addAttribute("currDate", new Date());
            model.addAttribute("isTuringFailed", true);
            return "index";
        }

        userManager.registerUser(userForm);
        return "reg-succ";
    }
}
