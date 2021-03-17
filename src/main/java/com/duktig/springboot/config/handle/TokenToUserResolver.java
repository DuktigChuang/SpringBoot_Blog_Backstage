package com.duktig.springboot.config.handle;

import com.duktig.springboot.config.annotation.TokenToUser;
import com.duktig.springboot.entity.AdminUser;
import com.duktig.springboot.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
 * @Description: SpringBoot自定义注解实现Token校验，这样就不用使用 security 框架
 * @Creator: flanderschuang
 * @Date: 2021/3/16 12:33 下午
 * * @param null
 * @return:
 * @link{https://www.javazhiyin.com/83772.html}
 */
@Component
public class TokenToUserResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private AdminUserService adminUserService;

    public TokenToUserResolver() {
    }

    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(TokenToUser.class)) {
            return true;
        }
        return false;
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取登陆用户信息
        if (parameter.getParameterAnnotation(TokenToUser.class) instanceof TokenToUser) {
            AdminUser adminUser = null;
            String token = webRequest.getHeader("token");
            if (null != token && !"".equals(token) && token.length() == 32) {
                adminUser = adminUserService.getAdminUserByToken(token);
            }
            return adminUser;
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readLength = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readLength == -1) {
                break;
            }
            i += readLength;
        }
        return buffer;
    }
}
