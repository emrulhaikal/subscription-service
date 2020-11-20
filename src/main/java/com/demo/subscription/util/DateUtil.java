package com.demo.subscription.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {

    private static final DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public List<String> getDailySubscription(final LocalDate startDate, final LocalDate endDate) {
        final List<String> dateRange = new ArrayList<>();
        LocalDate date = startDate;
        do {
            dateRange.add(this.dateFormat(date));
            date = date.plusDays(1);
        } while (this.isAfterOrEqual(date, endDate));

        return dateRange;
    }

    public List<String> getWeeklySubscription(final LocalDate startDate, final LocalDate endDate, final String day) {
        final DayOfWeek dowOfStart = startDate.getDayOfWeek();
        int difference = DayOfWeek.valueOf(day).getValue() - dowOfStart.getValue();
        if (difference < 0) difference += 7;
        final List<String> dateRange = new ArrayList<>();
        LocalDate nextDate = startDate.plusDays(difference);
        do {
            dateRange.add(this.dateFormat(nextDate));
            nextDate = nextDate.plusDays(7);
        } while (this.isAfterOrEqual(nextDate, endDate));

        return dateRange;
    }

    public List<String> getMonthlySubscription(final LocalDate startDate, final LocalDate endDate, final int day) {
        final int monthDifferent = Period.between(startDate, endDate).getMonths();
        final int domOfStart = startDate.getDayOfMonth();
        final int dayDifference = day - domOfStart;
        int startMonth=0;
        if (dayDifference < 0) startMonth=1;

        final List<String> dateRange = new ArrayList<>();
        final LocalDate lastDate = startDate.plusMonths(monthDifferent);
        LocalDate nextDate = startDate.plusMonths(startMonth).withDayOfMonth(day);
        do {
            dateRange.add(this.dateFormat(nextDate));
            nextDate = nextDate.plusMonths(1);
        } while (nextDate.isBefore(lastDate));

        return dateRange;
    }

    public boolean isMonthValid(final LocalDate startDate, final LocalDate endDate){
        if (startDate.isAfter(endDate) || Period.between(startDate, endDate).getMonths() > 3){
            return false;
        }
        return true;
    }

    private boolean isAfterOrEqual(final LocalDate date, final LocalDate compareToDate) {
        if (date == null || compareToDate == null) {
            return false;
        }
        return compareToDate.isAfter(date) || compareToDate.isEqual(date);
    }

    private String dateFormat(LocalDate date){
        return date.format(this.formatters);
    }
}
