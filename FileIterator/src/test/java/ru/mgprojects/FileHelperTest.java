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

    static int i;

    @Rule
    public final TestRule x = RuleChain.outerRule(new TestLogCatcherRule());

    @Test
    @Step("File info test")
    public void getFileInfo() throws Exception
    {
        final String baseString = "[JSESSIONID=H-Fwy4nc0AROw2IA6803vHUBpHDMugl7J02pXVjI.localhost; path=/; HttpOnly, device-tag=YXp1cmUyMDE3MTEwOTE5MzkyNUBHeFJqZWRvbWFpbi5lMmVcL77FlsoIzJGxD1Ha0uf2%2FM%2BE%2Brs1LCmvSyHpqFt5pXdhppy8MbgY; path=/; secure; HttpOnly; Max-Age=15552000; Expires=Wed, 09-May-2018 06:32:47 GMT]";
        LOGGER.info(String.format("%s", StringUtils.substringBetween(baseString, "JSESSIONID", ";")));
        LOGGER.info(String.format("%s", StringUtils.substringBetween(baseString, "device-tag", ";")));

//        screenshot("First screenshot");
        assertTrue(true);
    }

}