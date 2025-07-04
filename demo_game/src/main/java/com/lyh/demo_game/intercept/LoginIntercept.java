package com.lyh.demo_game.intercept;

import com.alibaba.fastjson2.JSON;
import com.lyh.demo_game.utils.JwtUtils;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle)throws Exception{
        //检查前端传过来的token
        response.setContentType("application/json;charset=UTF-8");
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultVo.reject("token没传")));
            writer.close();
            writer.flush();
            return false;
        }

        try {
            JwtUtils.getUserName(token);
            return true;
        } catch (Exception e) {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(ResultVo.reject(e.getMessage())));
            writer.close();
            writer.flush();
            return false;
        }
    }
}
