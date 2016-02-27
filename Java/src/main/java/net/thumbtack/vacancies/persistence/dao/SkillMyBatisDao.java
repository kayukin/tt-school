package net.thumbtack.vacancies.persistence.dao;

import net.thumbtack.vacancies.domain.Skill;
import net.thumbtack.vacancies.persistence.mybatis.MyBatis;
import net.thumbtack.vacancies.persistence.mybatis.mapper.SkillMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by Konstantin on 27.02.2016.
 */
public class SkillMyBatisDao implements SkillDao {
    private static final SkillMyBatisDao INSTANCE = new SkillMyBatisDao();

    private SkillMyBatisDao() {
    }

    public static SkillMyBatisDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Skill getSkill(String name) {
        try (SqlSession session = MyBatis.getInstance().openSession()) {
            SkillMapper skillMapper = session.getMapper(SkillMapper.class);
            Skill skill = skillMapper.findByName(name);
            if (skill == null) {
                skillMapper.createSkill(new Skill(0, name));
            }
            return skill;
        }
    }
}
