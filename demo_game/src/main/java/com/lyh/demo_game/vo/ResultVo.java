package com.lyh.demo_game.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVo {
    private Integer code; //2000 5000
    private  String mess;
    private Object data; //携带数据

    public static ResultVo success(String mess,Object data){
        return new ResultVo(2000,mess,data);
    }

    public static ResultVo error(String mess){
        return new ResultVo(5000,mess,null);
    }

    public static ResultVo reject(String mess){
        return new ResultVo(3000,mess,null);
    }
}
