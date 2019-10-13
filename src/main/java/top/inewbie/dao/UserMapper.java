package top.inewbie.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.inewbie.pojo.User;

@Repository
public interface UserMapper {
    User getUser(@Param("userName") String userName);
}
