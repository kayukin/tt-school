package net.thumbtack.vacancies.domain;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Employer extends User {
    private String company;
    private String address;

    public Employer() {
    }

    public Employer(String email, String firstName, String lastName, String login, String password, String company, String address) {
        super(email, firstName, lastName, login, password);
        this.company = company;
        this.address = address;
    }

    public Employer(int id, String email, String firstName, String lastName, String login, String password, String company, String address) {
        super(id, email, firstName, lastName, login, password);
        this.company = company;
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Employer employer = (Employer) o;

        if (company != null ? !company.equals(employer.company) : employer.company != null) return false;
        return address != null ? address.equals(employer.address) : employer.address == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "company='" + company + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
