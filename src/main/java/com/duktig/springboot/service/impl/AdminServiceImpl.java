package com.duktig.springboot.service.impl;

import com.duktig.springboot.dao.AdminUserDao;
import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.service.AdminUserService;
import com.duktig.springboot.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("adminService")
public class AdminServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserDao adminUserDao;
    @Override
    public PageResult getAdminUserPage(PageUtil pageUtil) {
        // 当前页码中的数据列表
        List<AdminUser> users = adminUserDao.findAdminUsers(pageUtil);
        // 数据总数，用来计算分页中每页中的条数
        int total = adminUserDao.getTotalAdminUser(pageUtil);
        PageResult pageResult = new PageResult(users, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public AdminUser updateTokenAndLogin(String name, String password) {
        AdminUser adminUser = adminUserDao.getAdminUserByUserNameAndPassword(name, password);
        if (adminUser != null) {
            // 登录成功执行修改 Token 操作
            String token = getNewToken(System.currentTimeMillis() + "", adminUser.getId());
            if (adminUserDao.updateUserToken(adminUser.getId(), token) > 0) {
                // 返回数据时带上 Token
                adminUser.setUserToken(token);
                return adminUser;
            }
        }
        return null;
    }
    /*
     * @Description: 获取 Token 值
     */
    private String getNewToken(String sessionId, Long userId) {
        String src = sessionId + userId + NumberUtil.genRandomNum(4);
        return SystemUtil.getToken(src);
    }

    @Override
    public AdminUser selectById(Long id) {
        return adminUserDao.getAdminUserById(id);
    }

    @Override
    public AdminUser selectByUserName(String userName) {
        return adminUserDao.getAdminUserByUserName(userName);
    }

    @Override
    public int saveUser(AdminUser user) {
        // 密码加密
        user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
        return adminUserDao.addUser(user);
    }

    @Override
    public int updatePassword(AdminUser user) {
        return adminUserDao.updateUserPassword(user.getId(), MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return adminUserDao.deleteBatch(ids);
    }

    @Override
    public AdminUser getAdminUserByToken(String userToken) {
        return adminUserDao.getAdminUserByToken(userToken);
    }
}
