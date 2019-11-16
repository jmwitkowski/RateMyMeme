package pl.sda.ratemymeme.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {StorageException.class})
    protected ModelAndView handleStorageFailure(StorageException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {StorageFileNotFoundException.class})
    protected ModelAndView handleFileNotFound(StorageException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {EmailExistsException.class})
    protected ModelAndView handleEmailExistInDataBase(EmailExistsException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {LoginExistsException.class})
    protected ModelAndView handleLoginExistInDataBase(LoginExistsException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ModelAndView handleUserNotFoundException(UsernameNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {MemeNotFoundException.class})
    protected ModelAndView handleMemeNotFoundException(MemeNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {RoleNotFoundException.class})
    protected ModelAndView handleRoleNotFoundException(RoleNotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }
}
