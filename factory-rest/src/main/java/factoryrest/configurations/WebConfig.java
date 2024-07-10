package factoryrest.configurations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import factory.config.AppConfig;

@Configuration
@EnableWebMvc
@ComponentScan("factoryrest.restcontrollers")
@Import(AppConfig.class)
public class WebConfig {

}
