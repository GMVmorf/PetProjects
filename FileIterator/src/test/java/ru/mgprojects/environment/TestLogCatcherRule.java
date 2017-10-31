package ru.mgprojects.environment;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import io.qameta.allure.Attachment;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class TestLogCatcherRule implements TestRule {
    @SuppressWarnings("unchecked")
    private static final TestLogAllureAppender<ILoggingEvent> appender = (TestLogAllureAppender<ILoggingEvent>) ((Logger)LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME)).getAppender("ALLURE");

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                // TODO generate short UUID
                final UUID testId = UUID.randomUUID();
                appender.beginTest(testId);
                try {
                    base.evaluate();
                } finally {
                    attachLog(appender.endTest());
                }
            }
        };
    }

    @Attachment(value = "Test execution log", type = "text/plain")
    private static String attachLog(byte[] data) {
        if (data == null || data.length == 0)
            return "No data captured.";

        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return e.toString();
        }
    }
}
