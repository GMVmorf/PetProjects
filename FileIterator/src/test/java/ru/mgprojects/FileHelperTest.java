package ru.mgprojects;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.junit.Assert.assertTrue;

public class FileHelperTest
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    @Step("File info test")
    public void getFileInfo() throws Exception
    {
        LOGGER.info(logIt("First empty test."));
        //screenshot("First screenshot");
        assertTrue(true);
    }

    @Attachment
    public String logIt(final String logMessage)
    {
        return logMessage;
    }

}