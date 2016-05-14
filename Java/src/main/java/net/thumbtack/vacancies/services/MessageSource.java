package net.thumbtack.vacancies.services;

public interface MessageSource {
    String getMessage(String propertyName);

    String getJsonErrorMessage(String propertyName);
}
