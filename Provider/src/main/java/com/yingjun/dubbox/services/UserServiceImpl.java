package com.yingjun.dubbox.services;

import com.yingjun.dubbox.api.UserService;
import com.yingjun.dubbox.entity.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author yingjun
 * http://10.26.210.90:8080/user/getUserByPhone/18888888888
 */

@Service("userService")
@Path("user")
@Produces({"application/json; charset=UTF-8", "text/xml; charset=UTF-8"})
public class UserServiceImpl implements UserService {

    @GET
    @Path("/getUserByPhone/{phone}/")
    public User getUserByPhone(@PathParam("phone") String phone) {
        try {
            //测试超时
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User(11L, "ByPhone", phone);
        return user;
    }

    @GET
    @Path("/getUserByName/{name}/")
    public User getUserByName(@PathParam("name") String name) {
        User user = new User(11L, name, "18888888888");
        return user;
    }
}
