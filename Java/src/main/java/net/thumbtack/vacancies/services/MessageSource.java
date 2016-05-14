package net.thumbtack.vacancies.services;

public interface MessageSource {
    static String ERROR = "error";
    static String INCORRECTPASSWORD = "incorrectpassword";
    static String USERNOTFOUND = "usernotfound";
    static String DUPLICATEUSER = "duplicateuser";
    static String DUPLICATECOMPANY = "duplicatecompany";
    static String TOKEN = "token";

    String getMessage(String propertyName);

    String toJson(String key, String value);
}
