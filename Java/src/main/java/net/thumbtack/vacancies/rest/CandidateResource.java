package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.dao.*;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateLogin;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.services.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("api/candidate")
public class CandidateResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateResource.class);
    private static final Gson gson = new Gson();
    private static volatile ServiceLocator locator = ServiceLocator.getInstance();

    private static volatile CandidateDao candidateDao = locator.getCandidateDao();
    private static volatile EmployerDao employerDao = locator.getEmployerDao();
    private static volatile OtherDao otherDao = locator.getOtherDao();
    private static MessageSource messageSource = locator.getMessageSource();
    private static volatile TokenService tokenService = locator.getTokenService();
    private static volatile CompareService compareService = locator.getCompareService();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Candidate candidate = gson.fromJson(body, Candidate.class);
        candidate.setPassword(DigestUtils.sha1Hex(candidate.getPassword()));
        try {
            int id = candidateDao.create(candidate);
            LOGGER.info("User: {} was created with id: {}.", candidate.getLogin(), id);
            String token = tokenService.createToken(candidate);
            JsonObject json = new JsonObject();
            json.addProperty("token", token);
            return Response.ok(json.toString()).build();
        } catch (DuplicateLogin e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", candidate.getLogin());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageSource.getJsonErrorMessage("duplicateuser")).build();
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
        Optional<Candidate> candidateOptional = candidateDao.getById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            return Response.ok(gson.toJson(candidate)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured
    @POST
    @Produces("application/json")
    @Path("/{id}/skill")
    public Response addSkill(@PathParam("id") int id, String body, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Candidate> candidateOptional = candidateDao.getById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            Skill skill = gson.fromJson(body, Skill.class);
            if (id != 0) {
                candidateDao.changeLevel(candidate, skill);
            } else {
                candidateDao.addSkillToCandidate(candidate, skill);
            }
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response getAll() {
        return Response.ok(gson.toJson(candidateDao.getAll())).build();
    }

    @Secured
    @GET
    @Produces("application/json")
    @Path("/{id}/skill")
    public Response getSkills(@PathParam("id") int id, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Candidate> candidateOptional = candidateDao.getById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            List<Skill> candidateSkills = candidate.getSkills();
            return Response.ok(gson.toJson(candidateSkills)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured
    @GET
    @Produces("application/json")
    @Path("/{id}/offers")
    public Response getOffers(@PathParam("id") int id, @Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Candidate> candidateOptional = candidateDao.getById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            List<Offer> allOffers = otherDao.getOffers();
            List<Offer> offers = compareService.filterOffers(allOffers, candidate.getSkills());
            return Response.ok(gson.toJson(offers)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured
    @DELETE
    @Path("/{id}/skill/{id_skill}")
    public Response removeSkill(@PathParam("id") int id, @Context ContainerRequestContext context,
                                @PathParam("id_skill") int id_skill) {
        String token = context.getHeaderString("token");
        int tokenId = tokenService.getUserId(token);
        if (tokenId != id)
            return Response.status(Response.Status.FORBIDDEN).build();
        Optional<Candidate> candidateOptional = candidateDao.getById(id);
        if (candidateOptional.isPresent()) {
            Candidate candidate = candidateOptional.get();
            //TODO remove skill
            otherDao.deleteSkill(id_skill, candidate);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }
}
