package com.duktig.springboot.service;

import com.duktig.springboot.entity.Picture;
import com.duktig.springboot.utils.PageResult;
import com.duktig.springboot.utils.PageUtil;

public interface PictureService {
    // 查询列表数据
    PageResult getPicturePage(PageUtil pageUtil);
    // 根据 id 查询对象
    Picture queryObject(Integer id);
    // 保存图片
    int savePicture(Picture picture);
    //更新图片
    int updatePicture(Picture picture);
    // 通过 id 删除 图片
    int deletePictureById(Integer id);
    // 批量删除
    int deleteBatch(Integer[] ids);
}
