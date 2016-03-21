package net.thumbtack.rest;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(9998).build();
        MyApplication config = new MyApplication();
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);
    }
}
