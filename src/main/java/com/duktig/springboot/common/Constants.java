package com.duktig.springboot.common;
/*
 * @Description: 定义本项目需要用到的常量，例如 请求状态码，上传文件的默认前缀和上传文件的目录
 * @Creator: flanderschuang
 * @Date: 2021/3/16 11:29 上午
 */
public class Constants {
    // 成功处理请求
    public static final int RESULT_CODE_SUCCESS = 200;
    // 请求错误
    public static final int RESULT_CODE_BAD_REQUEST = 412;
    // 未登录
    public static final int RESULT_CODE_NOT_LOGIN = 402;
    // 传参错误
    public static final int RESULT_CODE_PARAM_ERROR = 406;
    // 服务器错误
    public static final int RESULT_CODE_SERVER_ERROR = 500;
    // 上传文件的默认url前缀
    public static final String FILE_PRE_URL = "http://localhost:8080";
    // 文件上传到服务器中的目录地址
    public final static String FILE_UPLOAD_PATH = "/home/Project/upload/";

}
