package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.persistence.dao.EmployerDao;
import net.thumbtack.vacancies.persistence.dao.EmployerDaoInMemoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Konstantin on 15.02.2016.
 */

@Path("api/employer")
public class EmployerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerResource.class);
    private static final Gson gson = new Gson();
    private static volatile EmployerDao Dao = EmployerDaoInMemoryImpl.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Employer employer = gson.fromJson(body, Employer.class);
        int id = Dao.Create(employer);
        employer.setId(id);
        LOGGER.info("User: {} was created", employer.getLogin());
        return Response.status(Response.Status.CREATED).entity(gson.toJson(employer)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Employer employer = Dao.getById(id);
        if (employer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(gson.toJson(MessageSource.getInstance().getMessage("employernotfound")))
                    .build();

        } else {
            return Response.ok(gson.toJson(employer)).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(Dao.getAll())).build();
    }
}
