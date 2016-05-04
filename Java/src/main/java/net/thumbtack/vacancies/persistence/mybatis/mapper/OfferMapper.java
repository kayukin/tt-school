package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Requirement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Konstantin on 26.02.2016.
 */
public interface OfferMapper {
    List<Offer> getOffers();

    void createOffer(@Param("employer") Employer employer, @Param("offer") Offer offer);

    void createRequirement(@Param("requirement") Requirement requirement, @Param("offer") Offer offer);
}
