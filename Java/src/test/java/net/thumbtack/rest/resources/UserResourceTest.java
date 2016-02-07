package net.thumbtack.rest.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.rest.MyApplication;
import net.thumbtack.rest.models.User;
import net.thumbtack.rest.persistence.InMemoryUserDao;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import static org.junit.Assert.*;

/**
 * Created by Konstantin on 01.02.2016.
 */
public class UserResourceTest extends JerseyTest {
    private Gson gson = new GsonBuilder().create();
    @Override
    protected Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        return new MyApplication();
    }

    @BeforeClass
    public static void setUpOnce() {
        UserResource.setUserDao(InMemoryUserDao.getInstance());
    }

    @Test
    public void shouldDelegateUserStorageToDatabase() throws Exception {
        User john = new User("John", 33);

        String response = target("/api/user/")
                .request()
                .post(Entity.entity(gson.toJson(john), MediaType.APPLICATION_JSON_TYPE), String.class);

        User actual = gson.fromJson(response, User.class);


        assertEquals(InMemoryUserDao.getInstance().findById(actual.getId()), actual);

    }

    @Test
    public void shouldDeleteUser() throws Exception {
        int userId = InMemoryUserDao.getInstance().create(new User("John", 33));

        assertNotNull(InMemoryUserDao.getInstance().findById(userId));

        String response = target("/api/user/" + userId + "/")
                .request()
                .delete(String.class);


        assertNull(InMemoryUserDao.getInstance().findById(userId));

    }
}