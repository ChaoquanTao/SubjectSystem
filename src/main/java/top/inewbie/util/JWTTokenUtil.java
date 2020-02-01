package top.inewbie.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import top.inewbie.pojo.Global;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.Key;

//import java.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 对于认证：查询完数据库后生成token
 */

public class JWTTokenUtil {
//    private Key key = generalKey();
//@Value("${jwt.secret}")
//private String key="test";
    public String generateToken(Map claimsMap){
        SecretKey key = generalKey();
//        System.out.println("生成key"+key+" "+ Arrays.toString(key.getEncoded()));
//        System.out.println("再生成："+key+" "+Arrays.toString(key.getEncoded()));
        String jws = Jwts.builder().
                setClaims(claimsMap).
                signWith(key,SignatureAlgorithm.HS512).
                compact();
        System.out.println("jws:"+jws);
        return jws;
    }

    /**
     * 获取token中的负载
     * @return
     */
    public Claims getClaimsFromToken(String token){
        /**
         * surprise motherf*ker！！1
         * 收到的token莫名其妙多了个引号？？？
         */
//        token = token.replace("\"","");
        SecretKey key = generalKey();
        System.out.println("生成k2:"+Arrays.toString(key.getEncoded()));
//        System.out.println(token);
//        System.out.println(key.getEncoded());
        Claims claims = null ;

        claims = Jwts.parser().
                setSigningKey(key).
                parseClaimsJws(token).
                getBody();
        return claims ;
    }

    public Date generateExpirationDate(){
        return null ;
    }

    public String getUserNameFromeToke(String token){
        Claims  claims = getClaimsFromToken(token) ;
        String userName = (String) claims.get("userName");
        return userName ;
    }

    public boolean validateToken(){
        return false;
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    public SecretKey generalKey(){
        String stringKey = Global.JWT_SECRET;//本地配置文件中加密的密文
        byte[] encodedKey =Base64.decodeBase64(stringKey);//本地的密码解码[B@152f6e2
//        SecretKey key = Keys.hmacShaKeyFor(encodedKey) ;
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length,
                "HmacSHA512");
//        System.out.println("general中："+key.getEncoded());
        return key;
    }

//    public Key generalKey(){
//        Key KEY = new SecretKeySpec("javastack".getBytes(),
//                SignatureAlgorithm.HS512.getJcaName());
//        return KEY;
//    }


}
