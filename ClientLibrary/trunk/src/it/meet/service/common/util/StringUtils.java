package it.meet.service.common.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An utility class for strings operations.
 *
 */
public class StringUtils {

    /**
     * The pattern of the sailing code.
     */
    private static final Pattern SAILING_CODE_PATTERN = Pattern.compile("[A-Z]{6}[0-9]{12}");
    /**
     * The pattern that the db key values must satisfy.
     */
    private static String alphabeticPattern = "[a-zA-Z]";
    /**
     * the pattern that the db key values must satisfy
     */
    private static String keyPattern = "[A-Z][A-Z0-9_-]*";
    /**
     * This character can be used to represent composite entity keys, as
     * separator. For example: productCode + productIndex as a key in a Map can
     * be transformed in productCode +
     *
     * @keySeparator + productIndex. Entity keys contains name ore codes that
     * soddisfy
     * @keyPattern, then, this character must be not included in it to avoid
     * posible conflicts.
     */
    public static char keySeparator = '&';
    /**
     * The available status of the sailing for all the requested products
     */
    private static String availabileSailingStatus = "A";
    /**
     * The not available status of the sailing for all the requested products
     */
    private static String notAvailabileSailingStatus = "F";

    /**
     * The isNotEmpty method returns true if the {@code string} parameter is not
     * null and have a length greater than zero, false otherwise.
     *
     * @param string the string to test.
     * @return true if the {@code string} parameter is not null and have a
     * length greter than zero, false otherwise.
     */
    public static boolean isNotEmpty(String string) {
        return (string != null && string.trim().length() > 0);
    }

    /**
     * The isEmpty method returns true if the {@code string} parameter is null
     * or have a length equals to zero, false otherwise.
     *
     * @param string the string to test.
     * @return true if the {@code string} parameter is null or have a length
     * equals to zero, false otherwise.
     */
    public static boolean isEmpty(String string) {
        return (string == null || string.trim().length() == 0);
    }

    /**
     * The getString method returns an empty string if the string in input is
     * null otherwise returns the string in input.
     *
     * @param string the string to test.
     * @return an empty string if the string in input is null, otherwise returns
     * the string in input.
     */
    public static String getString(String string) {
        if (string == null || string.trim().length() == 0) {
            return "";
        } else {
            return string;
        }
    }

    /**
     * The isEmptyOrLessThan method returns true if the {@code string} parameter
     * is null, have a length equals to zero or less than the {@code num}
     * parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is null or have a length
     * equals to zero or have a length less than the {@code num} parameter,
     * false otherwise.
     */
    public static boolean isEmptyOrLessThan(String string, int num) {
        return isEmpty(string) || string.length() < num;
    }

    /**
     * The isEmptyOrLessThanEqual method returns true if the {@code string}
     * parameter is null, have a length equals to zero or less than or equal to
     * the {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is null, have a length
     * equals to zero or less than or equal to the {@code num} parameter, false
     * otherwise.
     */
    public static boolean isEmptyOrLessThanEqual(String string, int num) {
        return isEmpty(string) || string.length() <= num;
    }

    /**
     * The isEmptyOrGreaterThan method returns true if the {@code string}
     * parameter is null, have a length equals to zero or is greater than or
     * equal to the {@code
     * num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is null, have a length
     * equals to zero or is greater than or equal to the {@code num} parameter,
     * false otherwise.
     */
    public static boolean isEmptyOrGreaterThan(String string, int num) {
        return isEmpty(string) || string.length() > num;
    }

    /**
     * The isEmptyOrGreaterThanEqual method returns true if the {@code string}
     * parameter is null, have a length equals to zero or is greater than or
     * equal to the {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is null, have a length
     * equals to zero or is greater than or equal to the {@code num} parameter,
     * false otherwise.
     */
    public static boolean isEmptyOrGreaterThanEqual(String string, int num) {
        return isEmpty(string) || string.length() >= num;
    }

    /**
     * The isNotEmptyAndLessThan method returns true if the {@code string}
     * parameter is not null, have a length greater than zero and less than the
     * {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than zero and less than the {@code num} parameter, false
     * otherwise.
     */
    public static boolean isNotEmptyAndLessThan(String string, int num) {
        return isNotEmpty(string) && string.length() < num;
    }

    /**
     * The isNotEmptyAndLessThanEqual method returns true if the {@code string}
     * parameter is not null, have a length greater than zero and less than or
     * equal to the {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than zero and less than or equal to the {@code num} parameter,
     * false otherwise.
     */
    public static boolean isNotEmptyAndLessThanEqual(String string, int num) {
        return isNotEmpty(string) && string.length() <= num;
    }

    /**
     * The isNotEmptyAndNotEqualTo method returns true if the {@code string}
     * parameter is not null, have a length equal to param num. False otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * equal to param num. False otherwise.
     */
    public static boolean isNotEmptyAndNotEqualTo(String string, int num) {
        return isNotEmpty(string) && string.length() != num;
    }

    /**
     * The isNotEmptyAndGreaterThan method returns true if the {@code string}
     * parameter is not null, have a length greater than the {@code num}
     * parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than the {@code num} parameter, false otherwise.
     */
    public static boolean isNotEmptyAndGreaterThan(String string, int num) {
        return isNotEmpty(string) && string.length() > num;
    }

    /**
     * The isNotEmptyAndGreaterThanEqual method returns true if the
     * {@code string} parameter is not null, have a length greater than or equal
     * to the {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than or equal to the {@code num} parameter, false otherwise.
     */
    public static boolean isNotEmptyAndGreaterThanEqual(String string, int num) {
        return isNotEmpty(string) && string.length() >= num;
    }

    /**
     * The isLessThan method returns true if the {@code string} parameter is not
     * null and have a length less than the {@code num} parameter, false
     * otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null and have a
     * length less than the {@code num} parameter, false otherwise.
     */
    public static boolean isLessThan(String string, int num) {
        return string != null && string.length() <= num;
    }

    /**
     * The isLessThanEqual method returns true if the {@code string} parameter
     * is not null and have a length less than or equal to the {@code num}
     * parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null and have a
     * length less than or equal to the {@code num} parameter, false otherwise.
     */
    public static boolean isLessThanEqual(String string, int num) {
        return string != null && string.length() <= num;
    }

    /**
     * The isGreaterThan method returns true if the {@code string} parameter is
     * not null and have a length greater than the {@code num} parameter, false
     * otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null and have a
     * length greater than the {@code num} parameter, false otherwise.
     */
    public static boolean isGreaterThan(String string, int num) {
        return string != null && string.length() > num;
    }

    /**
     * The isGreaterThanEqual method returns true if the {@code string}
     * parameter is not null and have a length greater than or equal to the
     * {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null and have a
     * length greater than or equal to the {@code num} parameter, false
     * otherwise.
     */
    public static boolean isGreaterThanEqual(String string, int num) {
        return string != null && string.length() >= num;
    }

    /**
     * The isEmptyOrNotEqual method returns true if the {@code string} parameter
     * is null, have a length equals to zero, or is not equals to the
     * {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is null, have a length
     * equals to zero, or is not equals to the {@code num} parameter, false
     * otherwise.
     */
    public static boolean isEmptyOrNotEqual(String string, int num) {
        return isEmpty(string) || string.length() != num;
    }

    /**
     * The isNotEmptyAndEqual method returns true if the {@code string}
     * parameter is not null, have a length greater than zero, and is equal to
     * the {@code num} parameter, false otherwise.
     *
     * @param string the string to test.
     * @param num the number of characters used to test the length of the
     * {@code string} parameter.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than zero, and is equal to the {@code num} parameter, false
     * otherwise.
     */
    public static boolean isNotEmptyAndEqual(String string, int num) {
        return !isEmpty(string) && string.length() == num;
    }

    /**
     * The getTrueOrFalse method is an utility method that retrieves the true
     * boolean value if the {@code yesOrNo} parameter is "Y" or false when its
     * value is false.
     *
     * @param yesOrNo a string value.
     * @return the true boolean value if the {@code yesOrNo} parameter is "Y" or
     * false when its value is false.
     */
    public static boolean getTrueOrFalse(String yesOrNo) {
        if (yesOrNo == null || (!yesOrNo.equalsIgnoreCase("Y") && !yesOrNo.equalsIgnoreCase("N"))) {
            assert false : String.format("yesOrNo=%s", yesOrNo);
        }
        boolean flag = false;
        if (yesOrNo != null && yesOrNo.equalsIgnoreCase("Y")) {
            flag = true;
        }
        return flag;
    }

    /**
     * The getTrueOrFalseChar method is an utility method that retrieves the
     * true boolean value if the {@code yesOrNo} parameter is "Y" or false when
     * its value is false.
     *
     * @param yesOrNo a char value.
     * @return the true boolean value if the {@code yesOrNo} parameter is "Y" or
     * false when its value is false.
     */
    public static boolean getTrueOrFalseChar(char yesOrNo) {
        if (yesOrNo == ' ' || !(yesOrNo == 'Y') && !(yesOrNo == 'N')) {
            assert false : String.format("yesOrNo=%s", yesOrNo);
        }
        boolean flag = false;
        if (yesOrNo == 'Y') {
            flag = true;
        }
        return flag;
    }

    /**
     * The getYesOrNo method is an utility method that retrieves the "Y" string
     * if the {@code flag} is true or "N" when its value false.
     *
     * @param flag a boolean value.
     * @return the "Y" string if the {@code flag} is true or false when its
     * value "N".
     */
    public static String getYesOrNo(boolean flag) {
        return flag ? "Y" : "N";
    }

    /**
     * The getYesOrNoChar method is an utility method that retrieves the 'Y'
     * char if the {@code flag} is true or 'N' when its value false.
     *
     * @param flag a boolean value.
     * @return the 'Y' char if the {@code flag} is true or false when its value
     * 'N'.
     */
    public static char getYesOrNoChar(boolean flag) {
        return flag ? 'Y' : 'N';
    }

    /**
     * The checkPhoneNumberValidity method returns true if and only if the
     * {@code phoneNumber} parameter is a string compatible with the phone
     * number standard syntax, otherwise false.
     *
     * @param phoneNumber the phone number string to test.
     * @return true if and only if the {@code phoneNumber} parameter is a string
     * compatible with the phone number standard syntax, otherwise false.
     */
    public static boolean checkPhoneNumberValidity(String phoneNumber) {
        if (isEmpty(phoneNumber)) {
            return false;
        }
        Pattern pattern = Pattern // compile regular expression
                .compile("[+]?[\\d]+(-\\d){0,}[\\s{0,}\\d{0,}]+");
        Matcher matcher = pattern.matcher(phoneNumber); // create matcher object
        // with parameter email
        boolean checkMail = matcher.matches(); // check phone number validity
        return checkMail;
    }

    /**
     * The checkEmailValidity method returns true if and only if the
     * {@code email} parameter is a string compatible with the email standard
     * syntax, otherwise false.
     *
     * @param email the email string to test.
     * @return true if and only if the {@code email} parameter is a string
     * compatible with the email standard syntax, otherwise false.
     */
    public static boolean checkEmailValidity(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        Pattern pattern = Pattern // compile regular expression
                .compile("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]{2,}+.+[a-zA-Z0-9]{2,}+$");
        Matcher matcher = pattern.matcher(email); // create matcher object
        // with parameter email
        boolean checkMail = matcher.matches(); // check email validity
        return checkMail;
    }

    /**
     * The getTypologicalFormattedKey method returns a string formatted width
     * spaces trimming and upper case
     *
     * @param key the input key string
     * @return the formatted string
     */
    public static String getTypologicalFormattedKey(String key) {
        String result = null;
        if (key != null) {
            result = key.trim().toUpperCase();
        }
        return result;
    }

    /**
     * The getTrimmedString method returns a string width spaces trimming
     *
     * @param input the input string
     * @return the trimmed string
     */
    public static String getTrimmedString(String input) {
        String result = null;
        if (input != null) {
            result = input.trim();
        }
        return result;
    }

    /**
     * The formatSearchString method returns a string width spaces trimming,
     * upper case and treplace a single quotation marks with two single
     * quotation marks
     *
     * @param input the input string
     * @return the trimmed string
     */
    public static String formatSearchString(String input) {
        String result = null;
        if (input != null) {
            result = input.replaceAll("'", "''").toUpperCase().trim();
        }
        return result;
    }

    /**
     * Create a string pad filled with a {@code character} with {@code length}
     * size.
     *
     * @param character the character to use to fill the string pad.
     * @param length the length of the string to return.
     * @return retrieves a string of length {@code lLength} composed by
     * {@code character} chars.
     */
    public static String createPadString(char character, int length) {
        String result = "";
        String fillChar = "";
        if ((character >= '\u0000') && (character <= '\u0020')) {
            fillChar = " ";
        } else {
            fillChar += character;
            if (fillChar.length() == 0) {
                fillChar = " ";
            }
        }
        for (int index = 0; index < length; index++) {
            result += fillChar;
        }
        return result;
    }

    /**
     * Performs a left pad of the {@code toPad} argument, filling it with
     * {@code character} to reach {@code totalLength} value length. The leftPad
     * method so retrieves a string of length {@code totalLength} where the last
     * part is {@code toPad} and the first part is filled with
     * {@code totalLength} - {@code toPad} .length {@code character}. If the
     * length of {@code toPad} argument is greater or equal than
     * {@code totalLength}, no pad will be performed and the string
     * {@code toPad} will be returned unchanged. If the {@code toPad} argument
     * is null or an empty string, than a string of length {@code totalLength}
     * filled with {@code character} will be returned. If the {@code character}
     * argument is null or an empty string, than will be used a space character
     * to perform the padding.
     *
     * @param toPad the String to pad.
     * @param character the character to use to fill the string to pad.
     * @param totalLength the length of the string to return.
     * @return retrieves a string of length {@code totalLength} where the last
     * part is {@code toPad} and the first part is filled with {@code totalLength} - {@code
     *         toPad}.length {@code character}.
     */
    public static String leftPad(String toPad, char character, int totalLength) {
        String result = null;
        if (toPad != null) {
            result = createPadString(character, totalLength - toPad.length()) + toPad;
        }
        return result;
    }

    /**
     * Performs a right pad of the {@code toPad} argument, filling it with
     * {@code character} to reach {@code totalLength} value length. The rightPad
     * method so retrieves a string of length {@code totalLength} where the
     * first part is {@code toPad} and the last part is filled with
     * {@code totalLength} - {@code toPad} .length {@code character}. If the
     * length of {@code toPad} argument is greater or equal than
     * {@code totalLength}, no pad will be performed and the string
     * {@code toPad} will be returned unchanged. If the {@code toPad} argument
     * is null or an empty string, than a string of length {@code totalLength}
     * filled with {@code character} will be returned. If the {@code character}
     * argument is null or an empty string, than will be used a space character
     * to perform the padding.
     *
     * @param toPad the String to pad.
     * @param character the character to use to fill the string to pad.
     * @param totalLength the length of the string to return.
     * @return retrieves a string of length {@code totalLength} where the last
     * part is {@code toPad} and the first part is filled with {@code totalLength} - {@code
     *         toPad}.length {@code character}.
     */
    public static String rightPad(String toPad, char character, int totalLength) {
        String result = null;
        if (toPad != null) {
            result = toPad + createPadString(character, totalLength - toPad.length());
        }
        return result;
    }

    /**
     * Performs a string conversion of {@code value} with a left zero pad
     * filling with {@code totalLength} return string length. This method return
     * a string of length {@code totalLength} where the first part is the value
     * and the last part is filled with
     * {@code totalLength} - {@code toPad}.length zero. If the length of
     * {@code value} argument is greater or equal than {@code totalLength}, no
     * pad will be performed and the string {@code value} will be returned
     * unchanged.
     *
     * @param value the value to convert.
     * @param padLength the length of the string to return.
     * @return a string of length {@code totalLength} where the last part is
     * {@code value} and the first part is filled with
     * {@code totalLength} - {@code toPad} .length Zero.
     */
    public static String longToZeroPadString(long value, int padLength) {
        String result = "";
        String valueString = String.valueOf(Math.abs(value));
        if (value < 0) {
            result += "-";
            padLength--;
        }
        result += StringUtils.leftPad(valueString, '0', padLength);
        return result;
    }

    /**
     *
     */
    public static String getSailingStatusAvailability(boolean sailingStatusAvailability) {
        if (sailingStatusAvailability) {
            return availabileSailingStatus;
        } else {
            return notAvailabileSailingStatus;
        }
    }

    /**
     *
     */
    public static boolean getSailingStatusAvailability(String sailingStatusAvailability) {
        if (sailingStatusAvailability.equals(availabileSailingStatus)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * asciiToString METHOD decode an ascii element in a String element.
     *
     * @param int asciiNumber
     * @return a String that rappresent a string of that ascii element.
     */
    public static String asciiToString(int asciiNumber) {
        char character = (char) asciiNumber;
        return "" + character;
    }

    /**
     * Decode the superPNR. The first 3 char decoded as following
     * 01=A,02=B.....26=Z
     *
     *
     * @param superPNR
     * @return String superPNR decode or null if superPNR in input is not valid
     */
    public static String decodeSuperPNR(String superPNR) {
        // check if first 3 chars of superPNR are correct (capital letters)
        int firstLetter = Integer.parseInt(superPNR.substring(0, 2));
        int secondLetter = Integer.parseInt(superPNR.substring(2, 4));
        int thirdLetter = Integer.parseInt(superPNR.substring(4, 6));
        if ((firstLetter + 64) > 90 || (secondLetter + 64) > 90 || (thirdLetter + 64) > 90) {
            return null;
        }
        // decode SUPERPNR
        String superPNRConverted = "" + (char) (firstLetter + 64) + (char) (secondLetter + 64) + (char) (thirdLetter + 64);
        superPNRConverted = superPNRConverted + superPNR.substring(6, 10);
        return superPNRConverted;
    }

    /**
     * This method check if superPnr in input is correct. The correct format is
     * always XXX9999 first 3 chars are letters [A-Z] and last 4 char are
     * numbers [0-9].
     *
     * @param superPnrIn
     * @return true if is correct false otherwise
     */
    public static boolean checkSuperPNR(String superPnrIn) {
        if (superPnrIn == null || superPnrIn.length() != 7) {
            return false;
        }
        String superPnr = superPnrIn.toUpperCase();
        String superPnrLetter = superPnr.substring(0, 3);
        String superPnrDigits = superPnr.substring(3, 7);
        Pattern patternLetters = Pattern.compile("[A-Z][A-Z][A-Z]");
        Matcher matchLetters = patternLetters.matcher(superPnrLetter);
        Pattern patternDigits = Pattern.compile("[0-9][0-9][0-9][0-9]");
        Matcher matchDigits = patternDigits.matcher(superPnrDigits);
        if (!matchLetters.matches()) {
            return false;
        }
        if (!matchDigits.matches()) {
            return false;
        }
        return true;
    }

    /**
     * This method convert the superPnr in input with following rule: assumed
     * that SuperPNR in input is always XXX9999 first 3 chars converted
     * A=01,B=02...Z=26 for total 10 chars.
     *
     * @param superPnrIn
     * @return String the superPnr converted or null if SuperPNR isn't correct
     */
    public static String convertSuperPNR(String superPnrIn) {
        if (!checkSuperPNR(superPnrIn)) {
            return null;
        }
        String superPnrOut = "";
        String superPnr = superPnrIn.toUpperCase();
        String superPnrLetter = superPnr.substring(0, 3);
        for (int i = 0; i < superPnrLetter.length(); i++) {
            int asciiCode = (int) superPnrLetter.toUpperCase().charAt(i);
            if (asciiCode > 73) {
                superPnrOut += (asciiCode - 64);
            } else {
                superPnrOut += "0" + (asciiCode - 64);
            }
        }
        superPnrOut += superPnr.substring(3, 7);
        return superPnrOut;
    }

    /**
     * Check that the String is a number.
     *
     * @param input the String to check
     * @return true if is a number, false otherwise
     */
    public static boolean isInteger(String input) {
        return containsOnlyNumbers(input, false);
    }

    public static boolean containsOnlyNumbers(String str, boolean checkDecimals) {
        // It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!checkDecimals) {
                // If we find a non-digit character we return false.
                if (!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            } else {
                if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != '.')) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * Format the given nunber with specified number of decimals digits. If
     * needed a round is made automatically
     *
     * @param num The number to format
     * @param numberOfDecimals The number of decimals digits
     * @return Formatted number with specified number of decimals digits
     */
    public static String toFixedDecimal(double num, int numberOfDecimals) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
        formatter.setMaximumFractionDigits(numberOfDecimals);
        formatter.setMinimumFractionDigits(numberOfDecimals);
        formatter.setGroupingUsed(false);
        return formatter.format(num);
    }

    /**
     *
     * @return a random numeric String with maximum length = 35 The returned
     * String is based on the current date (at millisecond precision)
     * @throws java.text.ParseException
     */
    public static String getMax35charRandomNumber() throws ParseException {
        Random rand = new Random();
        StringBuilder randStr = new StringBuilder(DateUtils.getString(new Date(), "ddMMyyyyHHmmssSSS") + rand.nextInt(999999999) + rand.nextInt(999999999));
        int randStrLength = randStr.length();
        int maximumStrLength = randStrLength > 35 ? 35 : randStrLength;
        return randStr.toString().trim().substring(0, maximumStrLength);
    }

    /**
     * This method checks if the input is a decimal number
     *
     * @param str
     * @return boolean true if the field in input is a decimal number ,
     * otherwise false.
     */
    public static boolean isDecimalNumber(String str) {
        // It can't contain only numbers if it's null or empty...
        if (str == null) {
            return false;
        }
        try {
            //Double.parseDouble(str);
            return containsOnlyNumbers(str, true);
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * The isNotEmptyHHTimeField method returns true if the {@code string}
     * parameter is not null, have a length greater than zero and is not equal
     * to HH string value, false otherwise.
     *
     * @param string the string to test.
     * @return true if the {@code string} parameter is not null, have a length
     * greter than zero and is not equal to HH string value, false otherwise.
     */
    public static boolean isNotEmptyHHTimeField(String string) {
        boolean returnValue = false;
        if (string != null && string.trim().length() > 0) {
            if (!string.equalsIgnoreCase("HH")) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * The isNotEmptyMMTimeField method returns true if the {@code string}
     * parameter is not null, have a length greater than zero and is not equal
     * to MM string value, false otherwise.
     *
     * @param string the string to test.
     * @return true if the {@code string} parameter is not null, have a length
     * greater than zero and is not equal to MM string value, false otherwise.
     */
    public static boolean isNotEmptyMMTimeField(String string) {
        boolean returnValue = false;
        if (string != null && string.trim().length() > 0) {
            if (!string.equalsIgnoreCase("MM")) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    /**
     * Check that the input String contains only alphabetic characters.
     *
     * @param toCheck the String to check.
     *
     * @return true if the String has only alphabetic characters, false
     * otherwise.
     */
    public static boolean isAlphabeticString(String toCheck) {
        if (toCheck == null) {
            return false;
        }
        Pattern p = Pattern.compile(alphabeticPattern);
        Matcher m = p.matcher(toCheck);
        return m.matches();
    }

    /**
     * The method returns true if the given {@code sailingCode} matches the
     * {@code sailingCodePattern}, false otherwise.
     *
     * @param sailingCode
     * @return
     */
    public static boolean isValidSailingCode(String sailingCode) {
        Matcher matcher = SAILING_CODE_PATTERN.matcher(sailingCode);
        return matcher.matches();
    }

    /**
     * Returns the line separator string used by the OS
     *
     * @return
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }

    /**
     * If String in input is null return string empty ""
     *
     * @param toCheck
     * @return
     */
    public static String checkNull(String toCheck) {
        if (toCheck == null) {
            return "";
        } else {
            return toCheck;
        }
    }

    /**
     * Encloses the string in CDATA tags
     *
     * @param value
     * @return
     */
    public static String addCDATAforXml(String value) {
        return String.format("<![CDATA[%s]]>", StringUtils.isNotEmpty(value) ? value : "");
    }

    /**
     * Returns a new string that represents a substring of
     * <code>value</code> with left margin zero and right margin equal to
     * <code>lenght</code>. If
     * <code>value</code> is empty or less
     * <code>lenght</code>, the method return his reference.
     * <p>
     *
     * @param value string to truncate.
     * @param lenght max chars of the string.
     * @return If <code>value</code> is empty or less <code>lenght</code> else
     * returns a reference to <code>value</code>, otherwise a substring
     * of <code>value</code> with left margin zero and right margin equal
     * to <code>lenght</code>.
     * @exception IndexOutOfBoundsException if the <code>lenght</code> is
     * negative.
     */
    public static String troncates(String value, int lenght) {
        String troncated = value;

        if (lenght < 0) {
            throw new IndexOutOfBoundsException("StringUtils.troncates lenght is negative");
        }

        if (!isEmptyOrLessThanEqual(value, lenght)) {
            troncated = value.substring(0, lenght);
        }

        return troncated;
    }

    /**
     * The method replaces with the {@code toReplace} parameter the piece of
     * string beginning form {@code beginIndex} parameter into the
     * {@code inputString} parameter
     *
     * For example: inputString = "ABCDEFG" beginIndex = 2 toReplace = "123
     *
     * returns AB123FG
     *
     * @param inputString The original string
     * @param beginIndex The index from which start the replacing
     * @param toReplace The string that must be used in the new string
     * @return
     */
    public static String replace(String inputString, int beginIndex, String toReplace) {

        if (StringUtils.isEmpty(inputString)) {
            return toReplace;
        }

        if (beginIndex >= inputString.length()) {
            throw new IndexOutOfBoundsException("StringUtils.replace: index " + beginIndex + " is out of length for the string " + inputString);
        }

        String newString = inputString.substring(0, beginIndex);
        newString += toReplace;
        if (newString.length() >= inputString.length()) {
            return newString;
        } else {
            newString += inputString.substring(beginIndex + toReplace.length());
        }

        return newString;
    }

    /**
     * Returns the string representation of the
     * <code>Object</code> argument.
     *
     * @param obj an <code>Object</code>.
     * @return if the argument is <code>null</code>, then a empty string;
     * otherwise, the value of <code>obj.toString()</code> is returned.
     * @see java.lang.Object#toString()
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
}
