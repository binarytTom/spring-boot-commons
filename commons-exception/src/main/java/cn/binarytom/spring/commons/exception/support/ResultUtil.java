package cn.binarytom.spring.commons.exception.support;

import cn.binarytom.spring.commons.exception.error.BaseErrors;
import cn.binarytom.spring.commons.exception.error.Errors;
import cn.binarytom.spring.commons.exception.exception.GlobalHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lieh
 * @date 2019-05-14 20:34
 **/
public class ResultUtil {
    private static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static GlobalHttpResponse error (Exception ex) {
        return new GlobalHttpResponse(BaseErrors.UNKNOWN_ERROR.code(), ex.getMessage() ,null);
    }

    public static GlobalHttpResponse error (GlobalHttpException exception) {
        return new GlobalHttpResponse(exception.getCode(), exception.getMessage(), null);
    }

    public static GlobalHttpResponse error (Errors error) {
        return new GlobalHttpResponse(error.code(), error.msg(), null);
    }

    public static void httpError (HttpServletResponse response, Errors errors) {
        // access denied
        GlobalHttpResponse responseBody = ResultUtil.error(errors);
        try {
            response.setHeader("Content-Type", "application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseBody.toString());
        } catch (IOException e) {
            logger.warn("io exception ->{}", e);
        }
    }

    public static GlobalHttpResponse success () {
        return success(null);
    }

    public static GlobalHttpResponse success (Object data) {
        return new GlobalHttpResponse(BaseErrors.SUCCESS.code(), BaseErrors.SUCCESS.msg(), data);
    }
}
