package com.lyh.demo_game.exception;

import com.lyh.demo_game.vo.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptonHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo error(Exception e){
        e.printStackTrace();
        return ResultVo.error(e.getMessage());
    }
}
