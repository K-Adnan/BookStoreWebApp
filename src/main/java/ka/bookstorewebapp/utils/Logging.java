package ka.bookstorewebapp.utils;


import org.apache.log4j.Logger;

public class Logging {

    private static Logger LOG = Logger.getLogger(Logging.class);

    public static void traces(String message) {
        LOG.trace(message);
    }

    public static void debut(String message) {
        LOG.debug(message);
    }

    public static void info(String message) {
        LOG.info(message);
    }

    public static void warn(String message) {
        LOG.warn(message);
    }

    public static void error(String message) {
        LOG.error(message);
    }

    public static void fatal(String message) {
        LOG.fatal(message);
    }

}
