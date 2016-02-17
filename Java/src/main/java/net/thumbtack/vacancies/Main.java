package net.thumbtack.vacancies;

import net.thumbtack.vacancies.config.ConfigService;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Konstantin on 14.02.2016.
 */
public class Main {
    public static void main(String[] args) {
        URI baseUri = UriBuilder.fromUri(ConfigService.getInstance().getURI())
                .port(ConfigService.getInstance().getPort()).build();
        MyApplication config = new MyApplication();
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);
    }
}
