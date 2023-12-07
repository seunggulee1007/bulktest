package com.example.bulktest.repository;

import com.example.bulktest.entity.Calendar;
import com.example.bulktest.entity.CalendarPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CalendarRepository extends JpaRepository<Calendar, CalendarPk> {

    @Modifying
    @Transactional
    @Query("delete from Calendar where 1=1")
    void deleteAll();
}
