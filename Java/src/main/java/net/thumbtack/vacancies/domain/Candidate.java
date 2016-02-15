package net.thumbtack.vacancies.domain;

import java.util.List;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Candidate extends User {
    private String patronymic;
    private List<Skill> skills;

    public Candidate() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Candidate candidate = (Candidate) o;

        if (patronymic != null ? !patronymic.equals(candidate.patronymic) : candidate.patronymic != null) return false;
        return skills != null ? skills.equals(candidate.skills) : candidate.skills == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (skills != null ? skills.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "patronymic='" + patronymic + '\'' +
                ", skills=" + skills +
                '}';
    }
}
