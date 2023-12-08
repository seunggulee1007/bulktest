package com.example.bulktest.config;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.entity.CalendarPk;
import com.example.bulktest.properties.DatabaseProperties;
import com.example.bulktest.service.JdbcService;
import com.example.bulktest.service.JpaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final JdbcService jdbcService;
    private final JpaService jpaService;
    private final DatabaseProperties databaseProperties;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
            List<Calendar> calendarList = makeCalendarByBulkSize();
            log.info("########### jdbc insert start ##########");
            long start = System.currentTimeMillis();
            jdbcService.save(calendarList);
            log.warn("jdbc insert 시간 :: {}", getDurationTime(getSecondByMillis(start)));
            log.info("########### jdbc insert end ##########");
            jdbcService.deleteAll();
            log.info("########### jpa insert start ##########");
            start = System.currentTimeMillis();
            jpaService.save(calendarList);
            log.warn("jpa insert 시간 :: {} ", getDurationTime(getSecondByMillis(start)));
            log.info("########### jpa insert end ##########");
        };
    }

    private long getSecondByMillis(long start) {
        return (System.currentTimeMillis() - start) / 1000;
    }

    private List<Calendar> makeCalendarByBulkSize() {
        int bulkSize = databaseProperties.getBulkSize();
        log.info("bulk size :: {}",bulkSize);
        List<Calendar> calendarList = new ArrayList<>(bulkSize);
        for(int i=0; i<bulkSize; i++) {
            calendarList.add(Calendar.of(CalendarPk.of((long)i, LocalDate.now())));
        }
        return calendarList;
    }

    private String getDurationTime(long time) {
        Duration duration = Duration.ofSeconds(time);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        if (hours > 0) {
            return hours + "시간 " + minutes + "분" + seconds + "초";
        }
        if (minutes > 0) {
            return minutes + "분 " + seconds + "초";
        }
        return seconds + "초";
    }

}