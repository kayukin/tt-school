package net.thumbtack.vacancies.rest.session;

import net.thumbtack.vacancies.domain.User;
import net.thumbtack.vacancies.rest.filter.Role;

/**
 * Created by Konstantin on 22.02.2016.
 */
public class Session {
    private User user;
    private String sessionId;
    private Role role;
    private long creationDate;

    public long getCreationDate() {
        return creationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Session(User user, String sessionId) {
        this.user = user;
        this.sessionId = sessionId;
        creationDate = System.currentTimeMillis();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (user != null ? !user.equals(session.user) : session.user != null) return false;
        return sessionId != null ? sessionId.equals(session.sessionId) : session.sessionId == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (sessionId != null ? sessionId.hashCode() : 0);
        return result;
    }
}
