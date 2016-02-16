package net.thumbtack.vacancies.config;

import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class MessageSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSource.class);
    private static final MessageSource INSTANCE = new MessageSource();
    private final String filename = "messages.properties";
    private Properties properties = new Properties();

    private MessageSource() {

        try (InputStream inputStream = Resources.getResourceAsStream(filename)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Can't load property file: ", e);
            throw new RuntimeException(e);
        }
    }

    public static MessageSource getInstance() {
        return INSTANCE;
    }

    public String getMessage(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
