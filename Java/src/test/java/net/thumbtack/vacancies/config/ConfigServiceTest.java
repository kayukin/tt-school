package net.thumbtack.vacancies.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class ConfigServiceTest {
    @Test
    public void testGetMaxJobsNumber() throws Exception {
        assertEquals(10, ConfigService.getInstance().getMaxJobsNumber());
    }
}