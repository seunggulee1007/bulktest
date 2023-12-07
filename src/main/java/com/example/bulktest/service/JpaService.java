package com.example.bulktest.service;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JpaService {

    private final CalendarRepository calendarRepository;

    public void save(List<Calendar> calendarList) {
        calendarRepository.saveAll(calendarList);
    }

    public void deleteAll() {
        calendarRepository.deleteAll();
    }

}
