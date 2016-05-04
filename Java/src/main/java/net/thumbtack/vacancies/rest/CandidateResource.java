package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.CompareService;
import net.thumbtack.vacancies.CompareServiceImpl;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Skill;
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

@Path("api/candidate")
public class CandidateResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateResource.class);
    private static final Gson gson = new Gson();
    private static volatile CandidateDao candidateDao = CandidateMyBatisDao.getInstance();
    private static volatile EmployerDao employerDao = EmployerMyBatisDao.getInstance();
    private static volatile SharedDao sharedDao = SharedMyBatisDao.getInstance();

    private static MessageSource messageSource = MessageSource.getInstance();
    private static volatile TokenService tokenService = JWTService.getInstance();
    private static volatile CompareService compareService = CompareServiceImpl.getInstance();

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
            candidateDao.addSkillToCandidate(candidate, skill);
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
            List<Offer> allOffers = sharedDao.getOffers();
            List<Offer> offers = compareService.filterOffers(allOffers, candidate.getSkills());
            return Response.ok(gson.toJson(offers)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }
}
