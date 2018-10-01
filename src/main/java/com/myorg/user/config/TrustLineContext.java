package com.myorg.user.config;

import com.myorg.user.model.User;

/**
 * Registers the application for the user
 * <pre>
 *     maps the user to this server node, for example, Alice is localhost:8080
 * </pre>
 * 
 * @author vg
 * @since Oct 2018
 */
public class TrustLineContext {

    private User user;

    private static TrustLineContext SINGLETON;

    TrustLineContext(final User user) {
        this.user = user;
    }

    public static synchronized final TrustLineContext initialize(final User user) {
        TrustLineContext config = SINGLETON;
        if (config == null) {
            config = SINGLETON;
            synchronized (TrustLineContext.class) {
                if (config == null) {
                    config = SINGLETON = new TrustLineContext(user);
                }
            }
        }
        return config;
    }

    public static synchronized final TrustLineContext get() {
        return SINGLETON;
    }

    public User getUser() {
        return this.user;
    }
}
