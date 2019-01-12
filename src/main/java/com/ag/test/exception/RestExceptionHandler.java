package com.ag.test.exception;

import com.ag.test.api.ItemNotFoundException;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    protected final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
                                                                          HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing.";
        ErrorModel oftErrorModel = new ErrorModel();
        oftErrorModel.setStatus(HttpStatus.BAD_REQUEST.value());
        oftErrorModel.setCode(HttpStatus.BAD_REQUEST.value());
        oftErrorModel.setMessage(error);

        return new ResponseEntity<Object>(oftErrorModel, HttpStatus.BAD_REQUEST);
    }

    /**
     * Exception thrown when {@link org.springframework.validation.annotation.Validated} is used in controller.
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {

        try {
            List<String> messages = new ArrayList<String>();
            for (ConstraintViolation violation : ex.getConstraintViolations()) {
                messages.add(violation.getMessage());
            }
            ErrorModel oftErrorModel = new ErrorModel();
            oftErrorModel.setStatus(HttpStatus.BAD_REQUEST.value());
            oftErrorModel.setCode(HttpStatus.BAD_REQUEST.value());
            oftErrorModel.setMessage(StringUtils.join(messages,','));

            return new ResponseEntity<Object>(oftErrorModel, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            logger.error("Error creating constraint violation response ", e);
            ErrorModel oftErrorModel = new ErrorModel();
            oftErrorModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            oftErrorModel.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            oftErrorModel.setMessage(ex.getMessage());

            return new ResponseEntity<Object>(oftErrorModel, HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Handles custom System exception
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<?> handleCustomException(ItemNotFoundException ex, HttpServletRequest request, HandlerMethod handlerMethod) {
        try {
            ErrorModel oftErrorModel = new ErrorModel();
            oftErrorModel.setStatus(HttpStatus.BAD_REQUEST.value());
            oftErrorModel.setCode(HttpStatus.BAD_REQUEST.value());
            oftErrorModel.setMessage(ex.getMessage());

            return new ResponseEntity<Object>(oftErrorModel, HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ErrorModel oftErrorModel = new ErrorModel();
            oftErrorModel.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            oftErrorModel.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            oftErrorModel.setMessage(ex.getMessage());

            return new ResponseEntity<Object>(oftErrorModel, HttpStatus.BAD_REQUEST);
        }
    }

}