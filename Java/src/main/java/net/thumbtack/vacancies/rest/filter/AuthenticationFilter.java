package net.thumbtack.vacancies.rest.filter;

import net.thumbtack.vacancies.rest.session.Session;
import net.thumbtack.vacancies.rest.session.SessionManager;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by Konstantin on 22.02.2016.
 */
@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String token = containerRequestContext.getHeaderString("token");
        if (token == null) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        Optional<Session> sessionOptional = SessionManager.getInstance().getSession(token);
        sessionOptional.ifPresent(session -> containerRequestContext.setProperty("session", session));
        if (!sessionOptional.isPresent()) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
