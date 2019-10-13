package top.inewbie.service;


import org.springframework.stereotype.Service;
import top.inewbie.pojo.ReqResult;

public interface UserService {
    /**
     * 登录方法，返回token
     */
    ReqResult login(String userName, String passWord) ;
}
