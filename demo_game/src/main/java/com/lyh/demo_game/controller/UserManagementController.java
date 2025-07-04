package com.lyh.demo_game.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyh.demo_game.domain.BackendUser;
import com.lyh.demo_game.domain.DevUser;
import com.lyh.demo_game.domain.NormalUser;
import com.lyh.demo_game.mapper.BackendUserMapper;
import com.lyh.demo_game.mapper.DevUserMapper;
import com.lyh.demo_game.mapper.NormalUserMapper;
import com.lyh.demo_game.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理控制器，提供管理员、开发者、普通用户的分页查询接口
 */
@RestController
@RequestMapping("/user")
public class UserManagementController {
    // 注入后台用户Mapper
    @Autowired
    private BackendUserMapper backendUserMapper;
    // 注入开发者用户Mapper
    @Autowired
    private DevUserMapper devUserMapper;
    // 注入普通用户Mapper
    @Autowired
    private NormalUserMapper normalUserMapper;

    /**
     * 获取管理员列表，分页返回
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 管理员分页信息
     */
    @GetMapping("/admin/list")
    public ResultVo getAdminList(@RequestParam(defaultValue = "1") Integer pageNum,
                                @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<BackendUser> pageInfo = new PageInfo<>(backendUserMapper.selectAll());
        return ResultVo.success("获取管理员列表成功", pageInfo);
    }

    /**
     * 获取开发者列表，分页返回
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 开发者分页信息
     */
    @GetMapping("/dev/list")
    public ResultVo getDevList(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<DevUser> pageInfo = new PageInfo<>(devUserMapper.selectAll());
        return ResultVo.success("获取开发者列表成功", pageInfo);
    }

    /**
     * 获取普通用户列表，分页返回
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 普通用户分页信息
     */
    @GetMapping("/normal/list")
    public ResultVo getNormalUserList(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<NormalUser> pageInfo = new PageInfo<>(normalUserMapper.selectAll());
        return ResultVo.success("获取普通用户列表成功", pageInfo);
    }
} 