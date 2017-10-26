package ru.mgprojects;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class Encoder
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String DEFAULT_PATH = "C:\\temp\\temp\\UTF8file.txt";

    private static String readFile(final String filePath)
    {
        String result = "";
        final File file = new File(filePath);
        try
        {
            result = FileUtils.readFileToString(file, "ISO8859_1");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private static void writeFile(final String filePath, final String content, final String encoding)
    {
        try
        {
            FileUtils.write(new File(filePath), content, encoding);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
//        final String filename = StringUtils.defaultIfEmpty(args[0], DEFAULT_PATH);

//        final String content = readFile(filename);

//        writeFile(filename, content, "UTF-8");
        LOGGER.warn("Current thread: '{}'", Thread.currentThread().getName());

    }

}
