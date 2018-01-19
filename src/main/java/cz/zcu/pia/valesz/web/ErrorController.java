package cz.zcu.pia.valesz.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for custom error page.
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * Displays error page.
     * @param httpRequest
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {

        String errorMsg = "";
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Bad request. Data you have sent me are most likely not correct.";
                break;
            }
            case 401: {
                errorMsg = "Sorry, but you can't access this. Go back and try to log in first.";
                break;
            }
            case 403: {
                errorMsg = "Sorry, but you can't access this.";
                break;
            }
            case 404: {
                errorMsg = "Page you are looking was not found.";
                break;
            }
            case 500: {
                errorMsg = "500: Something nasty must have happened...";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "error";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }
}
