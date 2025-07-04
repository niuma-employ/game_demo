package com.lyh.demo_game.service;

import com.lyh.demo_game.domain.BackendUser;
import com.lyh.demo_game.domain.DevUser;
import com.lyh.demo_game.domain.NormalUser;
import com.lyh.demo_game.dto.LoginDto;
import com.lyh.demo_game.mapper.BackendUserMapper;
import com.lyh.demo_game.mapper.DevUserMapper;
import com.lyh.demo_game.mapper.NormalUserMapper;
import com.lyh.demo_game.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    BackendUserMapper backendUserMapper;

    @Autowired
    DevUserMapper devUserMapper;

    @Autowired
    NormalUserMapper normalUserMapper;

    public String login(LoginDto loginDto) {
        //判断账户密码是否正确
        if(loginDto.getUserType().equals("admin")){
            BackendUser admin = backendUserMapper.selectByUserName(loginDto.getUsername());
            if(admin==null){
                throw new RuntimeException("该账户不存在");
            }
            if(!admin.getUserpassword().equals(loginDto.getPassword())){
                throw new RuntimeException("密码错误");
            };

            //如果账户密码正确，那我们就要颁发token令牌
            String token = JwtUtils.getToken(loginDto.getUsername());
            return token;


        }else if(loginDto.getUserType().equals("dev")){
            DevUser devUser = devUserMapper.selectByUserName(loginDto.getUsername());

            if(devUser==null){
                throw new RuntimeException("该账户不存在");
            }
            if(!devUser.getDevpassword().equals(loginDto.getPassword())){
                throw new RuntimeException("密码错误");
            };

            //如果账户密码正确，那我们就要颁发token令牌
            String token = JwtUtils.getToken(loginDto.getUsername());
            return token;
        }else if(loginDto.getUserType().equals("normal")){
            NormalUser normalUser = normalUserMapper.selectByUserCode(loginDto.getUsername());
            if(normalUser==null){
                throw new RuntimeException("该账户不存在");
            }
            if(!normalUser.getUserPassword().equals(loginDto.getPassword())){
                throw new RuntimeException("密码错误");
            };
            String token = JwtUtils.getToken(loginDto.getUsername());
            return token;
        }else {
            throw new RuntimeException("非法操作");
        }

    }
}
