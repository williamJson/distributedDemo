package com.distributed.user;


import com.distributed.api.po.User;
import com.distributed.user.dao.UserDAO;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 启动类
 *
 * @author wyq
 */
@SpringBootApplication
@RestController
@MapperScan("com.distributed.user.dao")
@EnableTransactionManagement(proxyTargetClass = true)
public class UserApplication {


    private static final Logger logger = LoggerFactory.getLogger(UserApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/users")
    public List<User> getUsers() {
        logger.debug("this is debugger...");
        logger.info("this is new request...");
        return userDAO.getUserList();
    }
}
