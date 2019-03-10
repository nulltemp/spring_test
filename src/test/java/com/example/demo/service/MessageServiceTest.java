package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.*;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.example.demo.config.TestConfig;
import com.example.demo.constant.MessageConstant;

@SpringJUnitWebConfig(TestConfig.class)
class MessageServiceTest {
	private static final String NAME = "nulltemp";

	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageSource messageSource;

	@ParameterizedTest
	@MethodSource("testArgs")
	void test(String name, Locale locale, TestReporter testReporter) {
		String actual = messageService.hello(name, locale);
		String expected = messageSource.getMessage(MessageConstant.KEY_HELLO, new String[] { name }, locale);
		testReporter.publishEntry(new LinkedHashMap<String, String>() {
			{
				put("expected", expected);
				put("actual", actual);
			}
		});
		assertEquals(expected, actual, "expected=" + expected + ", actual=" + actual);
	}

	static Stream<Arguments> testArgs() {
		return Stream.of(
				// 日本語
				arguments(NAME, Locale.JAPAN),
				// 日本語以外
				arguments(NAME, Locale.US),
				// ロケール指定なし
				arguments(NAME, null));
	}
}
