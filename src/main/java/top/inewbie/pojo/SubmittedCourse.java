package top.inewbie.pojo;

import java.util.Set;

public class SubmittedCourse {
    private String userName ;
    private Set added;
    private Set deleted ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set getAdded() {
        return added;
    }

    public void setAdded(Set added) {
        this.added = added;
    }

    public Set getDeleted() {
        return deleted;
    }

    public void setDeleted(Set deleted) {
        this.deleted = deleted;
    }
}
