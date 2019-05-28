package cn.binarytom.spring.commons.logging.configuration;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfiguration;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class MyXMLConfiguration extends XmlConfiguration {
    private static final String PROJ_NAME_KEY = "project.name";
    private static final String LOG4J_PROP = "log4j2.properties";
    private static final String DEFAULT = "default";
    private static final String LOG_PATH = "log.path";

    public MyXMLConfiguration(final LoggerContext loggerContext, final ConfigurationSource configurationSource) {
        super(loggerContext, configurationSource);
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadAllProperties(LOG4J_PROP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty(PROJ_NAME_KEY, properties == null ? DEFAULT :properties.getProperty(PROJ_NAME_KEY));

        String catalinaHome = System.getProperty("catalina.home");
        System.setProperty(LOG_PATH, StringUtils.isEmpty(catalinaHome) ? "logs" :catalinaHome + "/logs");

    }

    @Override
    protected void doConfigure() {
        super.doConfigure();
    }
}
