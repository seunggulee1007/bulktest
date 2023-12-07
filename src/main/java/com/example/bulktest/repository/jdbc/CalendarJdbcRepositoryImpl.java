package com.example.bulktest.repository.jdbc;

import com.example.bulktest.entity.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CalendarJdbcRepositoryImpl implements CalendarJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void insertCalendarList(List<Calendar> calendars) {
        String sql = "insert into com_calendar ("
            + "organization_id"
            + ", date"
            + ", holiday_nm"
            + ", reg_date"
            + ", mod_date"
            + ")values ("
            + "?"
            + ", ?"
            + ", ?"
            + ", ?"
            + ", ?"
            + ")";
        Date sqlDate = Date.valueOf(LocalDateTime.now().toLocalDate());
        jdbcTemplate.batchUpdate( sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues ( PreparedStatement ps, int i ) throws SQLException {
                Calendar calendar = calendars.get(i);
                ps.setLong(1, calendar.getId().getOrganizationId() );
                ps.setDate(2, sqlDate);
                ps.setString(3, calendar.getHolidayNm());
                ps.setDate(4, sqlDate);
                ps.setDate(5, sqlDate);

            }

            @Override
            public int getBatchSize () {
                return calendars.size();
            }
        });
    }

    @Override
    public void delete() {
        String sql = "delete from com_calendar where 1=1";
        jdbcTemplate.update(sql);
    }

}
