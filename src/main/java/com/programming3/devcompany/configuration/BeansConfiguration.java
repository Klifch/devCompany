package com.programming3.devcompany.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class BeansConfiguration {

    Logger logger = LoggerFactory.getLogger(BeansConfiguration.class);

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();

        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);

        cookieLocaleResolver.setDefaultTimeZone(TimeZone.getTimeZone("UTC"));

        logger.info(cookieLocaleResolver.toString());

        return cookieLocaleResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor theLocaleChangeInterceptor = new LocaleChangeInterceptor();

        logger.info("INfo about locale {}", theLocaleChangeInterceptor.getParamName());
        theLocaleChangeInterceptor.setParamName("localeData");
        logger.info("INfo about locale {}", theLocaleChangeInterceptor.getParamName());

        return theLocaleChangeInterceptor;
    }

}
