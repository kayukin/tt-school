package net.thumbtack.vacancies;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Konstantin on 14.02.2016.
 */
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("net.thumbtack.vacancies.rest");
    }
}
