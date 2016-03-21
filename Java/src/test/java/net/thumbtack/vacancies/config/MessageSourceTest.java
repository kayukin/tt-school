package net.thumbtack.vacancies.config;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Konstantin on 17.02.2016.
 */
public class MessageSourceTest {
    @Test
    public void testDuplicateUser() throws Exception {
        assertEquals("error: Duplicate user", MessageSource.getInstance().getMessage("duplicateuser"));
    }
}