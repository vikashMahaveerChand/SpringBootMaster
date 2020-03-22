package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import com.example.demo.exception.ErrorResponse;

@RestControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {
	//private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
			request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
		}

		System.out.println("!!!!!Exception:" + ex.getMessage() + ", Status:" + status.name());
		ErrorResponse response = new ErrorResponse();
		response.setException(ex.getClass().getSimpleName());
		response.setMessage(status.name() + ":" + ex.getMessage());
		//response.setDetails(request.getDescription(false));
		
		if (ex instanceof MethodArgumentNotValidException) {
			BindingResult br = ((MethodArgumentNotValidException) ex).getBindingResult();
			List<FieldError> fieldErrors = br.getFieldErrors();
			String errorMessage = status.name()+":";
			for (FieldError fe : fieldErrors) {
				errorMessage += fe.getField() +"="+ fe.getDefaultMessage()+", ";
				//log.warn(errorMessage);
			}
			response.setMessage(errorMessage);
		}
		
		Map<String, String> errors = new HashMap<>();
		if (ex instanceof MethodArgumentNotValidException) {
		((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = "$$@@-->"+error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
		}
		response.setDetails(errors);
		
		return new ResponseEntity<>(response, headers, status);
	}
}
