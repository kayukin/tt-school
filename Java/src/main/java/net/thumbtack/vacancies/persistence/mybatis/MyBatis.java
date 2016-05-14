package net.thumbtack.vacancies.persistence.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatis {
    private static volatile SqlSessionFactory INSTANCE;
    private static final String resource = "mybatis.xml";

    private MyBatis() {
    }

    public static SqlSessionFactory SessionFactory() {
        if (INSTANCE == null)
            init();
        return INSTANCE;
    }

    private static synchronized void init() {
        if (INSTANCE == null) {
            try (InputStream inputStream = Resources.getResourceAsStream(resource)) {
                INSTANCE = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
