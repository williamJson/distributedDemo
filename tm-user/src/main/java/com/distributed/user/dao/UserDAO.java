package com.distributed.user.dao;

import com.distributed.api.po.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDAO {

    @Select("select * from user")
    List<User> getUserList();
}
