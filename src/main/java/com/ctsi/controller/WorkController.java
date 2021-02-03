package com.ctsi.controller;

import com.ctsi.config.Constant;
import com.ctsi.entity.TbWork;
import com.ctsi.service.TbWorkService;
import com.ctsi.util.PageResult;
import com.ctsi.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/2/3  20:31
 */
@RestController
@RequestMapping("/api/work")
public class WorkController {

    @Autowired
    private TbWorkService workService;

    // 根据id查询
    @GetMapping("/{id}")
    public TbWork getWorkById(@PathVariable Integer id) {
        return workService.selectById(id);
    }

    // 分页查询
    @GetMapping("/list")
    public PageResult<TbWork> getWorkList(Integer page, Integer userId) {
        return workService.selectPageList(page, Constant.PAGE_SIZE,userId);
    }

    //添加
    @PostMapping("/add")
    public Result addWork(@RequestBody TbWork work) {
        try{
            workService.add(work);
            return Result.ok();
        }catch (Exception e) {
            return Result.ok(e.getMessage());
        }
    }

    //修改
    @PostMapping("/edit")
    public Result editWork(@RequestBody TbWork work) {
        try{
            workService.update(work);
            return Result.ok();
        }catch (Exception e) {
            return Result.ok(e.getMessage());
        }
    }

    //删除
    @GetMapping("/delete/{id}")
    public Result deleteWord(@PathVariable Integer id) {
        try{
            workService.delete(id);
            return Result.ok();
        }catch (Exception e) {
            return Result.ok(e.getMessage());
        }
    }

}
