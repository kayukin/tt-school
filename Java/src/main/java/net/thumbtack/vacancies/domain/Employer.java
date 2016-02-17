package net.thumbtack.vacancies.domain;

import java.util.List;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class Employer extends User {
    private String company;
    private String address;
    List<Offer> offers;

    private Employer() {
        super(null, null, null, null, null);
    }

    public Employer(String email, String firstName, String lastName, String login,
                    String password, String company, String address, List<Offer> offers) {
        super(email, firstName, lastName, login, password);
        this.company = company;
        this.address = address;
        this.offers = offers;
    }

    public Employer(int id, String email, String firstName, String lastName, String login,

                    String password, String company, String address, List<Offer> offers) {
        super(id, email, firstName, lastName, login, password);
        this.company = company;
        this.address = address;
        this.offers = offers;
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

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }


    
    @Override
    public String toString() {
        return "Employer{" +
                "company='" + company + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
