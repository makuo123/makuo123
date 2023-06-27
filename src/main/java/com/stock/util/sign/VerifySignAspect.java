package com.stock.util.sign;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Description
 * @Author makuo
 * @Date 2023/3/20 13:52
 **/
@Aspect
@Component
public class VerifySignAspect {

    private final SignUtil signUtil;

    public VerifySignAspect(SignUtil signUtil) {
        this.signUtil = signUtil;
    }

    @Around("@annotation(com.stock.util.sign.VerifySign)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 获取签名数据和待验签数据
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String signature = request.getHeader("signature");
        String data = request.getParameter("data");

        // 进行验签操作
        if (signUtil.verify(signature, data)) {
            return pjp.proceed();  // 验签成功，继续执行被拦截的方法
        } else {
            throw new RuntimeException("验签失败");  // 验签失败，抛出异常
        }
    }
}

