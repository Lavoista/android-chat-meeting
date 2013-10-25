package it.meet.service.common.util;

/**
 * This enumeration is useful to define patterns to match with regular expressions.
 * 
 * @author E-DEA Transport Technology.
 * @see it.edea.ebooking.util.StringUtils
 */
public enum PatternEnumeration {

    /**
     * Pattern used to specify a relative value for modify date value.
     */
    DATEPATTERN("[+,-][0-9]+[M,Y,D,H,m]"),
    /**
     * Pattern used to specify a relative value for modify fax value.
     */
    FAXPATTERN("[+]?[0-9]+"),
    /**
     * Pattern used to specify a relative value for modify number value.
     */
    NUMBERPATTERN("[+,-][0-9]+"),
    /**
     * Pattern used to specifiy a relative value for change capacity
     * of sailing products.
     */
    //CHANGECAPACITYPATTERN("((['F',+,-])?([0-9])+)|['B']"),
    CHANGECAPACITYPATTERN("((((['F',+,-])?)|((['F'][+,-])?))[0-9]{1,6}(['.'][0-9]{1,2})?)|['B']"),
    /**
     * The pattern used for times in the SpaceWatch functionality.
     */
    SPACE_WATCH_TIME_PATTERN("[0-2]?[0-9]{1}:[0-5]?[0-9]{1}"),
    /**
     * Telephone pattern
     */
    TELEPHONE_PATTERN("[+]{0,1}[0-9]+"),

    /**
     * Pattern for age range.
     */
    AGE_RANGE_PATTERN("((([<][=])|([>][=])|([<|>])){1}[0-9]+)|[0-9]+[-][0-9]+"),

    /**
     *
     */
    NEGATIVE_OR_POSITIVE_DECIMAL_PERCENTAGE_NUMBER("^[-+]?([0-9]*\\.?[0-9]*)?[%]{0,1}$"),

    POSITIVE_DECIMAL_PERCENTAGE_NUMBER("^[+]?([0-9]*\\.?[0-9]*)?[%]{0,1}$"),
    /**
     * Matches: 123 | 123.54 | -0.54 | -3
     */
    NEGATIVE_OR_POSITIVE_DECIMAL_NUMBER("^[-+]?([0-9]*\\.?[0-9]*)?$"),

    POSITIVE_DECIMAL_NUMBER("^[+]?([0-9]*\\.?[0-9]*)?$"),



    /**
     * Email pattern
     */
    EMAIL_PATTERN("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$"),

    /**
     * Registration Number for vehicles
     */
    REGISTRATION_NUMBER_PATTERN("^[A-Za-z0-9]*$"),   

    /**
     * Pattern for checking the validity of values in update massive for price wizard. Only relative values (fixed or percentage) are allowed.
     * Example: ALLOWED: +10, -10, +10%, -10%
     *          NOT ALLOWED: 10, 10%
     */
    UPDATE_MASSIVE_PATTERN("^[+,-]{1}[0-9]+[%]{0,1}$"),

    
     /**
     * Pattern for checking the validity of values in update massive for price wizard. 
     * Only relative integre or decimal values (fixed or percentage) are allowed.
     * Example: ALLOWED: +10, -10, +10%, -10%, +10.00, +10.25, -10.50
     *          NOT ALLOWED: 10, 10%
     */
    UPDATE_MASSIVE_PATTERN_DECIMAL("^[+,-]{1}[0-9]+[%]{0,1}|[+,-]{1}[0-9]+[\\.]{1}[0-9]+[%]{0,1}$"),
    /**
     * The allowed chars for massive wizard operations: 0123456789  %  -  .  +
     */
    WIZARD_MASSIVE_VALID_CHARS_PATTERN("[0-9%\\-\\.\\+]"),
    
     /**
     * Pattern for checking the validity of EMS values
     * Example: ALLOWED: F-B; A-C,F-F
     */
    EMS_PATTERN("^[A-Za-z]{1}[-]{1}[A-Za-z]{1}[,]{1}[A-Za-z]{1}[-]{1}[A-Za-z]{1}$"),

    /**
     * Pattern for checking the validity of time values in HH24:MI format.
     */
    TIME_24_HOURS_PATTERN("^([0-1][0-9]|[2][0-3]):([0-5][0-9])$"),

    /**
     * The pattern for date for WS
     */
    WS_DATE_PATTERN("yyyy-MM-ddHH:mm:ssZ"),

    /**
     * The pattern for availability date on Web Services booking messages.
     */
    AVAILABILITY_DATE_PATTERN("ddMMyyHHmmss"),

    /**
     * The pattern for sailing departure and arrival date on Web Services booking messages.
     */
    SAILING_DEPARTURE_ARRIVAL_DATE_PATTERN("ddMMyy"),

    /**
     * The pattern for sailing departure and arrival time on Web Services booking messages.
     */
    SAILING_DEPARTURE_ARRIVAL_TIME_PATTERN("HHmm"),


    ADD_LEG_DATE_PATTERN("dd/MM/yyyy HHmm"),

    /**
     * The pattern of date used for exchange rate integration web service
     */
    EXCHANGE_RATE_CONVERSION_DATE_PATTERN("yyyy-MM-dd"),


    /**
     * The pattern to validate IP ADDRESS
     */
    IPADDRESS_PATTERN ("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")


    ;

    /**
     * The pattern as string.
     */
    private String value;

    /**
     * Create new PatternEnumeration object.
     *
     * @param value the String value of an element of this enumeration.
     */
    private PatternEnumeration(String value) {
        this.value = value;
    }

    /**
     * Getter method for value property.
     * 
     * @return the value.
     */
    public String getValue() {
        return value;
    }
}
