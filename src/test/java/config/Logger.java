package config;

import org.slf4j.LoggerFactory;

public class Logger {

    public static Logger out = new Logger(Logger.class.getName());

    private final org.slf4j.Logger loggerClass;

    public Logger(final String loggerName) {
        this.loggerClass = LoggerFactory.getLogger(loggerName);
    }


    public void trace(final String format, final Object... objects) {
        loggerClass.trace(String.format(format, objects));
    }

    public void info(final Object message) {
        loggerClass.info("{}", message);
    }


    public void info(final String format, final Object... objects) {
        loggerClass.info(String.format(format, objects));
    }

    public void error(final Object message, final Throwable e) {
        loggerClass.error("{}", message, e);
    }

    public void error(final String format, final Object... objects) {
        loggerClass.error(String.format(format, objects));
    }

    public void warn(final String format, final Object... objects) {
        loggerClass.warn(String.format(format, objects));
    }

}

