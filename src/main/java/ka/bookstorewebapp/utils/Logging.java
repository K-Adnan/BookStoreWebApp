package ka.bookstorewebapp.utils;


import org.apache.log4j.Logger;

public class Logging {

    private static Logger LOG(Class clazz) {
        return Logger.getLogger(clazz);
    }

    public static void trace(String message, Class clazz) {
        LOG(clazz).trace(message);
    }

    public static void debut(String message, Class clazz) {
        LOG(clazz).debug(message);
    }

    public static void info(String message, Class clazz) {
        LOG(clazz).info(message);
    }

    public static void warn(String message, Class clazz) {
        LOG(clazz).warn(message);
    }

    public static void error(String message, Class clazz) {
        LOG(clazz).error(message);
    }

    public static void fatal(String message, Class clazz) {
        LOG(clazz).fatal(message);
    }
}
