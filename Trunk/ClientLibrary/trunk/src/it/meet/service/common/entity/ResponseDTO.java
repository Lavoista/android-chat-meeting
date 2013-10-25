package it.meet.service.common.entity;

import java.io.Serializable;

/**
 *
 * @author luigi.vorraro
 */
public class ResponseDTO implements Serializable {
    /**
     * The error Code
     */
    private String errorCode;
    /**
     * The error message
     */
    private String errorDescription;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorDescription
     */
    public String getErrorDescription() {
        return errorDescription;
    }

    /**
     * @param errorDescription the errorDescription to set
     */
    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
