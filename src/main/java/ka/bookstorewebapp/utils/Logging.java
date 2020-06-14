package ka.bookstorewebapp.utils;


import org.apache.log4j.Logger;

public class Logging {

    private static Logger LOG = Logger.getLogger(Logging.class);

    public static void info(String message) {
        LOG.info(message);
    }

}
