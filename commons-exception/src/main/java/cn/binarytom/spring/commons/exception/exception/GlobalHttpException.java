package cn.binarytom.spring.commons.exception.exception;

import cn.binarytom.spring.commons.exception.error.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lieh
 * @date 2019-05-14 20:36
 **/
public class GlobalHttpException extends RuntimeException {
    Logger logger = LoggerFactory.getLogger(GlobalHttpException.class);

    private int code;

    public GlobalHttpException(Errors errorEnum) {
        super(errorEnum.msg());
        this.code = errorEnum.code();
    }

    public GlobalHttpException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
