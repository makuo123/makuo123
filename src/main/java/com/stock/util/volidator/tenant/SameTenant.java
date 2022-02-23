package com.stock.util.volidator.tenant;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * uid是否属于当前上下文租户
 * 注：当被校验对象为空时，校验为通过，所以如果需要校验对象必填，请自行结合相关校验注解
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SameTenant.StringChecker.class, SameTenant.LongChecker.class})
@Documented
public @interface SameTenant {

    String message() default "用户不存在或者不属于当前组织";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class StringChecker implements ConstraintValidator<SameTenant, String> {

        @Override
        public void initialize(SameTenant arg0) {
        }

        @Override
        public boolean isValid(String uid, ConstraintValidatorContext context) {

            System.out.println("到注解里面了"+uid);
            if (StringUtils.isNotBlank(uid)) {
                return true;
            }
            return false;
        }
    }

    class LongChecker implements ConstraintValidator<SameTenant, Long> {

        @Override
        public void initialize(SameTenant arg0) {
        }

        @Override
        public boolean isValid(Long uid, ConstraintValidatorContext context) {
            System.out.println("到注解里面了"+uid);
            if (null != uid) {
                return true;
            }
            return false;
        }
    }
}
