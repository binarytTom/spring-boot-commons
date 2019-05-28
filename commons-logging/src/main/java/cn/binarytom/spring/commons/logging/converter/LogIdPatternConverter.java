package cn.binarytom.spring.commons.logging.converter;

import cn.binarytom.spring.commons.logging.LogId;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternConverter;

/**
 * @author lieh
 */
@Plugin(name = "LogIdPatternConverter", category = PatternConverter.CATEGORY)
@ConverterKeys({ "y" })
public class LogIdPatternConverter extends LogEventPatternConverter{

    private static final LogIdPatternConverter INSTANCE =
            new LogIdPatternConverter();

    public static LogIdPatternConverter newInstance(
            final String[] options) {
        return INSTANCE;
    }

    private LogIdPatternConverter() {
        super("LogId", "logId");
    }

    @Override
    public void format(LogEvent logEvent, StringBuilder stringBuilder) {
        String logId = LogId.get();
        stringBuilder.append(logId == null ? LogId.generate() : logId);
    }

}
