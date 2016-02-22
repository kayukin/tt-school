package net.thumbtack.vacancies.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.thumbtack.vacancies.config.MessageSource;
import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.persistence.dao.CandidateMyBatisDao;
import net.thumbtack.vacancies.persistence.dao.EmployerMyBatisDao;
import net.thumbtack.vacancies.persistence.dao.UserMyBatisDao;
import net.thumbtack.vacancies.rest.filter.Role;
import net.thumbtack.vacancies.rest.session.SessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Optional;


@Path("api/login")
public class LoginResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);
    private static final Gson gson = new Gson();
    private static MessageSource messageSource = MessageSource.getInstance();

    @POST
    @Produces("application/json")
    public Response login(String body) {
        User credentials = gson.fromJson(body, User.class);
        String password = credentials.getPassword();
        Optional<User> userOptional = UserMyBatisDao.getInstance().getByLogin(credentials.getLogin());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!password.equals(user.getPassword())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(messageSource.getJsonErrorMessage("incorrectpassword")).build();
            }
            Optional<Employer> employerOptional = EmployerMyBatisDao.getInstance().getById(user.getId());
            Optional<Candidate> candidateOptional = CandidateMyBatisDao.getInstance().getById(user.getId());
            if (employerOptional.isPresent()) {
                Employer employer = employerOptional.get();
                String sessionId = SessionManager.getInstance().createSession(employer, Role.EMPLOYER);
                JsonObject json = new JsonObject();
                json.addProperty("token", sessionId);
                return Response.ok(json.toString()).build();
            } else if (candidateOptional.isPresent()) {
                Candidate candidate = candidateOptional.get();
                String sessionId = SessionManager.getInstance().createSession(candidate, Role.CANDIDATE);
                JsonObject json = new JsonObject();
                json.addProperty("token", sessionId);
                return Response.ok(json.toString()).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(messageSource.getJsonErrorMessage("usernotfound")).build();
    }
}
