package net.thumbtack.vacancies.services;

import net.thumbtack.vacancies.persistence.dao.*;

public class ServiceLocator {
    private static final ServiceLocator INSTANCE = new ServiceLocator();
    private boolean inMemory;

    public static ServiceLocator getInstance() {
        return INSTANCE;
    }

    private ServiceLocator() {
        inMemory = ConfigService.getInstance().isInMemory();
    }

    public CandidateDao getCandidateDao() {
        if (inMemory) {
            return CandidateInMemoryDao.getInstance();
        }
        return CandidateMyBatisDao.getInstance();
    }

    public EmployerDao getEmployerDao() {
        if (inMemory) {
            return EmployerInMemoryDao.getInstance();
        }
        return EmployerMyBatisDao.getInstance();
    }

    public OtherDao getOtherDao() {
        if (inMemory) {
            OtherInMemoryDao.getInstance();
        }
        return OtherMyBatisDao.getInstance();
    }

    public TokenService getTokenService() {
        return JWTService.getInstance();
    }

    public CompareService getCompareService() {
        return CompareServiceImpl.getInstance();
    }

    public MessageSource getMessageSource() {
        return MessageSourceImpl.getInstance();
    }
}
