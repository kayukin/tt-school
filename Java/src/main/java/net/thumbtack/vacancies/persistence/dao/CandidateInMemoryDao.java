package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Candidate;
import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.dao.exceptions.DuplicateLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateInMemoryDao implements CandidateDao {
    private static final CandidateDao INSTANCE = new CandidateInMemoryDao();

    public static CandidateDao getInstance() {
        return INSTANCE;
    }

    private static final Map<Integer, Candidate> database = new ConcurrentHashMap<>();

    private static final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public int create(Candidate candidate) throws DuplicateLogin {
        int id = nextId.incrementAndGet();
        database.put(id, candidate);
        return id;
    }

    @Override
    public Optional<Candidate> getById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Candidate> getAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public void addSkillToCandidate(Candidate candidate, Skill skill) {
        database.get(candidate.getId()).getSkills().add(skill);
    }

    @Override
    public void changeLevel(Candidate candidate, Skill skill) {
        List<Skill> skills = database.get(candidate.getId()).getSkills();
        skills.get(skills.indexOf(skill)).setLevel(skill.getLevel());
    }
}
