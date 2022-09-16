package com.commercehub.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

/**
 * Centralize the timezone setting
 */
public class TimeZoneUtils {

    private static final String FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final String TIMEZONE_UTC = "UTC";

    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone(TIMEZONE_UTC);
    private static final ZoneOffset ZONE_OFFSET = ZoneOffset.UTC;

    // TODO: How to initialize Date object with UTC ???
    // Is it meaningful to do this ? Feels like wasting time
    public static Date getDate(long milliseconds) {
        try {
            Date date = new Date(milliseconds);
            SimpleDateFormat reader = new SimpleDateFormat(FORMAT_ISO_8601);
            String strDate = reader.format(date);

            SimpleDateFormat parser = new SimpleDateFormat(FORMAT_ISO_8601);
            parser.setTimeZone(TIME_ZONE);
            return parser.parse(strDate);
        } catch(ParseException pe) {
            throw new RuntimeException("Failed to parse string date: " + pe.getMessage());
        }
    }

    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZONE_OFFSET));
    }

    public static LocalDate getDate(Date date) {
        return LocalDate.ofInstant(date.toInstant(), TIME_ZONE.toZoneId());
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), TIME_ZONE.toZoneId());
    }

}
