package com.duktig.springboot.service;

import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.utils.PageResult;
import com.duktig.springboot.utils.PageUtil;

public interface AdminUserService {
    // 分页功能
    PageResult getAdminUserPage(PageUtil pageUtil);

    // 登录功能
    AdminUser updateTokenAndLogin(String name, String password);

    // 根据 id 获取用户记录
    AdminUser selectById(Long id);

    // 根据用户名获取用户记录
    AdminUser selectByUserName(String userName);

    // 新增用户
    int saveUser(AdminUser user);

    // 修改密码
    int updatePassword(AdminUser user);

    // 批量删除功能
    int deleteBatch(Integer[] ids);

    // 根据用户 Token 获取用户记录
    AdminUser getAdminUserByToken(String userToken);

}
