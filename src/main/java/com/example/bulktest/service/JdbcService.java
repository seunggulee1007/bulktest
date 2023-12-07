package com.example.bulktest.service;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.repository.jdbc.CalendarJdbcRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JdbcService {
    private final CalendarJdbcRepository calendarJdbcRepository;

    public void save(List<Calendar> calendarList) {

        calendarJdbcRepository.insertCalendarList(calendarList);

    }

    public void deleteAll() {
        calendarJdbcRepository.delete();
    }

}
