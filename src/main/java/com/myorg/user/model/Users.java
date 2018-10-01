package com.myorg.user.model;

import java.util.List;

/**
 * Provides a list of users
 *
 * @author vg
 * @since Oct 2018
 */
public class Users<T extends User> {

    List<T> userList;

    public List<T> getUserList() {
        return this.userList;
    }

    public void setUserList(List<T> userList) {
        this.userList = userList;
    }

}
