package net.thumbtack.rest.resources;

import com.google.gson.Gson;
import net.thumbtack.rest.models.User;
import net.thumbtack.rest.persistence.JdbcUserDao;
import net.thumbtack.rest.persistence.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/api/user")
public class UserResource {
    //thread-safe thing
    private static final Gson gson = new Gson();

    private static volatile UserDao dao = new JdbcUserDao();//InMemoryUserDao.getSessionFactory();


    @POST
    @Produces("application/json")
    public String create(String body) {
        User user = gson.fromJson(body, User.class);
        int id = dao.create(user);
        user.setId(id);
        return gson.toJson(user);
    }

    @PUT
    @Produces("application/json")
    @Path("/{id}")
    public Response Modify(@PathParam("id") int id, String body) {

        User user = dao.findById(id);
        if (user == null) {
            return Response.notModified().build();
        }
        User newUser = gson.fromJson(body, User.class);
        newUser.setId(id);
        dao.modify(newUser);
        return Response.ok().build();
    }

    @GET
    @Produces("application/json")
    @Path("/{id}")
    public Response get(@PathParam("id") int id) {
        User user = dao.findById(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(gson.toJson(user)).build();
        }
    }

    @GET
    @Produces("application/json")
    public Response get() {
        return Response.ok(gson.toJson(dao.getAllUsers())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") int id) {
        dao.delete(id);
        return Response.ok().build();
    }

    @DELETE
    @Path("/deleteall")
    public Response deleteAll() {
        dao.deleteAll();
        return Response.ok().build();
    }

    /**
     * Sorry, guys, I needed that for unit testing, please do not use in production code
     */
    @Deprecated
    public static void setUserDao(UserDao dao) {
        UserResource.dao = dao;
    }
}
