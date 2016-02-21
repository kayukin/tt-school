package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.persistence.dao.DuplicateCompany;
import net.thumbtack.vacancies.persistence.dao.DuplicateLogin;
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
    private static MessageSource messageSource = MessageSource.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Employer employer = gson.fromJson(body, Employer.class);
        try {
            int id = Dao.create(employer);
            employer.setId(id);
            LOGGER.info("User: {} was created with id: {}.", employer.getLogin(), id);
            return Response.status(Response.Status.CREATED).entity(gson.toJson(employer)).build();
        } catch (DuplicateLogin e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", employer.getLogin());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageSource.getJsonErrorMessage("duplicateuser")).build();
        } catch (DuplicateCompany e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", employer.getCompany());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageSource.getJsonErrorMessage("duplicatecompany")).build();
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
            JsonObject json = new JsonObject();
            json.addProperty("error", MessageSource.getInstance().getMessage("usernotfound"));
            return Response.status(Response.Status.NOT_FOUND).entity(json.toString()).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(Dao.getAll())).build();
    }
}
