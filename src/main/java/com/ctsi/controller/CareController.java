package com.ctsi.controller;

import com.ctsi.entity.TbCare;
import com.ctsi.service.TbCareService;
import com.ctsi.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/31  15:03
 */
@RestController
@RequestMapping("/api/care")
public class CareController {

    @Autowired
    private TbCareService careService;

    @GetMapping("/get/{id}")
    public TbCare getById(@PathVariable Integer id) {
        return careService.selectById(id);
    }

    @PostMapping("/add")
    public Result add(@RequestBody TbCare care) {
        try{
            careService.add(care);
            return Result.ok();
        }catch (Exception e) {
            return Result.build(500,e.getMessage());
        }
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody TbCare care) {
        try{
            careService.edit(care);
            return Result.ok();
        }catch (Exception e) {
            return Result.build(500,e.getMessage());
        }
    }

}
