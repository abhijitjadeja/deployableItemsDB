package com.crazycookiecoders;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;

import com.crazycookiecoders.db.*;

import io.dropwizard.assets.AssetsBundle;



import com.crazycookiecoders.resources.*;
public class DeployableItemsDBApplication extends Application<DeployableItemsDBConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DeployableItemsDBApplication().run(args);
    }

    @Override
    public String getName() {
        return "DeployableItemsDB";
    }

    @Override
    public void initialize(final Bootstrap<DeployableItemsDBConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    @Override
    public void run(final DeployableItemsDBConfiguration configuration,
                    final Environment environment) {
        //environment.jersey().packages("com.crazycookiecoders.resources");
        DBIFactory factory = new DBIFactory();
        final DBI dbi=factory.build(environment, configuration.getDataSourceFactory(), "database");
        ReleaseDAO releaseDAO =dbi.onDemand(ReleaseDAO.class);
        ListDAO listDAO=dbi.onDemand(ListDAO.class);
        environment.jersey().register(new ReleaseResource(releaseDAO));
        environment.jersey().register(new ListResource(listDAO));
    }

}
