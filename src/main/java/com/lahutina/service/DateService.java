package com.lahutina.service;

import com.lahutina.model.Date;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Service
public class DateService {
    public double getDaysFromCurrentDay() {
        LocalDate currentDate = LocalDate.now();
        return calculateYearPassPercentage(currentDate);
    }

    public double getDaysFromSpecificDay(Date date)
    {
        if (date == null || date.getDate() == null) {
            return -1;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate inputDate = LocalDate.parse(date.getDate(), dtf);

        return calculateYearPassPercentage(inputDate);
    }

    private double calculateYearPassPercentage(LocalDate date) {
        LocalDate startDate = LocalDate.of(date.getYear(), 1, 1);
        LocalDate endDate = LocalDate.of(date.getYear(), 12, 31);

        double totalDaysInYear = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double daysPassed = ChronoUnit.DAYS.between(startDate, date) + 1;

        return daysPassed / totalDaysInYear * 100;
    }
}
