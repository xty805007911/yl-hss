package com.ctsi.controller;

import com.ctsi.entity.TbAvatar;
import com.ctsi.service.TbAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 用户头像
 * @Author: Tianyu Xiao
 * @CreateDate: 2021/1/30  20:15
 */
@RestController
@RequestMapping("/api/avatar")
public class AvatarController {

    @Autowired
    private TbAvatarService avatarService;

    // 获取所有头像
    @GetMapping("/all")
    public List<TbAvatar> getAllAvatar() {
        return avatarService.getAvatarListAll();
    }
}
