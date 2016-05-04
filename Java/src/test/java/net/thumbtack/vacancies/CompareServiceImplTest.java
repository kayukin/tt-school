package net.thumbtack.vacancies;

import net.thumbtack.vacancies.domain.Requirement;
import net.thumbtack.vacancies.domain.Skill;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Konstantin on 22.04.2016.
 */
public class CompareServiceImplTest {

    @Test
    public void isAccept() throws Exception {
        List<Requirement> requirements = new ArrayList<>();
        requirements.add(new Requirement(0, "Java", 5, true));
        requirements.add(new Requirement(1, "C#", 4, true));
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill(0, "Java", 5));
        skills.add(new Skill(1, "C#", 4));
        assertTrue(CompareServiceImpl.getInstance().isAccept(skills, requirements));
        skills.get(0).setLevel(1);
        assertFalse(CompareServiceImpl.getInstance().isAccept(skills, requirements));
    }
}