package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Employer;
import net.thumbtack.vacancies.domain.Offer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class EmployerInMemoryDao implements EmployerDao {
    private final static EmployerDao INSTANCE = new EmployerInMemoryDao();

    public static EmployerDao getInstance() {
        return INSTANCE;
    }

    private static final Map<Integer, Employer> database = new ConcurrentHashMap<>();

    private static final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public int create(Employer employer) {
        int id = nextId.incrementAndGet();
        employer.setId(id);
        database.put(id, employer);
        return id;
    }

    @Override
    public Optional<Employer> getById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Employer> getAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void addOfferToEmployer(Employer employer, Offer offer) {
        database.get(employer.getId()).getOffers().add(offer);
    }

    @Override
    public List<Candidate> getCandidates(Offer offer) {
        List<Candidate> allCandidates = CandidateInMemoryDao.getInstance().getAll();
        allCandidates.stream()
                .filter(candidate -> !candidate.getSkills().containsAll(offer.getRequirements()))
                .forEach(allCandidates::remove);
        return allCandidates;
    }


}
