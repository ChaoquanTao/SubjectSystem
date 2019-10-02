package controller;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;
import pojo.Administrator;
import pojo.ReqResult;
import util.JWTTokenUtil;

import java.util.Date;
import java.util.HashMap;


@Controller

public class AdminController {
    /**
     * 正常来讲，我们这里返回的应该是一个token,该token应该是service层查询数据库后生成的，
     * 包含了用户是否应该登录，以及过期时间
     * 这里先忽略持久层，直接在Controller这里生成一个token
     * 以后客户端要发请求，都带着这个token一起发，我们根据token里的内容给它指示
     * @param administrator
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody ReqResult login(@RequestBody  Administrator  administrator){
        final String userName = administrator.getUserName();
        String passWord = administrator.getPassWord();

        System.out.println(userName+"================"+passWord);

        /**
         * 查完数据库，根据查询结果生成token并返回
         */
        HashMap claims = new HashMap(){{
            put("access",false) ;
            put("expire",new Date(System.currentTimeMillis() + 60 * 1000));
            put("userName",userName) ;
        }};


        /**根据用户名密码查询数据库并返回相应结果*/
        if(userName.equals("tao") && passWord.equals("123456")){
            claims.put("access",true) ;

        }
        String token = new JWTTokenUtil().generateToken(claims) ;
        return new ReqResult(200,token);

    }
//    public @ResponseBody String login(String userName,
//                                             String passWord){
//
//        System.out.println(userName+"================"+passWord);
//        Administrator adm = new Administrator();
//        adm.setUserName("dddd");
//        adm.setPassWord("sfdadfa");
//        /**根据用户名密码查询数据库并返回相应结果*/
//
//        return "ddddddddddddddd" ;
//
//    }




}
