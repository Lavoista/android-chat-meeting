
package it.meet.service.messaging;
import java.io.Serializable;

/**
 *
 * @author tommy
 */
public enum ContentType implements Serializable {

    /*
     * type of content of a message
     */
    TEXT("TEXT"),
    IMAGE("IMAGE"),
    AUDIO("AUDIO"),
    VIDEO("VIDEO");
    
    private String contentType;
    
    private ContentType(String contentType){
        this.contentType = contentType;
    }

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
