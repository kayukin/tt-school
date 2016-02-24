package net.thumbtack.vacancies.rest.session;

import net.thumbtack.vacancies.config.ConfigService;
import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.rest.filter.Role;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Konstantin on 22.02.2016.
 */
public class SessionManager {
    private static final SessionManager INSTANCE = new SessionManager();
    private ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();
    Random random = new SecureRandom();


    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public String createSession(User user, Role role) {
        String sessionId = new BigInteger(130, random).toString(32);
        Session session = new Session(user, sessionId);
        session.setRole(role);
        sessions.put(sessionId, session);
        return sessionId;
    }

    public Optional<Session> getSession(String token) {
        Session session = sessions.get(token);
        if (session != null) {
            if (Math.abs(session.getCreationDate() - System.currentTimeMillis()) <
                    ConfigService.getInstance().getSessionTime()) {
                return Optional.of(session);
            } else {
                sessions.remove(token);
            }
        }
        return Optional.empty();
    }

    public void removeSession(String token) {
        sessions.remove(token);
    }

}
