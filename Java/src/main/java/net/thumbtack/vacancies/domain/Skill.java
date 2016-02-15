package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Skill extends Entity{
    private String name;
    private int level;

    public Skill() {
    }

    public Skill(int id, String name, int level) {
        super(id);
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Skill skill = (Skill) o;

        if (level != skill.level) return false;
        return name != null ? name.equals(skill.name) : skill.name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + level;
        return result;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
