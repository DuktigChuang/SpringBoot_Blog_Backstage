package com.duktig.springboot.dao;

import com.duktig.springboot.entity.Picture;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface PictureDao {
    //  返回数据列表
    List<Picture> findPictures(Map<String, Object> map);

    // 数据数目
    int getTotalPictures(Map<String, Object> map);

    // 添加图片
    int insertPicture(Picture picture);

    // 修改图片
    int updatePicture(Picture picture);

    // 删除
    int delPicture(Integer id);

    // 根据 id 查找
    Picture findPictureById(Integer id);

    // 批量删除
    int deleteBatch(Object[] ids);
}
