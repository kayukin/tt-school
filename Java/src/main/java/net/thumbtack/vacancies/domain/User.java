package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class User extends Entity {
    private String email;
    private String firstName;
    private String lastName;
    private String login;

    private User() {
    }

    public User(String email, String firstName, String lastName, String login, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public User(int id, String email, String firstName, String lastName, String login, String password) {
        super(id);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
