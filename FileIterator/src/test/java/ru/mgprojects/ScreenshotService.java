package ru.mgprojects;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;

public class ScreenshotService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ScreenshotService() {
        // static utility class
    }

    public static void screenshot(String basicName) {
        screenshot(basicName, Thread.currentThread().getStackTrace());
    }

    public static void screenshot(String basicName, Throwable th) {
        screenshot(basicName, th.getStackTrace());
    }

    private static void screenshot(String basicName, StackTraceElement[] stackTrace) {
        final Optional<StackTraceElement> whereCalled = Arrays.stream(stackTrace).filter(ste -> ste.getClassName().endsWith("Test")).findFirst();
        final Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        final String timestampStr = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(timestamp);
        final String testIdentifier = whereCalled
                .map(ste -> String.format("%s.%s(line%s)", StringUtils.substringAfterLast(ste.getClassName(), "."), ste.getMethodName(), ste.getLineNumber()))
                .orElse("unknown-test");
        final String screenshotName = String.format("%s_%s_%s", timestampStr, testIdentifier, basicName);
        LOGGER.info("Make screenshot {}", screenshotName);
        final String pngPath = Selenide.screenshot(screenshotName);
        if (pngPath != null) {
            reportScreenshot(timestamp, basicName, pngPath,
                    whereCalled.map(StackTraceElement::getClassName).orElse("unknown-class"),
                    whereCalled.map(StackTraceElement::getMethodName).orElse("unknown-method"),
                    whereCalled.map(StackTraceElement::getLineNumber).orElse(0));
        } else {
            saveText(basicName, "Not possible to make screenshot. Maybe webdriver or browser is not running at the moment.");
        }
    }

    @Step("Screenshot '{basicName}' at {timestamp}")
    private static void reportScreenshot(Timestamp timestamp, String basicName, String pngPath, String className, String methodName, int lineNumber) {
        saveScreenshotPng(basicName, new File(pngPath));
        saveScreenshotHtml(basicName, new File(FilenameUtils.removeExtension(pngPath) + ".html"));
    }

    @Attachment(value = "{basicName} PNG", type = "image/png")
    private static byte[] saveScreenshotPng(String basicName, File file) {
        LOGGER.debug("Screenshot file ({}): {}", basicName, file);
        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Failed to load screenshot file: " + file, e);
        }
        return new byte[0];
    }

    @Attachment(value = "{basicName} HTML", type = "text/html")
    private static String saveScreenshotHtml(String basicName, File file) {
        LOGGER.debug("Page HTML ({}): {}", basicName, file);
        try {
            return FileUtils.readFileToString(file, Charset.forName("UTF-8"));
        } catch (IOException e) {
            LOGGER.error("Failed to load HTML file: " + file, e);
        }
        return null;
    }

    @Attachment(value = "{basicName}", type = "text/plain")
    private static String saveText(String basicName, String text) {
        return text;
    }

    public static void screenshot() {
        Selenide.screenshot(getTimestampString(new Timestamp(System.currentTimeMillis())));
    }

    private static String getTimestampString(final Timestamp timestamp) {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(timestamp);
    }
}
