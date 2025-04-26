package br.com.sifatecommerce.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class InternationalizationConfig {

    @Bean
    MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =  new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setDefaultLocale(new Locale("pt", "BR"));
		return messageSource;
	}

    @Bean
    LocalValidatorFactoryBean validatorFactoryBean() {
		LocalValidatorFactoryBean bean =  new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
