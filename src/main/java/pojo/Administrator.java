package pojo;

import org.springframework.stereotype.Repository;

/**
 * 参数名要和前端传来的json里面的字段保持一致
 */
//使用这个注解接口将会被容器扫描和解析成对象

public class Administrator {
    private String userName ;
    private String passWord;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
