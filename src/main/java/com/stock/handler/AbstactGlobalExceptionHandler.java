package com.stock.handler;

import cn.hutool.core.util.StrUtil;
import com.stock.entity.R;
import com.stock.exception.BizException;
import com.stock.exception.code.ExceptionCode;
import org.apache.ibatis.exceptions.PersistenceException;
import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class AbstactGlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AbstactGlobalExceptionHandler.class);

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> otherExceptionHandler(Exception ex) {
        log.warn("Exception:", ex);
        return ex.getCause() instanceof BizException ? this.bizException((BizException)ex.getCause()) : R.builder().code(ExceptionCode.SYSTEM_BUSY.getCode()).data(null).msg(ExceptionCode.SYSTEM_BUSY.getMsg()).success(false).build();
    }


    @ExceptionHandler({BizException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<Object> bizException(BizException exc){
        return R.builder().code(exc.getCode()).data(null).msg(exc.getMessage())
                .success(false).build();
    }

    // region ====自定义异常=====
    /*@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ArgumentException.class})
    public R bizException(ArgumentException ex) {
        log.warn("ArgumentException:", ex);
        return R.result(ex.getCode(), (Object)null, ex.getMessage(), ex.getLocalizedMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R<?> forbiddenException(ForbiddenException ex) {
        log.warn("BizException:", ex);
        return R.result(ex.getCode(), (Object)null, ex.getMessage(), ex.getLocalizedMessage()).setPath(this.getPath());
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<?> unauthorizedException(UnauthorizedException ex) {
        log.warn("BizException:", ex);
        return R.result(ex.getCode(), (Object)null, ex.getMessage(), ex.getLocalizedMessage()).setPath(this.getPath());
    }*/
    // endregion

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.warn("HttpMessageNotReadableException:", ex);
        String message = ex.getMessage();
        if (StrUtil.containsAny(message, new CharSequence[]{"Could not read document:"})) {
            String msg = String.format("无法正确的解析json类型的参数：%s", StrUtil.subBetween(message, "Could not read document:", " at "));
            return new R(ExceptionCode.PARAM_EX.getCode(), (Object)null, msg, false);
        } else {
            return new R(ExceptionCode.PARAM_EX.getCode(), (Object)null, ExceptionCode.PARAM_EX.getMsg(), false);
        }

    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> bindException(BindException ex) {
        log.warn("BindException:", ex);

        try {
            String msg = ((FieldError) Objects.requireNonNull(ex.getBindingResult().getFieldError())).getDefaultMessage();
            if (StrUtil.isNotEmpty(msg)) {
                return new R(ExceptionCode.PARAM_EX.getCode(), (Object)null, msg, false);
            }
        } catch (Exception var4) {
            log.debug("获取异常描述失败", var4);
        }

        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) -> {
            msg.append("参数:[").append(oe.getObjectName()).append(".").append(oe.getField()).append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配.");
        });
        return new R(ExceptionCode.PARAM_EX.getCode(), (Object)null, msg.toString(), false);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.warn("MethodArgumentTypeMismatchException:", ex);
        String msg = "参数：[" + ex.getName() + "]的传入值：[" + ex.getValue() + "]与预期的字段类型：[" + ((Class)Objects.requireNonNull(ex.getRequiredType())).getName() + "]不匹配";
        return new R(ExceptionCode.PARAM_EX.getCode(), (Object)null, msg, false);
    }

    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> illegalStateException(IllegalStateException ex) {
        log.warn("IllegalStateException:", ex);
        return new R(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object)null, ExceptionCode.ILLEGAL_ARGUMENT_EX.getMsg(), false);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> missingServletRequestParameterException(MissingServletRequestParameterException ex) {
        log.warn("MissingServletRequestParameterException:", ex);
        return new R(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object)null, "缺少必须的[" + ex.getParameterType() + "]类型的参数[" + ex.getParameterName() + "]", false);
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> nullPointerException(NullPointerException ex) {
        log.warn("NullPointerException:", ex);
        return new R(ExceptionCode.NULL_POINT_EX.getCode(), (Object)null, ExceptionCode.NULL_POINT_EX.getMsg(),false);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> illegalArgumentException(IllegalArgumentException ex) {
        log.warn("IllegalArgumentException:", ex);
        return new R(ExceptionCode.ILLEGAL_ARGUMENT_EX.getCode(), (Object)null, ExceptionCode.ILLEGAL_ARGUMENT_EX.getMsg(), false);
    }

    @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        log.warn("HttpMediaTypeNotSupportedException:", ex);
        MediaType contentType = ex.getContentType();
        return contentType != null ? new R(ExceptionCode.MEDIA_TYPE_EX.getCode(), (Object)null, "请求类型(Content-Type)[" + contentType + "] 与实际接口的请求类型不匹配", false) : new R(ExceptionCode.MEDIA_TYPE_EX.getCode(), (Object)null, "无效的Content-Type类型", false);
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> missingServletRequestPartException(MissingServletRequestPartException ex) {
        log.warn("MissingServletRequestPartException:", ex);
        return new R(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object)null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg(), false);
    }

    @ExceptionHandler({ServletException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> servletException(ServletException ex) {
        log.warn("ServletException:", ex);
        String msg = "UT010016: Not a multi part request";
        return msg.equalsIgnoreCase(ex.getMessage()) ? new R(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object)null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg(), false) : new R(ExceptionCode.SYSTEM_BUSY.getCode(), (Object)null, ex.getMessage(), false);
    }

    @ExceptionHandler({MultipartException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> multipartException(MultipartException ex) {
        log.warn("MultipartException:", ex);
        return new R(ExceptionCode.REQUIRED_FILE_PARAM_EX.getCode(), (Object)null, ExceptionCode.REQUIRED_FILE_PARAM_EX.getMsg(), false);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> constraintViolationException(ConstraintViolationException ex) {
        log.warn("ConstraintViolationException:", ex);
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        String message = (String)violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return new R(ExceptionCode.BASE_VALID_PARAM.getCode(), (Object)null, message, false);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("MethodArgumentNotValidException:", ex);
        return new R(ExceptionCode.BASE_VALID_PARAM.getCode(), (Object)null, ((FieldError)Objects.requireNonNull(ex.getBindingResult().getFieldError())).getDefaultMessage(), false);
    }

    private String getPath() {
        String path = "";
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            path = request.getRequestURI();
        }

        return path;
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.warn("HttpRequestMethodNotSupportedException:", ex);
        return new R(ExceptionCode.METHOD_NOT_ALLOWED.getCode(), (Object)null, ExceptionCode.METHOD_NOT_ALLOWED.getMsg(), false);
    }

    @ExceptionHandler({PersistenceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> persistenceException(PersistenceException ex) {
        log.warn("PersistenceException:", ex);
        if (ex.getCause() instanceof BizException) {
            BizException cause = (BizException)ex.getCause();
            return new R(cause.getCode(), (Object)null, cause.getMessage(),false);
        } else {
            return new R(ExceptionCode.SQL_EX.getCode(), (Object)null, ExceptionCode.SQL_EX.getMsg(), false);
        }
    }

    @ExceptionHandler({MyBatisSystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> myBatisSystemException(MyBatisSystemException ex) {
        log.warn("PersistenceException:", ex);
        return ex.getCause() instanceof PersistenceException ? this.persistenceException((PersistenceException)ex.getCause()) : new R(ExceptionCode.SQL_EX.getCode(), (Object)null, ExceptionCode.SQL_EX.getMsg(), false);
    }

    @ExceptionHandler({SQLException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> sqlException(SQLException ex) {
        log.warn("SQLException:", ex);
        return new R(ExceptionCode.SQL_EX.getCode(), (Object)null, ExceptionCode.SQL_EX.getMsg(), false);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R<?> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.warn("DataIntegrityViolationException:", ex);
        return new R(ExceptionCode.SQL_EX.getCode(), (Object)null, ExceptionCode.SQL_EX.getMsg(), false);
    }
}
