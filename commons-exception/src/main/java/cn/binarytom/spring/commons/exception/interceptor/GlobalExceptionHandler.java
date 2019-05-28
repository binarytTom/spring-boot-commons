package cn.binarytom.spring.commons.exception.interceptor;

import cn.binarytom.spring.commons.exception.exception.GlobalHttpException;
import cn.binarytom.spring.commons.exception.support.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lieh
 * @date 2019-05-14 20:31
 **/
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE-1)
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
        logger.info("[INIT]: ----> GlobalExceptionHandler");
    }

    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @ExceptionHandler(value = Exception.class)
    public Object userExceptionHandler(Exception ex) {
        logger.warn(ex.toString(), ex);
        return ResultUtil.error(ex);
    }

    @ResponseBody
    @Order(Ordered.LOWEST_PRECEDENCE-1)
    @ExceptionHandler(value = GlobalHttpException.class)
    public Object userExceptionHandler(GlobalHttpException ex) {
        logger.warn(ex.toString(), ex);
        return ResultUtil.error(ex);
    }
}
