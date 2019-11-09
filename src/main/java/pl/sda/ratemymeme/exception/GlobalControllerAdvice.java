package pl.sda.ratemymeme.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {StorageException.class})
    protected ModelAndView handleStorageFailure(RuntimeException ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(value = {StorageFileNotFoundException.class})
    protected ModelAndView handleFileNotFound(StorageException ex, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errormessage", ex.getMessage());
        return modelAndView;
    }
}
