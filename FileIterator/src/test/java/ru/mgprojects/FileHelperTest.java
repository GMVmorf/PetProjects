package ru.mgprojects;

import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.slf4j.LoggerFactory;
import ru.mgprojects.environment.TestLogCatcherRule;

import java.lang.invoke.MethodHandles;

import static org.junit.Assert.assertTrue;

public class FileHelperTest
{
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Rule
    public final TestRule x = RuleChain.outerRule(new TestLogCatcherRule());

    @Test
    @Step("File info test")
    public void getFileInfo() throws Exception
    {
        LOGGER.info("Session ID = '{}'", StringUtils.substringBetween("SESSION_ID=1d9fddda-8476-497b-b3e0-f060efc5f2ef; SESSION_ID=21a4ba5a-c47a-4805-aec7-3b050fe733c9;;;;", "SESSION_ID=", ";"));
//        TestSetParameters.init("10.63.63.40", "root", "1q2w3e");
        LOGGER.info("First empty test.");
//        screenshot("First screenshot");
        assertTrue(true);
    }

//    @Attachment
//    public String logIt(final String logMessage)
//    {
//        return logMessage;
//    }

}