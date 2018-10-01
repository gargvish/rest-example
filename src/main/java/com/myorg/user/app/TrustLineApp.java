package com.myorg.user.app;

import javax.inject.Singleton;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.myorg.user.dao.InMemoryTrustLineDao;
import com.myorg.user.dao.InMemoryUserDao;
import com.myorg.user.dao.TrustLineDao;
import com.myorg.user.dao.UserDao;
import com.myorg.user.service.DefaultTrustlineService;
import com.myorg.user.service.TrustLineService;

/**
 * Provides the injection services for the application
 *
 * @author vg
 * @since Oct 2018
 */
public class TrustLineApp
    extends ResourceConfig {

    private static final String APP_PATH1 = "com.myorg.user.rest";
    private static final String APP_PATH2 = "com.myorg.user.service";

    public TrustLineApp() {
        packages(APP_PATH1, APP_PATH2);
        register(new MyBinder());
    }

    /**
     * Binders bind the injections
     */
    private static class MyBinder
        extends AbstractBinder {

        @Override
        protected void configure() {
            bind(InMemoryUserDao.class).to(UserDao.class);
            bind(InMemoryTrustLineDao.class).to(TrustLineDao.class).in(Singleton.class);
            bind(DefaultTrustlineService.class).to(TrustLineService.class).in(Singleton.class);
        }

    }
}
