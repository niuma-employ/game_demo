package com.lyh.demo_game.utils;

import com.lyh.demo_game.domain.NormalUser;
import com.lyh.demo_game.domain.DevUser;
import com.lyh.demo_game.domain.BackendUser;
import com.lyh.demo_game.mapper.NormalUserMapper;
import com.lyh.demo_game.mapper.DevUserMapper;
import com.lyh.demo_game.mapper.BackendUserMapper;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

@Component
public class JwtUtils {
    private static NormalUserMapper staticNormalUserMapper;
    private static DevUserMapper staticDevUserMapper;
    private static BackendUserMapper staticBackendUserMapper;

    @Autowired
    private NormalUserMapper normalUserMapper;
    @Autowired
    private DevUserMapper devUserMapper;
    @Autowired
    private BackendUserMapper backendUserMapper;

    @PostConstruct
    public void init() {
        staticNormalUserMapper = normalUserMapper;
        staticDevUserMapper = devUserMapper;
        staticBackendUserMapper = backendUserMapper;
    }

    /**
     * 生成token字符串  xxx.yyy.zzzz
     * @param username
     * @return
     */
    public static  String getToken(String username){

        return Jwts.builder().setHeaderParam("typ", "JWT")
                //过期时间  一个小时后此token就失效了
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, "jf3q-jwt").compact();
    }
    public static String getUserName(String token){

        String username = null;
        try {
            username = Jwts.parser().setSigningKey("jf3q-jwt").parseClaimsJws(token).getBody().getSubject();
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("token已经过期");
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("不支持的token");
        } catch (MalformedJwtException e) {
            throw new RuntimeException("token令牌格式不对");
        } catch (SignatureException e) {
            throw new RuntimeException("token签名问题");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("参数不合法-密钥不对");
        }


        return username;

    }

    public static Long getUserId(String token, String userType) {
        String userCode = getUserName(token);
        if ("dev".equals(userType)) {
            if (staticDevUserMapper == null) throw new RuntimeException("DevUserMapper 未注入");
            DevUser user = staticDevUserMapper.selectByUserName(userCode);
            if (user == null) throw new RuntimeException("开发者不存在");
            return user.getId();
        } else if ("admin".equals(userType)) {
            if (staticBackendUserMapper == null) throw new RuntimeException("BackendUserMapper 未注入");
            BackendUser user = staticBackendUserMapper.selectByUserName(userCode);
            if (user == null) throw new RuntimeException("管理员不存在");
            return user.getId();
        } else {
            if (staticNormalUserMapper == null) throw new RuntimeException("NormalUserMapper 未注入");
            NormalUser user = staticNormalUserMapper.selectByUserCode(userCode);
            if (user == null) throw new RuntimeException("用户不存在");
            return user.getId();
        }
    }
}
