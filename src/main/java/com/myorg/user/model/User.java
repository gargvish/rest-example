package com.myorg.user.model;

/**
 * Models the Trustline User
 *
 * @author vg
 * @since Oct 2018
 */
public class User {

    private int userId;

    private String userName;

    private String endPoint;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "User [userId=" + this.userId + ", userName=" + this.userName + ", endpoint= " + this.endPoint + "]";
    }
}
