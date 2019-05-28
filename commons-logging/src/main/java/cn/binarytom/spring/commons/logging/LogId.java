package cn.binarytom.spring.commons.logging;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author lieh
 */
public class LogId {

    private static final String KEY_MDC = "logId";

    private static final String KEY_HEADER = "log-id";

    private static final String KEY_PARAMETER = "logid";
    
    private static final String LOGID_REGEX = "\\d+";

    private static final long THREAD_SEED = nextSeed();

    public static String generate() {
        return parse(THREAD_SEED + Thread.currentThread().getId());
    }

    public static String generate(HttpServletRequest request) {
        final String logId = Optional.ofNullable(request.getHeader(KEY_HEADER))
                .orElse(request.getParameter(KEY_PARAMETER));
        if (StringUtils.hasLength(logId) && logId.matches(LOGID_REGEX)) {
            return logId;
        } else {
            return parse(nextSeed());
        }
    }

    private static long nextSeed() {
        return System.currentTimeMillis() + System.nanoTime();
    }

    private static String parse(long seed) {
        return String.valueOf(Math.abs(seed & 0x7FFFFFFF | 0x80000000));
    }

    public static void put(String val) {
        MDC.put(KEY_MDC, val);
    }

    public static String get() {
        return MDC.get(KEY_MDC);
    }

    public static void remove() {
        MDC.remove(KEY_MDC);
    }

}

