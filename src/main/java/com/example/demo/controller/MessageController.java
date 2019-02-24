package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageSource messageSource;

	@GetMapping("/hello")
	public String hello(@RequestParam(name = "name", defaultValue = "no name") String name, Locale locale) {
		return messageSource.getMessage("hello", new String[] { name }, locale);
	}
}
