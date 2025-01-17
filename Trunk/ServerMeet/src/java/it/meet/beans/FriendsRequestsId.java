package it.meet.beans;
// Generated 21-set-2013 9.59.58 by Hibernate Tools 3.2.1.GA

/**
 * FriendsRequestsId generated by hbm2java
 */
public class FriendsRequestsId implements java.io.Serializable {

    private String username;
    private String usernamefriend;
    private String startdate;

    public FriendsRequestsId() {
    }

    public FriendsRequestsId(String username, String usernamefriend, String startdate) {
        this.username = username;
        this.usernamefriend = usernamefriend;
        this.startdate = startdate;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernamefriend() {
        return this.usernamefriend;
    }

    public void setUsernamefriend(String usernamefriend) {
        this.usernamefriend = usernamefriend;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.username != null ? this.username.hashCode() : 0);
        hash = 59 * hash + (this.usernamefriend != null ? this.usernamefriend.hashCode() : 0);
        hash = 59 * hash + (this.startdate != null ? this.startdate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FriendsRequestsId other = (FriendsRequestsId) obj;
        if ((this.username == null) ? (other.username != null) : !this.username.equals(other.username)) {
            return false;
        }
        if ((this.usernamefriend == null) ? (other.usernamefriend != null) : !this.usernamefriend.equals(other.usernamefriend)) {
            return false;
        }
        if ((this.startdate == null) ? (other.startdate != null) : !this.startdate.equals(other.startdate)) {
            return false;
        }
        return true;
    }

    /**
     * @return the startdate
     */
    public String getStartdate() {
        return startdate;
    }

    /**
     * @param startdate the startdate to set
     */
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
}
