package com.example.demo.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.MessageConstant;
import com.example.demo.service.MessageService;

@CrossOrigin
@RestController
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;

	@GetMapping("/hello")
	public String hello(@RequestParam(name = "name", defaultValue = MessageConstant.DEFAULT_NAME) String name,
			Locale locale) {
		return messageService.hello(name, locale);
	}
}
