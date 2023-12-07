package com.example.bulktest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Slf4j
@ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "com_calendar")
public class Calendar  {

    /**
     * Pk
     */
    @EmbeddedId
    private CalendarPk id;

    /**
     * 휴일명
     */
    @Column(name = "holiday_nm")
    private String holidayNm;


    /**
     * 생성 일시
     */
    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now();
    /**
     * 마지막 변경 일시
     */
    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate = LocalDateTime.now();

    public static Calendar of(CalendarPk calendarPk) {
        Calendar calendar = new Calendar();
        calendar.id = calendarPk;
        return calendar;
    }
}
