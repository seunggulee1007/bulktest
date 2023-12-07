package com.example.bulktest.config;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.entity.CalendarPk;
import com.example.bulktest.service.JdbcService;
import com.example.bulktest.service.JpaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JdbcService jdbcService;
    private final JpaService jpaService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            int size = 1000000;
            List<Calendar> calendarList = new ArrayList<>(size);
            for(int i=0; i<size; i++) {
                calendarList.add(Calendar.of(CalendarPk.of((long)i, LocalDate.now())));
            }
            log.info("########### jdbc insert start ##########");
            long start = System.currentTimeMillis();
            jdbcService.save(calendarList);
            long jdbcEnd = System.currentTimeMillis() - start;
            log.info("########### jdbc insert end ##########");
            log.warn("jdbc insert 시간 :: {} 초", (jdbcEnd / 1000));
            jdbcService.deleteAll();
            log.info("########### jpa insert start ##########");
            start = System.currentTimeMillis();
            jpaService.save(calendarList);
            log.info("########### jpa insert end ##########");
            long end = System.currentTimeMillis() - start;
            log.warn("jpa insert 시간 :: {} 초", (end / 1000));
        };
    }

}