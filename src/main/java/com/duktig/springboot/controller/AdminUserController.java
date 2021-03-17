package com.duktig.springboot.controller;

import com.duktig.springboot.common.Constants;
import com.duktig.springboot.common.Result;
import com.duktig.springboot.common.ResultGenerator;
import com.duktig.springboot.config.annotation.TokenToUser;
import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.service.AdminUserService;
import com.duktig.springboot.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    /*
     * @Description: 查询页面列表信息
     * @Creator: flanderschuang
     * @Date: 2021/3/17 9:55 上午
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String,Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.getSuccessResult(adminUserService.getAdminUserPage(pageUtil));
    }

    @PostMapping("/login")
    public Result login(@RequestParam AdminUser user) {
        Result result = ResultGenerator.getFailResult("登录失败");
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            result.setMessage("请填写登录信息！");
        }
        AdminUser loginUser = adminUserService.updateTokenAndLogin(user.getUserName(), user.getPassword());
        if (loginUser != null) {
            result = ResultGenerator.getSuccessResult(loginUser);
        }
        return result;
    }
    /*
     * @Description: 用户注册
     * @Creator: flanderschuang
     * @Date: 2021/3/17 9:56 上午
     */
    @PostMapping("/save")
    public Result save(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录");
        }
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");
        }
        AdminUser tempUser = adminUserService.selectByUserName(user.getUserName());
        if (tempUser != null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "当前用户已经存在，请返回登录页面");
        }
        if ("admin".endsWith(user.getUserName().trim())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无法添加管理员账户");
        }
        if (adminUserService.saveUser(user) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("添加失败，请重试");
        }
    }
    /*
     * @Description: 修改密码
     * @Creator: flanderschuang
     * @Date: 2021/3/17 9:56 上午
     */
    @PutMapping("/updatePassword")
    public Result update(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录，请登录后重试");
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "请输入登录密码");
        }
        AdminUser temUser = adminUserService.selectById(user.getId());
        if (temUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "用户不存在");
        }
        if ("admin".endsWith(temUser.getUserName().trim())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "您没有权限修改管理员账户密码");
        }
        temUser.setPassword(user.getPassword());
        if (adminUserService.updatePassword(user) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("修改失败，请重试");
        }
    }
    /*
     * @Description: 删除用户
     * @Creator: flanderschuang
     * @Date: 2021/3/17 9:56 上午
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Integer[] ids, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录，请登录后重试");
        }
        if (ids.length < 1) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");
        }
        if (adminUserService.deleteBatch(ids) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("删除失败");
        }
    }
}
