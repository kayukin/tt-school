package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.persistence.dao.CandidateDao;
import net.thumbtack.vacancies.persistence.dao.EmployerDao;
import net.thumbtack.vacancies.persistence.dao.OtherDao;
import net.thumbtack.vacancies.rest.filter.Secured;
import net.thumbtack.vacancies.services.MessageSource;
import net.thumbtack.vacancies.services.ServiceLocator;
import net.thumbtack.vacancies.services.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("api/login")
public class LoginResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);
    private static final Gson gson = new Gson();
    private static volatile ServiceLocator locator = ServiceLocator.getInstance();

    private static MessageSource messageSource = locator.getMessageSource();
    private static volatile CandidateDao candidateDao = locator.getCandidateDao();
    private static volatile EmployerDao employerDao = locator.getEmployerDao();
    private static volatile OtherDao otherDao = locator.getOtherDao();
    private static volatile TokenService tokenService = locator.getTokenService();

    @GET
    @Secured
    @Produces("application/json")
    public Response getUser(@Context ContainerRequestContext context) {
        String token = context.getHeaderString("token");
        int userId = tokenService.getUserId(token);
        JsonObject jsonObject = new JsonObject();
        Optional<Candidate> candidate = candidateDao.getById(userId);
        if (candidate.isPresent()) {
            jsonObject.add("candidate", gson.toJsonTree(candidate.get()));
        } else {
            Optional<Employer> employer = employerDao.getById(userId);
            if (employer.isPresent()) {
                jsonObject.add("employer", gson.toJsonTree(employer.get()));
            }
        }
        return Response.ok(jsonObject.toString()).build();
    }

    @POST
    @Produces("application/json")
    public Response login(String body) {
        User credentials = gson.fromJson(body, User.class);
        String passwordHash = DigestUtils.sha1Hex(credentials.getPassword());
        LOGGER.debug("Hash: {}", passwordHash);
        Optional<User> userOptional = otherDao.getByLogin(credentials.getLogin());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!passwordHash.equals(user.getPassword())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(messageSource.toJson(MessageSource.ERROR, MessageSource.INCORRECTPASSWORD)).build();
            }
            Optional<Employer> employerOptional = employerDao.getById(user.getId());
            Optional<Candidate> candidateOptional = candidateDao.getById(user.getId());
            if (employerOptional.isPresent()) {
                Employer employer = employerOptional.get();
                String token = tokenService.createToken(employer);
                return Response.ok(messageSource.toJson(MessageSource.TOKEN, token)).build();
            } else if (candidateOptional.isPresent()) {
                Candidate candidate = candidateOptional.get();
                String token = tokenService.createToken(candidate);
                return Response.ok(messageSource.toJson(MessageSource.TOKEN, token)).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(messageSource.toJson(MessageSource.ERROR, MessageSource.USERNOTFOUND)).build();
    }

    @DELETE
    @Produces("application/json")
    public Response logout(@HeaderParam("token") String token) {
        return Response.ok().build();
    }
}
