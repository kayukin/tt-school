package net.thumbtack.vacancies.domain;

import java.util.List;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Offer extends Entity {
    private String name;
    private int salary;
    private List<Requirement> requirements;

    private Offer() {
    }

    public Offer(String name, int salary, List<Requirement> requirements) {
        this.name = name;
        this.salary = salary;
        this.requirements = requirements;
    }

    public Offer(int id, String name, int salary, List<Requirement> requirements) {
        super(id);
        this.name = name;
        this.salary = salary;
        this.requirements = requirements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public List<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<Requirement> requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", requirements=" + requirements +
                '}';
    }
}
