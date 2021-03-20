package org.aijie.helloworld.user.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.aijie.helloworld.user.entity.User;
import org.aijie.helloworld.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-10-25 00:49:04
 */
@RestController
@RequestMapping("user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 页面
     */
    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public User selectOne(Long id) {
        return this.userService.queryById(id);
    }

    @GetMapping("findAllUser")
    public List<User> findAllUser(){
        return this.userService.queryAllByLimit(0,10);
    }

}