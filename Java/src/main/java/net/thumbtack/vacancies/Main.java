package net.thumbtack.vacancies;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * Created by Konstantin on 14.02.2016.
 */
public class Main {
    URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
    MyApplication config = new MyApplication();
    Server server = JettyHttpContainerFactory.createServer(baseUri, config);
}
