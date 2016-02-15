package net.thumbtack.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("net.thumbtack.rest.rest");
    }
}
