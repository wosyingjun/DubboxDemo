package com.yingjun.dubbox;

import com.alibaba.fastjson.JSONObject;
import com.yingjun.dubbox.api.UserService;
import com.yingjun.dubbox.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yingjun
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class ConsumerTest {

    private final Logger log = LoggerFactory.getLogger(ConsumerTest.class);

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        try {
            User user = userService.getUserByPhone("1233523452");
            log.info(JSONObject.toJSONString(user));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
