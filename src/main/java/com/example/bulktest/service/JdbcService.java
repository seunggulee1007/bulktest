package com.example.bulktest.service;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.properties.DatabaseProperties;
import com.example.bulktest.repository.jdbc.CalendarJdbcRepository;
import com.example.bulktest.util.ListUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcService {

    private final CalendarJdbcRepository calendarJdbcRepository;
    private final DatabaseProperties databaseProperties;

    public void save(List<Calendar> calendarList) {

        List<List<Calendar>> dividedArrayList = ListUtil.divideArrayList(calendarList, databaseProperties.getBatchSize());
        for (List<Calendar> calendars : dividedArrayList) {
            calendarJdbcRepository.insertCalendarList(calendars);
        }

    }

    public void deleteAll() {
        calendarJdbcRepository.delete();
    }

}
