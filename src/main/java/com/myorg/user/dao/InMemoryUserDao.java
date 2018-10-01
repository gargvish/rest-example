package com.myorg.user.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.myorg.user.model.User;

/**
 * In Memory data access for user operations
 *
 * @author vg
 * @since Oct 2018
 */
public class InMemoryUserDao
    implements UserDao {

    private static final Map<Integer, User> USERS = new LinkedHashMap();

    private static final SequenceIdProvider ID_PROVIDER = new SequenceIdProvider();

    @Override
    public List<User> getAll() {
        return new ArrayList<>(USERS.values());
    }

    @Override
    public User getById(int id) {
        return USERS.get(id);
    }

    @Override
    public int add(User user) {
        final int userId = ID_PROVIDER.nextId();
        final User newUser = new User();
        newUser.setUserId(userId);
        newUser.setUserName(user.getUserName());
        USERS.put(userId, newUser);
        return userId;
    }

    @Override
    public boolean update(User user) {
        final User user1 = getById(user.getUserId());
        if (user1 != null) {
            user1.setUserName(user.getUserName());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        final User user = getById(id);
        if (user != null) {
            USERS.remove(user);
            return true;
        }
        return false;
    }

    /**
     * 
     */
    interface IdProvider {
        int nextId();
    }

    /**
     *
     */
    private static final class SequenceIdProvider
        implements IdProvider {
        private final AtomicInteger sequence = new AtomicInteger();

        @Override
        public int nextId() {
            return sequence.incrementAndGet();
        }
    }

}
