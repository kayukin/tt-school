package net.thumbtack.vacancies.services;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Konstantin on 15.02.2016.
 */
public class MessageSourceImpl implements MessageSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSourceImpl.class);
    private static final MessageSourceImpl INSTANCE = new MessageSourceImpl();
    private final String filename = "messages.properties";
    private Properties properties = new Properties();

    private MessageSourceImpl() {

        try (InputStream inputStream = MessageSourceImpl.class.getClassLoader().getResourceAsStream(filename);) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Can't load property file: ", e);
            throw new RuntimeException(e);
        }
    }

    public static MessageSourceImpl getInstance() {
        return INSTANCE;
    }

    public String getMessage(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public String getJsonErrorMessage(String propertyName) {
        JsonObject json = new JsonObject();
        json.addProperty("error", getMessage(propertyName));
        return json.toString();
    }
}
