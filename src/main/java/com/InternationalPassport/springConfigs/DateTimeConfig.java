package com.InternationalPassport.springConfigs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateTimeConfig {
//    @Bean
//    public FormattingConversionService  formattingConversionService() {
//        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(false);
//
//        DateTimeFormatterRegistrar dateTimeFormatterRegistrar = new DateTimeFormatterRegistrar();
//        dateTimeFormatterRegistrar.setDateFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
////        dateTimeFormatterRegistrar.setDateFormatter();
//        dateTimeFormatterRegistrar.setDateTimeFormatter(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
//        dateTimeFormatterRegistrar.registerFormatters(conversionService);
//
//        return conversionService;
//    }
}
