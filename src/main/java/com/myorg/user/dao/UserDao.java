package com.myorg.user.dao;

import com.myorg.user.model.User;

import java.util.List;

/**
 * Provides data access for User operations
 *
 * @author vg
 * @since Oct 2018
 */
public interface UserDao {

    List<User> getAll();

    User getById(int id);

    int add(User hello);

    boolean update(User hello);

    boolean delete(int id);
}
