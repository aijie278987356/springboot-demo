package org.aijie.helloworld.capital.controller;

import lombok.extern.slf4j.Slf4j;
import org.aijie.helloworld.capital.entity.Capital;
import org.aijie.helloworld.capital.service.CapitalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * (Capital)表控制层
 *
 * @author makejava
 * @since 2020-11-10 21:17:42
 */
@Slf4j
@Controller
public class CapitalController {
    /**
     * 服务对象
     */
    @Resource
    private CapitalService capitalService;

    /**
     * 页面
     */
    @RequestMapping("/index")
    public String index(){

        return "index";
    }
    /**
     * 导入excel
     */
    @RequestMapping("/import")
    @ResponseBody
    public String excelImport(@RequestParam(value="filename") MultipartFile file, HttpSession session){
        try {
            capitalService.addCapital(file);
        } catch (Exception e) {
            log.info("导入报错："+e.getMessage());
        }

        return "excel文件数据导入成功！";

    }
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Capital selectOne(String id) {
        return this.capitalService.queryById(id);
    }

}