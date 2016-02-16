package net.thumbtack.vacancies.config;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class ConfigService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigService.class);
    private Properties properties = new Properties();
    private final String filename = "application.properties";
    private static final ConfigService INSTANCE = new ConfigService();

    private ConfigService() {
        try (InputStream inputStream = Resources.getResourceAsStream(filename)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Can't load property file: ", e);
            throw new RuntimeException(e);
        }
    }

    public static ConfigService getInstance() {
        return INSTANCE;
    }

    public int getMaxJobsNumber() {
        return Integer.parseInt(properties.getProperty("max_jobs"));
    }

    public int getMaxEmployerSkillsNumber() {
        return Integer.parseInt(properties.getProperty("max_employer_skills"));
    }

    public int getMaxEmployeeSkillsNumber() {
        return Integer.parseInt(properties.getProperty("max_employee_skills"));
    }

    public int getJobExpirationDays() {
        return Integer.parseInt(properties.getProperty("job_expiration"));
    }

    public int getEmployeeExpirationDays() {
        return Integer.parseInt(properties.getProperty("employee_expiration"));
    }
}
