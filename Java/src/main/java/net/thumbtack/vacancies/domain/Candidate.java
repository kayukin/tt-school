package net.thumbtack.vacancies.domain;

import java.util.List;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Candidate extends User {
    private String patronymic;
    private List<Skill> skills;

    private Candidate() {
        super(null, null, null, null, null);
    }

    public Candidate(String email, String firstName, String lastName, String login, String password, String patronymic, List<Skill> skills) {
        super(email, firstName, lastName, login, password);
        this.patronymic = patronymic;
        this.skills = skills;
    }

    public Candidate(int id, String email, String firstName, String lastName, String login, String password, String patronymic, List<Skill> skills) {
        super(id, email, firstName, lastName, login, password);
        this.patronymic = patronymic;
        this.skills = skills;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "patronymic='" + patronymic + '\'' +
                ", skills=" + skills +
                '}';
    }
}
