package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.persistence.dao.DuplicateCompany;
import net.thumbtack.vacancies.persistence.dao.DuplicateLogin;
import net.thumbtack.vacancies.persistence.dao.EmployerDao;
import net.thumbtack.vacancies.persistence.dao.EmployerMyBatisDao;
import net.thumbtack.vacancies.rest.filter.Role;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.rest.session.Session;
import net.thumbtack.vacancies.rest.session.SessionManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
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
        employer.setPassword(DigestUtils.sha1Hex(employer.getPassword()));
        try {
            int id = Dao.create(employer);
            LOGGER.info("User: {} was created with id: {}.", employer.getLogin(), id);
            String sessionId = SessionManager.getInstance().createSession(employer, Role.EMPLOYER);
            JsonObject json = new JsonObject();
            json.addProperty("token", sessionId);
            return Response.ok(json.toString()).build();
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

    @Secured({Role.EMPLOYER})
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Optional<Employer> employer = Dao.getById(id);
        if (employer.isPresent()) {
            return Response.ok(gson.toJson(employer.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured({Role.EMPLOYER})
    @POST
    @Produces("application/json")
    @Path("/{id}/offer")
    public Response addOffer(@PathParam("id") int id, String body, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Optional<Employer> employerOptional = Dao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            Offer offer = gson.fromJson(body, Offer.class);
            Dao.addOfferToEmployer(employer, offer);

            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured({Role.EMPLOYER})
    @GET
    @Produces("application/json")
    @Path("/{id}/offer")
    public Response getOffers(@PathParam("id") int id, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Optional<Employer> employerOptional = Dao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            List<Offer> employerOffers = employer.getOffers(); //Dao.getEmployerOffers(employer);
            return Response.ok(gson.toJson(employerOffers)).build();
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

    @GET
    @Produces("application/json")
    //@Secured({Role.EMPLOYER})
    @Path("/{id}/offer/{offerId}")
    public Response getCandidates() {
        List<Candidate> list = Dao.getCandidates(new Offer("a", 10, null));
        return Response.ok(gson.toJson(list)).build();
    }
}
