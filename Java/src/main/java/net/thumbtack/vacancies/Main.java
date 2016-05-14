package net.thumbtack.vacancies;

import net.thumbtack.vacancies.services.ConfigService;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import javax.ws.rs.core.UriBuilder;
import java.io.FileNotFoundException;
import java.net.URI;

/**
 * Created by Konstantin on 14.02.2016.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        URI baseUri = UriBuilder.fromUri(ConfigService.getInstance().getURI())
                .port(ConfigService.getInstance().getPort()).build();
        MyApplication config = new MyApplication();
        /*SslContextFactory sslContextFactory = new SslContextFactory();
        String jettyDistKeystore = "../../jetty-distribution/target/distribution/demo-base/etc/keystore";
        String keystorePath = System.getProperty(
                "example.keystore", jettyDistKeystore);
        File keystoreFile = new File(keystorePath);
        if (!keystoreFile.exists()) {
            throw new FileNotFoundException(keystoreFile.getAbsolutePath());
        }
        sslContextFactory.setKeyStorePath(keystoreFile.getAbsolutePath());
        sslContextFactory.setKeyStorePassword("OBF:1vny1zlo1x8e1vnw1vn61x8g1zlu1vn4");
        sslContextFactory.setKeyManagerPassword("OBF:1u2u1wml1z7s1z7a1wnl1u2g");*/
        Server server = JettyHttpContainerFactory.createServer(baseUri, config);


    }
}
