package ru.mgprojects.environment;

import ch.qos.logback.core.OutputStreamAppender;
import org.slf4j.MDC;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class TestLogAllureAppender<E> extends OutputStreamAppender<E> {
    private static final String TEST_ID_KEY = "testId";
    private static final ThreadLocal<ByteArrayOutputStream> stream = ThreadLocal.withInitial(ByteArrayOutputStream::new);

    @Override
    public void start() {
        final OutputStream targetStream = new OutputStream() {
            @Override
            public void write(int b) {
                stream.get().write(b);
            }
        };
        super.setOutputStream(targetStream);
        super.start();
    }

    public void beginTest(UUID testId) {
        MDC.put(TEST_ID_KEY, testId.toString());
    }

    public byte[] endTest() {
        byte[] data = null;

        if (super.isStarted()) {
            data = stream.get().toByteArray();
            stream.remove();
        }
        MDC.remove(TEST_ID_KEY);
        return data;
    }
}
