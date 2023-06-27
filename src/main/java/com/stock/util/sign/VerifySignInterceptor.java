package com.stock.util.sign;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/20 13:49
 **/
@Component
public class VerifySignInterceptor implements HandlerInterceptor {

    private final SignUtil signUtil;

    public VerifySignInterceptor(SignUtil signUtil) {
        this.signUtil = signUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取签名数据和待验签数据
        String signature = request.getHeader("signature");
        String data = request.getParameter("data");

        // 进行验签操作
        if (signUtil.verify(signature, data)) {
            return true;  // 验签成功，继续执行后续操作
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());  // 验签失败，返回未授权状态码
            return false;
        }
    }
}

