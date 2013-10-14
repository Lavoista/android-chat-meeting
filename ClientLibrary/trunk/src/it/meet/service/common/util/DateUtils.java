package it.meet.service.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * An utility class for dates conversions.
 *
 */
public class DateUtils {

    private static Date defaultEndDate = null;
    private static Date thresholdDate = null;
    public static final String DEFAULT_TIME_FROM = "00:00";
    public static final String DEFAULT_TIME_TO = "23:59";

    static {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.YEAR, 9999);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        defaultEndDate = calendar.getTime();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 01);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.YEAR, 2000);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        thresholdDate = calendar.getTime();
    }

    /**
     * The getDate method generates a java.util.Date object from the specified
     * parameters. This method is usually used in the Web layer.
     *
     * @param day a numeric value representing the day of the date.
     * @param month a numeric value representing the month of the date.
     * @param year a numeric value representing the year of the date.
     * @param hours a numeric value representing the hour (24 hours format) of
     * the date.
     * @param minutes a numeric value representing the minutes of the date.
     * @param seconds a numeric value representing the seconds of the date.
     * @return a Date generated from the specified date parameters.
     */
    public static Date getDate(int day, int month, int year, int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.SECOND, seconds);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * The getDate method generates a java.util.Date object parsing the string
     * {@code date} parameter with the specified string {@code pattern}
     * parameter.
     * <h4>Date and Time Patterns</h4>
     *
     * <p>
     * Date and time formats are specified by <em>date and time pattern</em>
     * strings. Within date and time pattern strings, unquoted letters from
     * <code>'A'</code> to
     * <code>'Z'</code> and from
     * <code>'a'</code> to
     * <code>'z'</code> are interpreted as pattern letters representing the
     * components of a date or time string. Text can be quoted using single
     * quotes (
     * <code>'</code>) to avoid interpretation.
     * <code>"''"</code> represents a single quote. All other characters are not
     * interpreted; they're simply copied into the output string during
     * formatting or matched against the input string during parsing.
     * <p>
     *
     * The following pattern letters are defined (all other characters from
     * <code>'A'</code> to
     * <code>'Z'</code> and from
     * <code>'a'</code> to
     * <code>'z'</code> are reserved): <blockquote> <table border=0
     * cellspacing=3 cellpadding=0 summary="Chart shows pattern letters,
     * date/time component, presentation, and examples.">
     *
     * <tr bgcolor="#ccccff">
     * <th align=left>Letter
     * <th align=left>Date or Time Component
     * <th align=left>Presentation
     * <th align=left>Examples
     * <tr>
     * <td><code>G</code>
     * <td>Era designator
     * <td><a href="#text">Text</a>
     * <td><code>AD</code>
     *
     * <tr bgcolor="#eeeeff">
     * <td><code>y</code>
     * <td>Year
     * <td><a href="#year">Year</a>
     * <td><code>1996</code>;
     * <code>96</code>
     * <tr>
     * <td><code>M</code>
     *
     * <td>Month in year
     * <td><a href="#month">Month</a>
     * <td><code>July</code>;
     * <code>Jul</code>;
     * <code>07</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>w</code>
     * <td>Week in year
     * <td><a href="#number">Number</a>
     *
     * <td><code>27</code>
     * <tr>
     * <td><code>W</code>
     * <td>Week in month
     * <td><a href="#number">Number</a>
     * <td><code>2</code>
     * <tr bgcolor="#eeeeff">
     *
     * <td><code>D</code>
     * <td>Day in year
     * <td><a href="#number">Number</a>
     * <td><code>189</code>
     * <tr>
     * <td><code>d</code>
     * <td>Day in month
     * <td><a href="#number">Number</a>
     *
     * <td><code>10</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>F</code>
     * <td>Day of week in month
     * <td><a href="#number">Number</a>
     * <td><code>2</code>
     * <tr>
     *
     * <td><code>E</code>
     * <td>Day in week
     * <td><a href="#text">Text</a>
     * <td><code>Tuesday</code>;
     * <code>Tue</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>a</code>
     *
     * <td>Am/pm marker
     * <td><a href="#text">Text</a>
     * <td><code>PM</code>
     * <tr>
     * <td><code>H</code>
     * <td>Hour in day (0-23)
     * <td><a href="#number">Number</a>
     * <td><code>0</code>
     *
     * <tr bgcolor="#eeeeff">
     * <td><code>k</code>
     * <td>Hour in day (1-24)
     * <td><a href="#number">Number</a>
     * <td><code>24</code>
     * <tr>
     * <td><code>K</code>
     *
     * <td>Hour in am/pm (0-11)
     * <td><a href="#number">Number</a>
     * <td><code>0</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>h</code>
     * <td>Hour in am/pm (1-12)
     * <td><a href="#number">Number</a>
     * <td><code>12</code>
     *
     * <tr>
     * <td><code>m</code>
     * <td>Minute in hour
     * <td><a href="#number">Number</a>
     * <td><code>30</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>s</code>
     *
     * <td>Second in minute
     * <td><a href="#number">Number</a>
     * <td><code>55</code>
     * <tr>
     * <td><code>S</code>
     * <td>Millisecond
     * <td><a href="#number">Number</a>
     * <td><code>978</code>
     *
     * <tr bgcolor="#eeeeff">
     * <td><code>z</code>
     * <td>Time zone
     * <td><a href="#timezone">General time zone</a>
     * <td><code>Pacific Standard Time</code>;
     * <code>PST</code>;
     * <code>GMT-08:00</code>
     * <tr>
     *
     * <td><code>Z</code>
     * <td>Time zone
     * <td><a href="#rfc822timezone">RFC 822 time zone</a>
     * <td><code>-0800</code> </table> </blockquote> Pattern letters are usually
     * repeated, as their number determines the exact presentation:
     * <ul>
     *
     * <li><strong><a name="text">Text:</a></strong> For formatting, if the
     * number of pattern letters is 4 or more, the full form is used; otherwise
     * a short or abbreviated form is used if available. For parsing, both forms
     * are accepted, independent of the number of pattern letters.
     * <li><strong><a name="number">Number:</a></strong> For formatting, the
     * number of pattern letters is the minimum number of digits, and shorter
     * numbers are zero-padded to this amount. For parsing, the number of
     * pattern letters is ignored unless it's needed to separate two adjacent
     * fields.
     * <li><strong><a name="year">Year:</a></strong> If the formatter's <A
     * HREF="../../java/text/DateFormat.html#getCalendar()"><CODE>Calendar</CODE></A>
     * is the Gregorian calendar, the following rules are applied.<br>
     *
     * <ul>
     * <li>For formatting, if the number of pattern letters is 2, the year is
     * truncated to 2 digits; otherwise it is interpreted as a <a
     * href="#number">number</a>.
     * <li>For parsing, if the number of pattern letters is more than 2, the
     * year is interpreted literally, regardless of the number of digits. So
     * using the pattern "MM/dd/yyyy", "01/11/12" parses to Jan 11, 12 A.D.
     * <li>For parsing with the abbreviated year pattern ("y" or "yy"),
     * <code>SimpleDateFormat</code> must interpret the abbreviated year
     * relative to some century. It does this by adjusting dates to be within 80
     * years before and 20 years after the time the
     * <code>SimpleDateFormat</code> instance is created. For example, using a
     * pattern of "MM/dd/yy" and a
     * <code>SimpleDateFormat</code> instance created on Jan 1, 1997, the string
     * "01/11/12" would be interpreted as Jan 11, 2012 while the string
     * "05/04/64" would be interpreted as May 4, 1964. During parsing, only
     * strings consisting of exactly two digits, as defined by <A
     * HREF="../../java/lang/Character.html#isDigit(char)"><CODE>Character.isDigit(char)</CODE></A>,
     * will be parsed into the default century. Any other numeric string, such
     * as a one digit string, a three or more digit string, or a two digit
     * string that isn't all digits (for example, "-1"), is interpreted
     * literally. So "01/02/3" or "01/02/003" are parsed, using the same
     * pattern, as Jan 2, 3 AD. Likewise, "01/02/-3" is parsed as Jan 2, 4 BC.
     * </ul>
     *
     * Otherwise, calendar system specific forms are applied. For both
     * formatting and parsing, if the number of pattern letters is 4 or more, a
     * calendar specific <A HREF="../../java/util/Calendar.html#LONG">long
     * form</A>
     * is used. Otherwise, a calendar specific <A
     * HREF="../../java/util/Calendar.html#SHORT">short or abbreviated form</A>
     * is used.
     * <li><strong><a name="month">Month:</a></strong> If the number of pattern
     * letters is 3 or more, the month is interpreted as <a
     * href="#text">text</a>; otherwise, it is interpreted as a <a
     * href="#number">number</a>.
     *
     * This method is usually used in the Web layer.
     *
     * @param date the string representing the date to parse. It must follow the
     * format specified by the {@code pattern} parameter.
     * @param pattern specifies the format that the string {@code date }
     * parameter must follow.
     * @return Date a Date object corresponding to the specified pattern.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and {@code pattern} parameters.
     */
    public static Date getDate(String date, String pattern) throws ParseException {
        return getDate(date, pattern, false);
    }

    /**
     * The getDate method generates a java.util.Date object parsing the string
     * {@code date} parameter with the specified string {@code pattern}
     * parameter and the lenient mode specified.
     *
     * @param date the string representing the date to parse. It must follow the
     * format specified by the {@code pattern} parameter.
     * @param pattern specifies the format that the string {@code date }
     * parameter must follow.
     * @param lenient specifies the lenient mode for parsing action.
     */
    public static Date getDate(String date, String pattern, boolean lenient) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(lenient);
        Date formattedDate = dateFormat.parse(date);
        return getDateWithoutAmbiguity(formattedDate, pattern);
    }

    public static Date getDateWithoutAmbiguity(Date date, String pattern) {
        if (isAmbiguosYearPattern(pattern) && date.before(getThresholdDate())) {
            date = increaseDate(date, 100, 'Y');
        }
        return date;
    }

    public static boolean isAmbiguosYearPattern(String pattern) {
        int length = pattern.length();
        int counter = 0;
        for (int i = 0; i < length; ++i) {
            char characterAt = pattern.charAt(i);
            if (characterAt == 'y') {
                counter++;
            }
        }
        return counter < 3;
    }

    /**
     * Check the date format. The correct format is ddMMyy
     *
     * @param date the date to check
     * @return true if the date is correct, false otherwise
     */
    public static boolean isValidDateFormat(String date) {
        boolean valid = true;
        if ((date == null) || (date.length() != 6) || (!StringUtils.isInteger(date))) {
            valid = false;
        }
        return valid;
    }

    /**
     * Check the time format. The correct format is
     *
     * @param date the hour to check
     * @return true if the hour is correct, false otherwise
     */
    public static boolean isValidTime(String time) {
        boolean valid = true;
        if ((time == null) || (time.length() != 4) || (!StringUtils.isInteger(time)) || (Integer.valueOf(time.substring(0, 2)).intValue() < 0) || // hour
                (Integer.valueOf(time.substring(0, 2)).intValue() > 23) || (Integer.valueOf(time.substring(2, 4)).intValue() < 0) || // minutes
                (Integer.valueOf(time.substring(2, 4)).intValue() > 59)) // minutes
        {
            valid = false;
        }
        return valid;
    }

    /**
     * Check the time format. The correct format is HH:MM
     *
     * @param time
     * @return
     */
    public static boolean isValidTimeWithSeparator(String time) {
        if (time != null) {
            if (time.contains(":")) {
                return isValidTime(time.replace(":", ""));
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     *
     * The isValidDate method check date validity.
     *
     * @param date the date to verify
     * @param pattern the date pattern to apply
     * @return true if the date is valid, false otherwise
     */
    public static boolean isValidDate(String date, String pattern) {
        boolean valid = true;
        if (date.length() != pattern.length()) {
            return false;
        }
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * The getSQLDate method retrieves a java.sql.Date object extracted from the
     * java util.Date {@code date} object parameter. This method is usually used
     * int the business layer.
     *
     * @param date a java.util.Date object.
     * @return a java.sql.Date object extracted from the java util.Date
     * {@code date} object parameter.
     */
    public static java.sql.Date getSQLDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * The getSQLDate method retrieves a java.sql.Date object extracted from the
     * long {@code millis} parameter representsenting milliseconds. This method
     * is usually used int the business layer.
     *
     * @param millis the difference, measured in milliseconds, between the
     * current time and midnight, January 1, 1970 UTC.
     * @return a java.sql.Date object extracted from the long {@code millis}
     * parameter
     */
    // lato Core
    public static java.sql.Date getSQLDate(long millis) {
        return new java.sql.Date(new Date(millis).getTime());
    }

    /**
     * The getSQLDateTime method retrieves a java.sql.Time object extracted from
     * the inputDate parameter representsenting a java.util.Date. This method is
     * usually used int the business layer.
     *
     * @param inputDate the input date in java.util.Date format.
     * @return a java.sql.Time object that contains the date and time
     * information as is stored into a db
     */
    public static java.sql.Time getSQLDateTime(java.util.Date inputDate) {
        java.sql.Time result = null;
        if (inputDate != null) {
            result = new java.sql.Time(inputDate.getTime());
        }
        return result;
    }

    /**
     * The getDefaultEndDate method retrieves the default end date of the Entity
     * objects, that is set on to the 31th, December of 9999. This method is
     * usually used int the business layer.
     *
     * @return the default end date of the Entity objects.
     */
    public static Date getDefaultEndDate() {
        return defaultEndDate;
    }

    /**
     * The getDefaultEndDate method retrieves the default end date of the Entity
     * objects, that is set on to the 31th, December of 9999. This method is
     * usually used int the business layer.
     *
     * @return the default end date of the Entity objects as a string.
     * @throws java.text.ParseException
     */
    public static String getDefaultEndDateString() throws ParseException {
        return getString(getDefaultEndDate());
    }

    /**
     * The getThresholdDate method retrieves the default thresholdDate date
     * useful to solve a bug, for ambiguos date ehare the pattern is specified
     * ambiguosly. This date is equal to 01/01/2000. This method is usually used
     * int the business layer.
     *
     * @return the default threshlod date.
     */
    public static Date getThresholdDate() {
        return thresholdDate;
    }

    /**
     * The getDate method generates a java.util.Date object parsing it with the
     * default pattern string "yyyyMMddHHmmss". This method is usually used in
     * the business layer.
     *
     * @param date the string
     * @return a java.util.Date object parsing it with the default pattern
     * string "yyyyMMddHHmmss". This method is usually used int the business
     * layer.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     * @see #getDate(String, String)
     */
    public static Date getDate(String date) throws ParseException {
        String pattern = "yyyyMMddHHmmss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if ((date == null) || (date.equals(""))) {
            throw new ParseException("<<DateUtils.getDate>> The date is null", 0);
        }
        return dateFormat.parse(date);
    }

    /**
     * The getDate method generates a java.util.Date object parsing it with the
     * default pattern string "yyyyMMddHHmmss". This method is usually used in
     * the business layer.
     *
     * @param date the string
     * @return a java.util.Date object parsing it with the default pattern
     * string "yyyyMMddHHmmss". This method is usually used int the business
     * layer.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     * @see #getDate(String, String)
     */
    public static Date getNullableDate(String date) {
        try {
            String pattern = "yyyyMMddHHmmss";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            if ((date == null) || (date.equals(""))) {
                return null;
            }
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * The getDate method generates a java.util.Date object parsing it with the
     * default pattern string "HH:mm". This method is usually used in the
     * business layer.
     *
     * @param date the string
     * @return a java.util.Date object parsing it with the default pattern
     * string "HH:mm". This method is usually used int the business layer.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     * @see #getDate(String, String)
     */
    public static Date getNullableTime(String date) {
        try {
            String pattern = "HH:mm";
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            if ((date == null) || (date.equals(""))) {
                return null;
            }
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    /**
     * The getString method retrieves a string representating the java.util.Date
     * {@code date} parameter, that follows the default pattern
     * "yyyyMMddHHmmss".This method is usually used in the business layer.
     *
     * @param date the date from wihch extrct the string.
     * @return a string representating the java.util.Date {@code date}
     * parameter.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     */
    public static String getString(Date date) throws ParseException {
        String pattern = "yyyyMMddHHmmss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getString method retrieves a string representating the java.util.Date
     * {@code date} parameter, that follows the default pattern
     * "yyyyMMddHHmmss".This method is usually used in the business layer.
     *
     * @param date the date from wihch extrct the string.
     * @return a string representating the java.util.Date {@code date}
     * parameter.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     */
    public static String getNullableString(Date date) {
        if (date == null) {
            return null;
        }
        String pattern = "yyyyMMddHHmmss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * The getString method retrieves a string representating the java.util.Date
     * {@code date} parameter, that follows the {@code pattern} parameter.
     *
     * @param date the date from wihch extract the string.
     * @param pattern the pattern from wihch extract the string.
     * @return a string representating the java.util.Date {@code date}
     * parameter.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     */
    public static String getString(Date date, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getString method retrieves a string representating the java.util.Date
     * {@code date} parameter, that follows the "yyyy-MM-ddTHH:mm:ss+HH:mm"
     * pattern.
     *
     * @param date the date from wihch extract the string.
     * @return a string representating the java.util.Date {@code date} parameter
     * formatted as Norfolk ESB wants.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code date} and the deafult pattern.
     */
    public static String getWSString(Date date) throws ParseException {
        String pattern = PatternEnumeration.WS_DATE_PATTERN.getValue();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getWSString>> The date is null", 0);
        }
        String dateString = dateFormat.format(date);
        return String.format("%sT%s:%s", dateString.substring(0, 10), dateString.substring(10, 21), dateString.substring(21, 23));
    }

    /**
     * Get the Date from the WS formatted date.
     *
     * @param wsDate the yyyy-MM-ddTHH:mm:ss+HH:mm format.
     * @return the java Date
     * @throws java.text.ParseException
     */
    public static Date getWSDate(String wsDate) throws ParseException {
        Date date = null;
        if (wsDate == null) {
            throw new ParseException("The date is null", 0);
        } else if (wsDate.trim().length() != 25) {
            throw new ParseException("Invalid lenght", 0);
        } else {
            String year = wsDate.substring(0, 4);
            String month = wsDate.substring(5, 7);
            String day = wsDate.substring(8, 10);
            String hour = wsDate.substring(11, 13);
            String minutes = wsDate.substring(14, 16);
            String seconds = wsDate.substring(17, 19);
            String hourTimeZone = wsDate.substring(20, 22);
            String minutesTimeZone = wsDate.substring(23, 25);
            if (!StringUtils.isInteger(year) || (Integer.valueOf(year).intValue() < 2000)) {
                throw new ParseException("Invalid year", 0);
            } else if (!StringUtils.isInteger(month) || (Integer.valueOf(month).intValue() < 1) || (Integer.valueOf(month).intValue() > 12)) {
                throw new ParseException("Invalid month", 0);
            } else if (!StringUtils.isInteger(day) || (Integer.valueOf(day).intValue() < 1) || (Integer.valueOf(day).intValue() > 31)) {
                throw new ParseException("Invalid day", 0);
            } else if (!StringUtils.isInteger(hour) || (Integer.valueOf(hour).intValue() < 0) || (Integer.valueOf(hour).intValue() > 23)) {
                throw new ParseException("Invalid hour", 0);
            } else if (!StringUtils.isInteger(minutes) || (Integer.valueOf(minutes).intValue() < 0) || (Integer.valueOf(minutes).intValue() > 59)) {
                throw new ParseException("Invalid minutes", 0);
            } else if (!StringUtils.isInteger(seconds) || (Integer.valueOf(seconds).intValue() < 0) || (Integer.valueOf(seconds).intValue() > 59)) {
                throw new ParseException("Invalid seconds", 0);
            } else if (!StringUtils.isInteger(hourTimeZone) || (Integer.valueOf(hourTimeZone).intValue() < 0) || (Integer.valueOf(hourTimeZone).intValue() > 23)) {
                throw new ParseException("Invalid hour time zone", 0);
            } else if (!StringUtils.isInteger(minutesTimeZone) || (Integer.valueOf(minutesTimeZone).intValue() < 0)
                    || (Integer.valueOf(minutesTimeZone).intValue() > 59)) {
                throw new ParseException("Invalid minutes time zone", 0);
            } else if (!isValidDate(day + month + year, "ddMMyyyy")) {
                throw new ParseException("Invalid date", 0);
            }
            date = getDate(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year), Integer.valueOf(hour), Integer.valueOf(minutes), Integer.valueOf(seconds));
        }
        return date;
    }

    /**
     * Format the ws date in the database date.
     *
     * @param wsDate the yyyy-MM-ddTHH:mm:ss+HH:mm format.
     * @return the formatted date in format yyyyMMddHHmmss.
     */
    public static String formatWSString(String wsDate) {
        StringBuilder formattedDate = new StringBuilder();
        if ((wsDate != null) && (wsDate.trim().length() == 25)) {
            formattedDate.append(wsDate.substring(0, 4)); // yyyy
            formattedDate.append(wsDate.substring(5, 7)); // MM
            formattedDate.append(wsDate.substring(8, 10)); // dd
            formattedDate.append(wsDate.substring(11, 13)); // HH
            formattedDate.append(wsDate.substring(14, 16)); // mm
            formattedDate.append(wsDate.substring(17, 19)); // ss
        }
        return formattedDate.toString();
    }

    /**
     * Check the format validity of the date.
     *
     * @param wsDate the yyyy-MM-ddTHH:mm:ss+HH:mm format.
     * @return true for valid date, false otherwise.
     */
    public static boolean isValidWSDate(String wsDate) {
        boolean isValid = true;
        if ((wsDate == null) || (wsDate.trim().length() != 25)) {
            isValid = false;
        } else {
            try {
                Date date = getWSDate(wsDate);
            } catch (Exception ex) {
                isValid = false;
            }
        }
        return isValid;
    }

    /**
     * The getTimeString method returns a string representation of the specified
     * java.util.Date {@code time} parameter.
     *
     * @param date the time of which to create the string representation.
     * @return string a string representation of the specified time.
     * @throws ParseException if the specified time is null.
     */
    public static String getTimeString(Date date) throws ParseException {
        String pattern = "HHmm";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getTimeString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getTime method returns a string representation of the specified
     * java.util.Date {@code time} parameter.
     *
     * @param date the time of which to create the string representation.
     * @param pattern specifies the format that the string {@code date }
     * parameter must follow.
     * @return string a string representation of the specified time.
     * @throws ParseException if the specified time is null.
     */
    public static String getTime(Date date, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getTimeString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getDateString method returns a string representation of the specified
     * date.
     *
     * @param date the date of which to create the string representation.
     * @return String a string representation of the specified date.
     * @throws ParseException if the specified date is null.
     */
    public static String getDateString(Date date) throws ParseException {
        String pattern = "yyyyMMdd";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getDateString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getDateString method returns a string representation expressed in the
     * format in input.
     *
     * @param dateTime the string representation, expressed in the format in
     * input, of the date to convert.
     * @param pattern The pattern to format the string.
     * @return string A string representation, expressed in the format in input,
     * of the specified {@code dateTime}.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code dateTime} and the default pattern.
     */
    public static String getDateString(String dateTime, String pattern) throws ParseException {
        Date date = DateUtils.getDate(dateTime);
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * The getTimeString method returns a string representation of the specified
     * java.util.Date {@code time} parameter.
     *
     * @param date the date and time of which to create the string
     * representation.
     * @param pattern The pattern to format the string.
     *
     * @return string a string representation of the specified time.
     * @throws ParseException if the specified time is null.
     */
    public static String getDateString(Date date, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        if (date == null) {
            throw new ParseException("<<DateUtils.getDateTimeString>> The date is null", 0);
        }
        return dateFormat.format(date);
    }

    /**
     * The getTimeString method returns a string in format
     *
     * @yyyyMMddHHmmss representation of the specified java.util.Date
     * {@code date} parameter.
     *
     * @param date the date and time of which to create the string
     * representation.
     * @return string a string representation of the specified time.
     * @throws ParseException if the specified date is null.
     * @deprecated ParseException?! WHY!! Use @link getDateFormated
     */
    @Deprecated
    public static String getDateTimeString(Date date) throws ParseException {
        if (date == null) {
            throw new ParseException("<<DateUtils.getDateTimeString>> The date is null", 0);
        }

        String pattern = "yyyyMMddHHmmss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * The getTimeString method returns a string in format <b>yyyyMMddHHmmss</b>
     * representation of the specified java.util.Date {@code date} parameter.
     *
     * @param date the date and time of which to create the string
     * representation.
     * @return string a string representation of the specified time.
     * @throws IllegalArgumentException if the specified date is null.
     */
    public static String getDateFormatted(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("<<DateUtils.getDateFormatted>> The date is null");
        }

        String pattern = "yyyyMMddHHmmss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * The getTimeString method returns a string representation expressed in
     * "dd/MM/yyyy HH:mm:ss" format of given {@code dateTime} string parameter
     * expressed in "yyyyMMddHHmmss" format.
     *
     * @param dateTime the string representation, expressed in "yyyyMMddHHmmss"
     * format, of the date to convert.
     * @return string a string representation, expressed in "dd/MM/yyyy
     * HH:mm:ss" format, of the specified {@code dateTime}.
     * @throws ParseException if the parsing fails because of some incongruences
     * between the {@code dateTime} and the default pattern.
     */
    public static String getDateTimeString(String dateTime) throws ParseException {
        Date date = DateUtils.getDate(dateTime);
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * The increaseDate method increases the date value of a specified amount.
     *
     * @param date the date to increase.
     * @param amount the value used to increase the date.
     * @param field a character specifying the amount type ('M'=month, 'Y'=year,
     * 'D'=day, 'H'=hour, 'm'=minutes, 's'=seconds).
     * @return date the date increased by specified amount.
     */
    public static Calendar increaseDate(Calendar date, int amount, char field) {
        switch (field) {
            case 'M': // date is increased of amount months
                date.add(Calendar.MONTH, amount);
                break;
            case 'Y': // date is increased of amount years
                date.add(Calendar.YEAR, amount);
                break;
            case 'D': // date is increased of amount days
                date.add(Calendar.DAY_OF_MONTH, amount);
                break;
            case 'H': // date is increased of amount hours
                date.add(Calendar.HOUR_OF_DAY, amount);
                break;
            case 'm': // date is increased of amount minutes
                date.add(Calendar.MINUTE, amount);
                break;
            case 's': // date is increased of amount seconds
                date.add(Calendar.SECOND, amount);
                break;
            default:
                break;
        }
        return date;
    }

    /**
     * The increaseDate method increases the date value of a specified amount.
     *
     * @param date the date to increase.
     * @param amount the value used to increase the date.
     * @param field a variable specifying the amount type (month, year, day,
     * hour, minutes, seconds).
     * @return date the date increased.
     */
    public static Date increaseDate(Date date, int amount, char field) {
        Calendar calendar, calendarResult;
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendarResult = increaseDate(calendar, amount, field);
        return calendarResult.getTime();
    }

    /**
     * The increaseDate method increases the date value of a specified amount of
     * milliseconds.
     *
     * @param date the date before the increment.
     * @param milliseconds the milliseconds to add.
     * @return the {@code date} increased by the specified {@code milliseconds}.
     */
    public static Date increaseDate(Date date, long milliseconds) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        long time = date.getTime();
        TimeZone tz1;
        TimeZone tz2;
        cal1.setTimeInMillis(time);
        time += milliseconds;
        cal2.setTimeInMillis(time);
        tz1 = cal1.getTimeZone();
        tz2 = cal2.getTimeZone();
        boolean condition1 = tz1.inDaylightTime(cal1.getTime());
        boolean condition2 = tz2.inDaylightTime(cal2.getTime());
        if (condition1 == condition2) {
            return cal2.getTime();
        } else {
            if (cal1.get(Calendar.MONTH) == 2) {
                time = time - tz1.getDSTSavings();
                cal2.setTimeInMillis(time);
            } else {
                time = time + tz1.getDSTSavings();
                cal2.setTimeInMillis(time);
            }
            return cal2.getTime();
        }
    }

    /**
     * The dateDifference method calculate the difference between the specified
     * first date and the specified second date.
     *
     * @param firstDate the first operand of the date difference.
     * @param secondDate the second operand of the date difference.
     * @return long the difference between the specified dates.
     * @throws EntityValidityException if the first date is less than the second
     * date.
     */
    public static long dateDifference(Date firstDate, Date secondDate) throws Exception {

        if (firstDate.before(secondDate)) {
            throw new Exception("The date \"" + firstDate.toString() + "\" is less than the date \"" + secondDate.toString() + "\"");
        }
        long dateDifference = firstDate.getTime() - secondDate.getTime();
        return dateDifference;
    }

    /**
     *
     * @param from
     * @param to
     * @return
     * @throws it.edea.ebooking.business.common.util.EntityValidityException
     */
    public static int numberOfDaysBetweenDates(Date from, Date to) throws Exception {
        long diff = DateUtils.dateDifference(to, from);
        long milPerDay = 1000 * 60 * 60 * 24;
        long days = diff / milPerDay;
        return (int) days;
    }

    /**
     * The dateDifference method calculate the difference in days between the
     * specified first "from" date and the specified second "to" date. If the
     * first input "from" date is after the second input "to" date, the
     * difference is negative.
     *
     * @param from
     * @param to
     * @return
     * @throws EntityValidityException
     */
    public static int getNumberOfDaysBetweenDates(Date from, Date to) throws Exception {
        long diff = DateUtils.getDateDifference(from, to);
        long milPerDay = 1000 * 60 * 60 * 24;
        long days = diff / milPerDay;
        return (int) days;
    }

    /**
     * The dateDifference method calculate the difference between the specified
     * first date and the specified second date. If the first input date is
     * after the second input date, the difference is negative.
     *
     * @param firstDate the first operand of the date difference.
     * @param secondDate the second operand of the date difference.
     * @return long the difference between the specified dates.
     * @throws EntityValidityException if the first date is less than the second
     * date.
     */
    public static long getDateDifference(Date firstDate, Date secondDate) throws Exception {

        long dateDifference = secondDate.getTime() - firstDate.getTime();
        return dateDifference;
    }

    /**
     * The removeSecondsFrom method removes the seconds value from the specified
     * date.
     *
     * @param date the date from which remove the seconds value.
     * @return the date without seconds value;
     */
    public static Date removeSecondsFrom(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.SECOND, 0);
        Date newDate = calendar.getTime();
        return newDate;
    }

    /**
     * The parseJourneyDuration method convert string in format XXX days XX
     * hours XX minutes in millisecond
     *
     * @param journeyDuration
     * @return long
     */
    public static long parseJourneyDuration(String journeyDuration) {
        String daysString = journeyDuration.substring(0, journeyDuration.indexOf(" days"));
        String hoursString = journeyDuration.substring((journeyDuration.indexOf(" days") + 6), journeyDuration.indexOf(" hours"));
        String minutesString = journeyDuration.substring((journeyDuration.indexOf(" hours") + 7), journeyDuration.indexOf(" minutes"));
        long daysInt = Integer.parseInt(daysString);
        long hoursInt = Integer.parseInt(hoursString);
        long minutesInt = Integer.parseInt(minutesString);
        long resultLong = daysInt * 86400000 + hoursInt * 3600000 + minutesInt * 60000;
        return resultLong;
    }

    /**
     * The getOnlyDate method convert an input date in a date without a time
     * information
     *
     * @param date the input date
     * @return the input date without a time information
     */
    public static Date getOnlyDate(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        gregorianCalendar.set(Calendar.HOUR_OF_DAY, 0);
        gregorianCalendar.set(Calendar.MINUTE, 0);
        gregorianCalendar.set(Calendar.SECOND, 0);
        gregorianCalendar.set(Calendar.MILLISECOND, 0);
        return gregorianCalendar.getTime();
    }

    /**
     * The getYearFromDate method retrieve the year from the date input
     * parameter.
     *
     * @param date the input date
     * @return the year date
     */
    public static String getYearFromDate(Date date) {
        String year;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        year = "" + calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     *
     * The getDateTo method returns the date input parameter setting time to
     * 23:59:59
     *
     * @param date the date to chnage.
     * @return true if the specified week day is contained, false otherwise.
     */
    public static Date getDateTo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 23, 59, 59);
        return calendar.getTime();
    }

    /**
     *
     * The getDateFrom method returns the date input parameter setting time to
     * 00:00:00
     *
     * @param date the date to chnage.
     * @return true if the specified week day is contained, false otherwise.
     */
    public static Date getDateFrom(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 00, 00, 00);
        return calendar.getTime();
    }

    /**
     *
     * The getDateFrom method returns the date input parameter with time
     *
     * @param date the date to change.
     * @return true if the specified week day is contained, false otherwise.
     */
    public static Date getDateTimeFrom(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return calendar.getTime();
    }

    /**
     * The compareDate method compares the dates specified in the parameters.
     *
     * @param dateA the first date to compare.
     * @param dateB the second date to compare.
     * @return the value 0 if the first date is equal to the second date; a
     * value less than 0 if the first date is before the second date; a value
     * greater than 0 if the first date is after the second date.
     */
    public static int compareDate(Date dateA, Date dateB) {
        Calendar calendarA = Calendar.getInstance();
        Calendar calendarB = Calendar.getInstance();
        calendarA.setTime(dateA);
        calendarB.setTime(dateB);
        return calendarA.compareTo(calendarB);
    }

    /**
     * The compareDate method compares the times specified in the parameters.
     *
     * @param timeFrom the first time to compare (HHmm).
     * @param timeTo the second time to compare (HHmm).
     * @return the value 0 if the timeFrom is equal to the timeTo; a value
     * greater than 0 if the timeFrom is before the timeTo; a value less than 0
     * if the timeFrom is after the timeTo.
     */
    public static int compareTime(String timeFrom, String timeTo) {
        int result = 0;
        if ((timeFrom != null) && (timeTo != null)) {
            int hourFrom = Integer.valueOf(timeFrom.substring(0, 2)).intValue();
            int hourTo = Integer.valueOf(timeTo.substring(0, 2)).intValue();
            int minutesFrom = Integer.valueOf(timeFrom.substring(2, 4)).intValue();
            int minutesTo = Integer.valueOf(timeTo.substring(2, 4)).intValue();

            if (hourFrom > hourTo) {
                result = -1;
            } else if (hourFrom < hourTo) {
                result = 1;
            } else if (minutesFrom > minutesTo) {
                result = -1;
            } else if (minutesFrom < minutesTo) {
                result = 1;
            }
        }
        return result;
    }

    /**
     * The method checks if given {@code date} is inside the date interval
     * identified by {@code startInterval} and {@code endInterval}.
     *
     * In other words, the method checks if {@code startInterval} &lt;=
     * {@code date} &lt;= {@code endInterval}.
     *
     * @param date the date to check.
     * @param startInterval the lower bound of the date interval.
     * @param endInterval the upper bound of the date interval.
     * @return if given {@code date} is inside the date interval, false
     * otherwise.
     */
    public static boolean isDateInsideInterval(Date date, Date startInterval, Date endInterval) {
        if ((date.after(startInterval) && date.before(endInterval)) || date.equals(startInterval) || date.equals(endInterval)) {
            return true;






        }
        return false;






    }

    /**
     * The method retrieve the last date in month for the given {@code Date}
     * object
     *
     * @param date The date for which retrieve the last date in month
     * @return The last date for the month
     */
    public static Date getLastDateOfMonth(Date date) {
        // Get a calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // Get the last date of the current month.






        int lastDate = calendar.getActualMaximum(Calendar.DATE);
        // Set the calendar date to the last date of the month so then we can
        // get the last day of the month
        calendar.set(Calendar.DATE, lastDate);






        return calendar.getTime();






    }

    /**
     * This method return true is date time of the input date is AM, false
     * otherwise
     *
     * @param date
     * @return
     */
    public static boolean isAM_Date(Date date) {
        // Get a calendar instance
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);






        int result = calendar.get(Calendar.AM_PM);






        if (result == 0) {
            return true;






        } else {
            return false;






        }
    }

    /**
     * This method return true is date time of the input date is PM, false
     * otherwise
     *
     * @param date
     * @return
     */
    public static boolean isPM_Date(Date date) {
        return !isAM_Date(date);






    }

    /**
     * Retrieves the day of week as a string.
     *
     * @param dayOfWeekInt the index of the day of week.
     * @return the day of week as a string.
     */
    public static String getDayOfWeekString(int dayOfWeekInt) {
        String stringToReturn = "SUNDAY";

        switch (dayOfWeekInt) {
            case Calendar.MONDAY:
                stringToReturn = "MONDAY";
                break;
            case Calendar.TUESDAY:
                stringToReturn = "TUESDAY";
                break;
            case Calendar.WEDNESDAY:
                stringToReturn = "WEDNESDAY";
                break;
            case Calendar.THURSDAY:
                stringToReturn = "THURSDAY";
                break;
            case Calendar.FRIDAY:
                stringToReturn = "FRIDAY";
                break;
            case Calendar.SATURDAY:
                stringToReturn = "SATURDAY";
                break;
            case Calendar.SUNDAY:
                stringToReturn = "SUNDAY";
                break;
            default:
                assert false : "Error parsing day of week !!!";
                break;
        }
        return stringToReturn;
    }

    /**
     *
     * @param daysOfWeek
     * @param day
     * @return
     */
    private static int getWeekDayIntValue(String[] daysOfWeek, String day) {
        for (int i = 0; i
                < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equals(day)) {
                return i + 1;
            }
        }
        assert false : "This should never happen !!!";
        return 1;
    }

    /**
     * The method builds a {@code Date} object whose date is numerically
     * increased by given {@code numberOfDaysFromToday} days from today's date
     * and whose time is set to given {@code hours}, {@code minutes} and
     * {@code seconds} values. Milliseconds are always set to zero.
     *
     * @param numberOfDaysFromToday the number of days to use to numerically
     * increase today's date. It can be either positive or negative.
     * @param hours a numeric value representing the hours (24 hours format) of
     * the date.
     * @param minutes a numeric value representing the minutes of the date.
     * @param seconds a numeric value representing the seconds of the date.
     * @return the date.
     */
    public static Date getDateFromToday(int numberOfDaysFromToday, int hours, int minutes, int seconds) {
        // get current date and time
        Calendar calendar = Calendar.getInstance();
        // increase current date and time of numberOfDaysFromToday
        calendar = increaseDate(calendar, numberOfDaysFromToday, 'D');
        // set hours
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        // set minutes
        calendar.set(Calendar.MINUTE, minutes);
        // set seconds
        calendar.set(Calendar.SECOND, seconds);
        // always set milliseconds to zero
        calendar.set(Calendar.MILLISECOND, 0);
        // return the date






        return calendar.getTime();






    }

    /**
     * The convertDateToCalendar method, converts a Date in a Calendar.
     *
     * @param date to convert.
     *
     * @return calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     *
     * The addTimeToDate method sets the time specified da timeToAdd into date
     * parameter.
     *
     * @param timeToAdd the time to set
     * @param date the date value to which add the time value.
     * @return Calendar a calendar object having the time specified
     */
    public static Calendar addTimeToDate(Date timeToAdd, Calendar date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeToAdd);
        date.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        date.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
        date.set(Calendar.SECOND, 0);






        return date;






    }

    /**
     *
     * The addHoursToDate method adds the hours to input (@code date).
     *
     * @param timeToSet
     *            the time to set
     * @param days the days number to which add the date value.
     * @return Date object having the time setted.
     */
    public static Date addHoursToDate(Date timeToSet, int hours) {
        if (null == timeToSet) {
            throw new IllegalArgumentException("timeToSet is null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeToSet);
        calendar.add(Calendar.HOUR_OF_DAY, hours);

        return calendar.getTime();
    }

    /**
     *
     * The addDaysToDate method adds the days to input (@code date).
     *
     * @param timeToSet
     *            the time to set
     * @param hours the hours number to which add the time value.
     * @return Date object having the date setted.
     */
    public static Date addDaysToDate(Date timeToSet, int days) {
        if (null == timeToSet) {
            throw new IllegalArgumentException("timeToSet is null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timeToSet);
        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar.getTime();
    }

    /**
     * Check id the dates are changed
     *
     * @param oldDateTime old date.
     * @param newDateTime new date.
     * @return true if changed, false otherwise.
     */
    public static boolean isDateChanged(Date oldDateTime, Date newDateTime) {
        boolean isChanged = false;

        if (((oldDateTime == null) && (newDateTime != null)) || ((oldDateTime != null) && (newDateTime == null))
                || ((oldDateTime != null) && (newDateTime != null) && !(oldDateTime.equals(newDateTime)))) {
            isChanged = true;
        }
        return isChanged;
    }

    /**
     * Creates a object Date (current date) with the hours and minutes in input.
     *
     * @param hours The hours of the date.
     * @param minutes The minutes of the date.
     * @return The current date with hours and minutes in input.
     */
    public static Date getTimeFromHoursAndMinutes(String hours, String minutes) {
        if (hours == null || "".equals(hours) || "HH".equals(hours) || minutes == null || "".equals(minutes) || "MM".equals(minutes)) {
            return null;
        }
        Date date = DateUtils.getDateFromToday(0, Integer.parseInt(hours), Integer.parseInt(minutes), 0);
        return date;
    }

    /**
     * This method perform a union of a date string in format ddMMyy and time
     * string in format HHmm and transform it in a date
     *
     * @param date date string in format ddMMyy
     * @param time time string in format HHmm
     * @return a date, union of date and time
     * @throws ParseException in case of wrong format of the input
     */
    public static Date getDateFromDateAndTimeStrings(String date, String time) throws ParseException {
        if (StringUtils.isEmpty(date) || StringUtils.isEmpty(time) || date.length() != 6 || time.length() != 4) {
            throw new ParseException("date or time is in wrong format", 0);
        }
        StringBuilder toReturn = new StringBuilder(14);
        // yy
        toReturn.append("20");
        // yy
        toReturn.append(date.substring(4, 6));
        // MM
        toReturn.append(date.substring(2, 4));
        // dd
        toReturn.append(date.substring(0, 2));
        // HHmm
        toReturn.append(time);
        // ss
        toReturn.append("00");

        return DateUtils.getDate(toReturn.toString());
    }

    /**
     * This method return a Date object obtained from the date parameter (in
     * string format ddMMyy), adding a number of days daysToAdd
     *
     * @param departurePort departure port, this parameter is is used for log
     * purpose
     * @param arrivalPort arrival port, this parameter is is used for log
     * purpose
     * @param date in format ddMMyy
     * @param daysToAdd days to add to the date
     * @throws EntityValidityException if the date format is wrong
     */
    public static Date getFormattedDate(String departurePort, String arrivalPort, String date, int daysToAdd) throws Exception {
        if (date.length() == 6) {
            try {
                Date toReturn = DateUtils.getDate(date, "ddMMyy");
                toReturn = DateUtils.increaseDate(toReturn, daysToAdd, 'D');
                return toReturn;
            } catch (ParseException e) {
                throw new Exception("The date format of the request leg " + departurePort + "-" + arrivalPort + " is wrong");
            }
        } else {
            throw new Exception("The date format of the request leg " + departurePort + "-" + arrivalPort + " is wrong");
        }
    }

    /**
     * The method convert the original format of a date string into new
     * specified format
     *
     * @param dateString The string to convert
     * @param originalPattern The original pattern of the date
     * @param newPattern The new pattern of the date
     * @return The date formatted with new pattern
     *
     * @throws ParseException
     */
    public static String convertDateStringFormat(String dateString, String originalPattern, String newPattern) throws ParseException {
        return DateUtils.getString(DateUtils.getDate(dateString, originalPattern), newPattern);
    }
}
