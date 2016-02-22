package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.persistence.dao.*;
import net.thumbtack.vacancies.rest.filter.Role;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.rest.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by Konstantin on 17.02.2016.
 */
@Path("api/candidate")
@Secured({Role.CANDIDATE})
public class CandidateResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateResource.class);
    private static final Gson gson = new Gson();
    private static volatile CandidateDao Dao = CandidateMyBatisDao.getInstance();
    private static MessageSource messageSource = MessageSource.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Candidate candidate = gson.fromJson(body, Candidate.class);
        try {
            int id = Dao.create(candidate);
            LOGGER.info("User: {} was created with id: {}.", candidate.getLogin(), id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(candidate)).build();
        } catch (DuplicateLogin e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", candidate.getLogin());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageSource.getJsonErrorMessage("duplicateuser")).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Optional<Candidate> candidate = Dao.getById(id);
        if (candidate.isPresent()) {
            return Response.ok(gson.toJson(candidate.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(Dao.getAll())).build();
    }
}
