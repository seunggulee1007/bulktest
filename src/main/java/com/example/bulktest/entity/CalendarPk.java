package com.example.bulktest.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CalendarPk implements Serializable {
    /**
     * 법인 Id
     */
    @Column(name = "organization_id", nullable = false)
    private Long organizationId;

    /**
     * 일자
     */
    @Column(name = "date", nullable = false, columnDefinition = "DATE")
    private LocalDate date;

    public static CalendarPk of(Long organizationId, LocalDate date) {
        return new CalendarPk(organizationId, date);
    }

}