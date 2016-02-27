package net.thumbtack.vacancies.persistence.mybatis.mapper;

import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;
import net.thumbtack.vacancies.domain.Requirement;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Konstantin on 26.02.2016.
 */
public interface OfferMapper {
    void createOffer(@Param("employer") Employer employer, @Param("offer") Offer offer);

    void createRequirement(@Param("requirement") Requirement requirement, @Param("offer") Offer offer);
}
