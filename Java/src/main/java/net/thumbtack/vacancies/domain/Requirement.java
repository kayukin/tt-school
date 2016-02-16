package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Requirement extends Skill {
    private boolean isNecessary;

    private Requirement() {
        super(0, null);
    }

    public Requirement(int id, String name, int level, boolean isNecessary) {
        super(id, name, level);
        this.isNecessary = isNecessary;
    }

    public boolean isNecessary() {
        return isNecessary;
    }

    public void setNecessary(boolean necessary) {
        isNecessary = necessary;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "isNecessary=" + isNecessary +
                '}';
    }
}
