package com.programming3.devcompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.support.ResourceBundleMessageSource;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

@SpringBootApplication
public class DevcompanyApplication {

	// i forgot to add readme

	public static void main(String[] args) {

//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasenames("lang/messages");
//		messageSource.setDefaultEncoding("UTF-8");

		SpringApplication.run(DevcompanyApplication.class, args);
	}

}
