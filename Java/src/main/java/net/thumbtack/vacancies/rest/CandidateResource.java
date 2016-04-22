package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.dao.CandidateDao;
import net.thumbtack.vacancies.persistence.dao.DuplicateLogin;
import net.thumbtack.vacancies.rest.filter.Role;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.rest.session.Session;
import net.thumbtack.vacancies.rest.session.SessionManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@Path("api/candidate")
public class CandidateResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CandidateResource.class);
    private static final Gson gson = new Gson();
    @Autowired
    private static volatile CandidateDao candidateDao;
    private static MessageSource messageSource = MessageSource.getInstance();

    @POST
    @Produces("application/json")
    public Response create(String body) {
        Candidate candidate = gson.fromJson(body, Candidate.class);
        candidate.setPassword(DigestUtils.sha1Hex(candidate.getPassword()));
        try {
            int id = candidateDao.create(candidate);
            LOGGER.info("User: {} was created with id: {}.", candidate.getLogin(), id);
            String sessionId = SessionManager.getInstance().createSession(candidate, Role.CANDIDATE);
            JsonObject json = new JsonObject();
            json.addProperty("token", sessionId);
            return Response.ok(json.toString()).build();
        } catch (DuplicateLogin e) {
            LOGGER.info("Attempt to create a duplicate user: {}.", candidate.getLogin());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(messageSource.getJsonErrorMessage("duplicateuser")).build();
        }
    }

    @Secured({Role.CANDIDATE})
    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response getById(@PathParam("id") int id, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        Optional<Candidate> candidate = candidateDao.getById(id);
        if (candidate.isPresent()) {
            return Response.ok(gson.toJson(candidate.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
        }
    }

    @Secured({Role.CANDIDATE})
    @POST
    @Produces("application/json")
    @Path("/{id}/skill")
    public Response addSkill(@PathParam("id") int id, String body, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
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

    @Secured({Role.CANDIDATE})
    @GET
    @Produces("application/json")
    @Path("/{id}/skill")
    public Response getSkills(@PathParam("id") int id, @Context ContainerRequestContext context) {
        Session session = (Session) context.getProperty("session");
        if (session.getUser().getId() != id) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
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
}
