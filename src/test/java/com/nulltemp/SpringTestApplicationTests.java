package com.nulltemp;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import com.nulltemp.config.TestConfig;

@SpringJUnitWebConfig(TestConfig.class)
public class SpringTestApplicationTests {

	@Test
	public void contextLoads() {
	}

}
