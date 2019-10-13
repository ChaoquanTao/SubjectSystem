package top.inewbie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.inewbie.dao.UserMapper;
import top.inewbie.pojo.ReqResult;
import top.inewbie.pojo.User;
import top.inewbie.service.UserService;
import top.inewbie.util.JWTTokenUtil;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;


/**
 * 使用Service注解表明是Service层
 */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public ReqResult login(String userName, String passWord) {
        User user = userMapper.getUser(userName);
        int statCode;
        if (user.getUserName().equals(userName) && user.getPassWord().equals(passWord)) {
            statCode = 200;
        } else
            statCode = 0;
        HashMap claims = new HashMap();

//            put("access",false) ;
        claims.put("expire", new Date(System.currentTimeMillis() + 60 * 1000));
        claims.put("userName", userName);


//        /**根据用户名密码查询数据库并返回相应结果*/
//        if(userName.equals("tao") && passWord.equals("123456")){
//            claims.put("access",true) ;
//
//        }
        String token = new JWTTokenUtil().generateToken(claims);
        return new ReqResult(statCode, token);
    }
}
