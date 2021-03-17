package com.duktig.springboot.controller;

import com.duktig.springboot.common.Constants;
import com.duktig.springboot.common.Result;
import com.duktig.springboot.common.ResultGenerator;
import com.duktig.springboot.config.annotation.TokenToUser;
import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.entity.Picture;
import com.duktig.springboot.service.PictureService;
import com.duktig.springboot.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    /*
     * @Description: 查询分页数据列表
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:13 上午
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");
        }
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.getSuccessResult(pictureService.getPicturePage(pageUtil));
    }

    /*
     * @Description: 查询具体信息
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:17 上午
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "用户未登录，请登录后重试");
        }
        if (id < 1) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数错误");
        }
        Picture picture = pictureService.queryObject(id);
        if (picture == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");
        }
        return ResultGenerator.getSuccessResult(picture);
    }

    /*
     * @Description: 保存图片
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:25 上午
     */
    @PostMapping("/save")
    public Result save(@RequestBody Picture picture, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (StringUtils.isEmpty(picture.getPath()) || StringUtils.isEmpty(picture.getRemark())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (pictureService.savePicture(picture) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("添加失败");
        }
    }

    /*
     * @Description: 修改图片
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:26 上午
     */
    @PutMapping("/update")
    public Result update(@RequestBody Picture picture, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (null == picture.getId() || StringUtils.isEmpty(picture.getPath()) || StringUtils.isEmpty(picture.getRemark())) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        Picture tempPicture = pictureService.queryObject(picture.getId());
        if (tempPicture == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (pictureService.updatePicture(picture) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("修改失败");
        }
    }

    /*
     * @Description: 删除图片
     * @Creator: flanderschuang
     * @Date: 2021/3/17 10:29 上午
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Integer[] ids, @TokenToUser AdminUser loginUser) {
        if (loginUser == null) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录！");
        }
        if (ids.length < 1) {
            return ResultGenerator.getErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常！");
        }
        if (pictureService.deleteBatch(ids) > 0) {
            return ResultGenerator.getSuccessResult();
        } else {
            return ResultGenerator.getFailResult("删除失败");
        }
    }
}
