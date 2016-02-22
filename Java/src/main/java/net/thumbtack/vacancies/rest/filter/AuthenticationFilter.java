package net.thumbtack.vacancies.rest.filter;

import net.thumbtack.vacancies.rest.session.SessionManager;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

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
        if (token == null || !isValidToken(token)) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        containerRequestContext.setProperty("session", SessionManager.getInstance().getSession(token).get());
    }

    private boolean isValidToken(String token) {
        return SessionManager.getInstance().getSession(token).isPresent();
    }
}
