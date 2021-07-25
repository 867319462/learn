package com.learn.config;

import com.learn.enums.ResultEnum;
import com.learn.exception.TokenAuthExpiredException;
import com.learn.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Stream;

/**
 * 异常处理
 *
 * @author wangxl
 * @date 2021/7/23 22:00
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 捕获异常，兜底
     *
     * @param exception 异常类
     * @return 响应
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception exception) {
        StackTraceElement[] stackTrace = exception.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (StackTraceElement stackTraceElement : stackTrace) {
            stringBuilder.append(stackTraceElement).append("\n");
        }
        log.error("出现未预料的错误：{} \n {}", exception.getMessage(), stringBuilder);
        return ResultEnum.setInfo(ResultEnum.UNKNOWN_EXCEPTION);
    }

    /**
     * 处理@Validated参数校验失败异常
     *
     * @param exception 异常类
     * @return 响应
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        if (!result.hasErrors()) {
            return ResultEnum.setInfo(ResultEnum.PARAM_ERROR);
        }

        List<ObjectError> errors = result.getAllErrors();
        Stream<String> errorList = errors.stream()
                .map(e -> {
                    FieldError fieldError = (FieldError) e;
                    String entity = fieldError.getObjectName();
                    String field = fieldError.getField();
                    String message = fieldError.getDefaultMessage();
                    log.warn("Bad Request Parameters：entity [{}],field [{}],message [{}]", entity, field, message);
                    return field + "：" + message;
                });
        return ResultEnum.setInfo(ResultEnum.PARAM_ERROR, errorList);
    }

    /**
     * 用户 token 过期
     *
     * @param exception 异常类
     * @return 响应
     */
    @ExceptionHandler(value = TokenAuthExpiredException.class)
    public Result tokenExpiredExceptionHandler(TokenAuthExpiredException exception) {
        log.warn("用户 token 过期");
        return ResultEnum.setInfo(ResultEnum.TOKEN_EXPIRED);
    }
}
