package com.example.bulktest.repository.jdbc;


import com.example.bulktest.entity.Calendar;

import java.util.List;

public interface CalendarJdbcRepository {

    void insertCalendarList(List<Calendar> calendars);

    void delete();

}
