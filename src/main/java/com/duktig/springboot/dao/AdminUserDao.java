package com.duktig.springboot.dao;

import com.duktig.springboot.entity.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface AdminUserDao {
    // 根据参数查询用户列表
    List<AdminUser> findAdminUsers(Map param);
    // 查询用户总数
    int getTotalAdminUser(Map param);
    // 根据用户名和密码获取用户记录,@Param 参数存在的意义是为了和 Mapper.xml 对应 #{username} #{passwordMD5}
    // 直接不加 @Param 会出现异常，提示参数不对应，除了以上方法外，还能把 Mapper.xml 改写成 #{arg0} #{arg1} 或者 {param1} {param2}.但是因为两者下标的区别，所以不推荐使用这种方式
    AdminUser getAdminUserByUserNameAndPassword(@Param("userName") String userName, @Param("passwordMD5") String passwordMD5);
    // 根据 userToken 获取用户记录
    AdminUser getAdminUserByToken(String userToken);
    // 根据 id 获取用户记录
    AdminUser getAdminUserById(Long id);
    // 根据用户名获取用户记录
    AdminUser getAdminUserByUserName(String userName);
    // 新增用户记录
    int addUser(AdminUser user);
    // 批量新增用户记录
    // 此处的 @Param 与上面不同，这里是对象参数，因为这里传入的对象不止一个，所以用对象参数代替。如果这里修改为 @Param("User") Mapper.xml 应该改写为 #{User.username},而不是 #{adminUser.userName}
    int insertUserBatch(@Param("adminUsers") List<AdminUser> adminUsers);
    // 修改密码
    int updateUserPassword(@Param("userId")Long userId,@Param("newPassword")String newPassword);
    // 更新用户 Token
    int updateUserToken(@Param("userId") Long userId, @Param("newToken") String newToken);
    // 批量删除
    int deleteBatch(Object[] ids);
    // 获取全部用户
    List<AdminUser> getAllAdminUsers();
}
