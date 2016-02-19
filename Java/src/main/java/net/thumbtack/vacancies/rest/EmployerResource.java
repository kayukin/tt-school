package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.persistence.dao.DuplicateEmployer;
import net.thumbtack.vacancies.persistence.dao.EmployerDao;
import net.thumbtack.vacancies.persistence.dao.EmployerMyBatisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by Konstantin on 15.02.2016.
 */

@Path("api/employer")
public class EmployerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerResource.class);
    private static final Gson gson = new Gson();
    private static volatile EmployerDao Dao = EmployerMyBatisDao.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Employer employer = gson.fromJson(body, Employer.class);
        try {
            int id = Dao.create(employer);
            employer.setId(id);
            LOGGER.info("User: {} was created with id: {}.", employer.getLogin(), id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(employer)).build();
        } catch (DuplicateEmployer e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", employer.getLogin());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(MessageSource.getInstance().getMessage("duplicateuser")).build();
        }
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Optional<Employer> employer = Dao.getById(id);
        if (employer.isPresent()) {
            return Response.ok(gson.toJson(employer.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson(MessageSource.getInstance().getMessage("employernotfound")))
                    .build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(Dao.getAll())).build();
    }
}
