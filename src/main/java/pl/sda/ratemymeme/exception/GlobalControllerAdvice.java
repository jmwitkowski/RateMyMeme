package pl.sda.ratemymeme.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ModelAndView handleStorageFailure(RuntimeException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }
}