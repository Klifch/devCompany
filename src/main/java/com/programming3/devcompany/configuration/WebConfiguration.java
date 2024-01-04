package com.programming3.devcompany.configuration;

import com.programming3.devcompany.configuration.interceptor.PageTrackingInterceptor;
import com.programming3.devcompany.presentation.converter.LocalDateConverter;
import com.programming3.devcompany.presentation.converter.PositionConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    private LocaleChangeInterceptor localeChangeInterceptor;

    private PageTrackingInterceptor pageTrackingInterceptor;

    @Autowired
    public WebConfiguration(LocaleChangeInterceptor localeChangeInterceptor, PageTrackingInterceptor pageTrackingInterceptor) {
        this.localeChangeInterceptor = localeChangeInterceptor;
        this.pageTrackingInterceptor = pageTrackingInterceptor;
    }

    @Bean
    public PageTrackingInterceptor yourCustomInterceptor() {
        return new PageTrackingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(yourCustomInterceptor());
    }

    // formatter for conversion String -> LocalDate
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter());
        registry.addConverter(new PositionConverter());
    }
}
