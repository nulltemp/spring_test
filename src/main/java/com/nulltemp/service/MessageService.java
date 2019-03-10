package com.nulltemp.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.nulltemp.constant.MessageConstant;

@Service
public class MessageService {
	@Autowired
	private MessageSource messageSource;

	public String hello(String name, Locale locale) {
		return messageSource.getMessage(MessageConstant.KEY_HELLO, new String[] { name }, locale);
	}
}
