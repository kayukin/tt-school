package net.thumbtack.vacancies.domain;

import java.util.List;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Offer extends Entity {
    private String name;
    private int Salary;
    private List<Requirement> requirements;

    public Offer() {
    }

    public Offer(String name, int salary, List<Requirement> requirements) {
        this.name = name;
        Salary = salary;
        this.requirements = requirements;
    }

    public Offer(int id, String name, int salary, List<Requirement> requirements) {
        super(id);
        this.name = name;
        Salary = salary;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Offer offer = (Offer) o;

        if (Salary != offer.Salary) return false;
        if (name != null ? !name.equals(offer.name) : offer.name != null) return false;
        return requirements != null ? requirements.equals(offer.requirements) : offer.requirements == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Salary;
        result = 31 * result + (requirements != null ? requirements.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "name='" + name + '\'' +
                ", Salary=" + Salary +
                ", requirements=" + requirements +
                '}';
    }
}
