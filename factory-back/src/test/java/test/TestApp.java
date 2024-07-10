package test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import factory.config.AppConfig;

@SpringJUnitConfig(AppConfig.class)
class TestApp {

	@Test
	void test() {
		
	}

}
