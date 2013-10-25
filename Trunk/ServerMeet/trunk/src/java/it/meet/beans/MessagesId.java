package it.meet.beans;
// Generated 21-set-2013 9.59.58 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * MessagesId generated by hbm2java
 */
public class MessagesId  implements java.io.Serializable {


     private String username;
     private String usernamereceiver;
     private Date date;

    public MessagesId() {
    }

    public MessagesId(String username, String usernamereceiver, Date date) {
       this.username = username;
       this.usernamereceiver = usernamereceiver;
       this.date = date;
    }
   
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsernamereceiver() {
        return this.usernamereceiver;
    }
    
    public void setUsernamereceiver(String usernamereceiver) {
        this.usernamereceiver = usernamereceiver;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MessagesId) ) return false;
		 MessagesId castOther = ( MessagesId ) other; 
         
		 return ( (this.getUsername()==castOther.getUsername()) || ( this.getUsername()!=null && castOther.getUsername()!=null && this.getUsername().equals(castOther.getUsername()) ) )
 && ( (this.getUsernamereceiver()==castOther.getUsernamereceiver()) || ( this.getUsernamereceiver()!=null && castOther.getUsernamereceiver()!=null && this.getUsernamereceiver().equals(castOther.getUsernamereceiver()) ) )
 && ( (this.getDate()==castOther.getDate()) || ( this.getDate()!=null && castOther.getDate()!=null && this.getDate().equals(castOther.getDate()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getUsername() == null ? 0 : this.getUsername().hashCode() );
         result = 37 * result + ( getUsernamereceiver() == null ? 0 : this.getUsernamereceiver().hashCode() );
         result = 37 * result + ( getDate() == null ? 0 : this.getDate().hashCode() );
         return result;
   }   


}


