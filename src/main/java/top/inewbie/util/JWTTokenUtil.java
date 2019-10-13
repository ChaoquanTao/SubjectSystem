package top.inewbie.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 对于认证：查询完数据库后生成token
 */

public class JWTTokenUtil {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(Map claimsMap){
        String jws = Jwts.builder().setClaims(claimsMap).signWith(key).compact();
        return jws;
    }

    public Claims getClaimsFromToken(){
        return null ;
    }

    public Date generateExpirationDate(){
        return null ;
    }

    public String getUserNameFromeToke(){
        return null ;
    }

    public boolean validateToken(){
        return false;
    }


}
