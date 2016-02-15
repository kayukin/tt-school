package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Requirement extends Skill {
    private boolean isNecessary;

    public Requirement() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Requirement that = (Requirement) o;

        return isNecessary == that.isNecessary;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (isNecessary ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Requirement{" +
                "isNecessary=" + isNecessary +
                '}';
    }
}
