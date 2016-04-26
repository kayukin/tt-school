package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Skill extends Entity {
    private String name;
    private int level;

    private Skill() {
    }

    public Skill(int level, String name) {
        this.level = level;
        this.name = name;
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
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
