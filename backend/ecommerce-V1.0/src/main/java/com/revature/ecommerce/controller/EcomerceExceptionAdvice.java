package com.revature.ecommerce.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.ecommerce.dto.EcomerceMessage;
import com.revature.ecommerce.dto.EcommErrorInfo;
import com.revature.ecommerce.exceptions.NoCartException;
import com.revature.ecommerce.exceptions.NoOrderException;
import com.revature.ecommerce.exceptions.NoProductException;
import com.revature.ecommerce.exceptions.NoReviewException;

@RestController
public class EcomerceExceptionAdvice {
   Logger logger = LoggerFactory.getLogger(EcomerceExceptionAdvice.class);
   
	@ExceptionHandler({NoCartException.class, NoOrderException.class, NoProductException.class, NoReviewException.class})
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public EcommErrorInfo handleExcveption(Exception ex) {
		logger.error(ex.getMessage());
		return new EcommErrorInfo (ex.getMessage());
	}
}
