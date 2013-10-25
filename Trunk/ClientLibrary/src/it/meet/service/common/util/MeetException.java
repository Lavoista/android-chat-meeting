package it.meet.service.common.util;

import java.text.MessageFormat;

/**
 *
 * @author Luigi
 */
public class MeetException extends Exception {

    private ErrorCodeEnumeration codeEnumeration;
    private Object[] arguments;

    /**
     * Create a new MeetExcdeption
     *
     * @param codeEnumeration
     */
    public MeetException(ErrorCodeEnumeration codeEnumeration, Exception ex) {
        super(ex);
        this.codeEnumeration = codeEnumeration;
    }

    /**
     * Create a new MeetExcdeption
     *
     * @param codeEnumeration
     */
    public MeetException(ErrorCodeEnumeration codeEnumeration, Object... arguments) {
        super();
        this.codeEnumeration = codeEnumeration;
        this.arguments = arguments;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        if (this.codeEnumeration != null) {
            return codeEnumeration.getErrorCode();
        } else {
            return "";
        }
    }

    public String getErrorDescription() {
        if (this.codeEnumeration != null) {
            String errorDescripition = codeEnumeration.getErrorDescription();
            if (arguments != null && arguments.length > 0) {
                errorDescripition = MessageFormat.format(errorDescripition, arguments);
            }
            return errorDescripition;
        } else {
            return "";
        }
    }
}
