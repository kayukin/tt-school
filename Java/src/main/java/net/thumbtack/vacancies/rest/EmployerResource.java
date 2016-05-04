package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.CompareService;
import net.thumbtack.vacancies.CompareServiceImpl;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.persistence.dao.*;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.rest.token.JWTService;
import net.thumbtack.vacancies.rest.token.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;


@Path("api/employer")
public class EmployerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerResource.class);
    private static final Gson gson = new Gson();
    private static volatile EmployerDao employerDao = EmployerMyBatisDao.getInstance();
    private static volatile CandidateDao candidateDao = CandidateMyBatisDao.getInstance();
    private static MessageSource messageSource = MessageSource.getInstance();
    private static volatile TokenService tokenService = JWTService.getInstance();
    private static volatile CompareService compareService = CompareServiceImpl.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Employer employer = gson.fromJson(body, Employer.class);
        employer.setPassword(DigestUtils.sha1Hex(employer.getPassword()));
        try {
            int id = employerDao.create(employer);
            LOGGER.info("User: {} was created with id: {}.", employer.getLogin(), id);
            String token = tokenService.createToken(employer);
            JsonObject json = new JsonObject();
            json.addProperty("token", token);
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

    @Secured
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Employer> employerOptional = employerDao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            return Response.ok(gson.toJson(employer)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured
    @POST
    @Produces("application/json")
    @Path("/{id}/offer")
    public Response addOffer(@PathParam("id") int id, String body, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Employer> employerOptional = employerDao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            Offer offer = gson.fromJson(body, Offer.class);
            employerDao.addOfferToEmployer(employer, offer);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured
    @GET
    @Produces("application/json")
    @Path("/{id}/offer")
    public Response getOffers(@PathParam("id") int id, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Employer> employerOptional = employerDao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            List<Offer> employerOffers = employer.getOffers();
            return Response.ok(gson.toJson(employerOffers)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(employerDao.getAll())).build();
    }

    @GET
    @Produces("application/json")
    @Secured
    @Path("/{id}/offer/{offerId}")
    public Response getCandidates(@PathParam("id") int id, @PathParam("offerId") int offerId, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Employer> employerOptional = employerDao.getById(id);
        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            List<Offer> employerOffers = employer.getOffers();
            Offer offer = employerOffers.get(employerOffers.indexOf(new Offer(offerId, null, 0, null)));
            if (offer != null) {
                List<Candidate> result = compareService
                        .filterCandidates(candidateDao.getAll(), offer.getRequirements());
                return Response.ok(gson.toJson(result)).build();
            }
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }
}
