package org.employee.exception;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.validation.ConstraintViolationException;
import org.employee.domain.exception.EmailExistBeforeException;
import org.employee.domain.exception.HasAlreadyTimeOffException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ProblemDetail handleEntityNotFound(EntityNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ProblemDetail handleValidationViolation(MethodArgumentNotValidException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getFieldErrors().stream().
                map(this::parseFieldException).toList().toString());
    }

    @ExceptionHandler(BindException.class)
    protected ProblemDetail handleBindException(BindException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getFieldErrors().stream().
                map(this::parseFieldException).toList().toString());
    }

    @ExceptionHandler({DatabindException.class})
    public ProblemDetail handleDatabindException(DatabindException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getOriginalMessage());

    }

    @ExceptionHandler({HasAlreadyTimeOffException.class})
    public ProblemDetail handleDatabindException(HasAlreadyTimeOffException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
    }

    @ExceptionHandler({EmailExistBeforeException.class})
    public ProblemDetail handleEmailExistBeforeException(EmailExistBeforeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ProblemDetail handleValidationViolation(ConstraintViolationException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400)
                , ex.getConstraintViolations().stream().map(e -> e.getMessageTemplate() + " "
                        + e.getInvalidValue()).toList().toString());
    }

    private String parseFieldException(FieldError fieldError) {
        return fieldError.getField() + " is set to '" +
                fieldError.getRejectedValue() + "'" + ", " + fieldError.getDefaultMessage();
    }

}
