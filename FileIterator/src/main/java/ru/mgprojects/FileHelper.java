package ru.mgprojects;

import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.invoke.MethodHandles;

import static org.apache.commons.io.FileUtils.listFilesAndDirs;
import static org.apache.commons.io.FileUtils.sizeOf;

public class FileHelper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String OS_LINUX = "Linux";
    private static final String OS_WINDOWS = "Windows";
    private static final String OS_UNKNOWN = "Unknown";

    private static final String DEFAULT_LINUX_FILEPATH = "/tmp/temp";
    private static final String DEFAULT_WINDOWS_FILEPATH = "C:\\temp\\temp";
    private static final String OS_TYPE =
        SystemUtils.IS_OS_LINUX ? OS_LINUX :
            (SystemUtils.IS_OS_WINDOWS ? OS_WINDOWS : OS_UNKNOWN);
    private static final String DEFAULT_FILEPATH =
        OS_TYPE.equals(OS_LINUX) ? DEFAULT_LINUX_FILEPATH :
            (OS_TYPE.equals(OS_WINDOWS) ? DEFAULT_WINDOWS_FILEPATH : "");
    private static final String PATHNAME = StringUtils.defaultIfEmpty(System.getProperty("path"), DEFAULT_FILEPATH);
    private static final File FILE_OR_DIRECTORY = new File(PATHNAME);
    private static final IOFileFilter ALL_FILE_FILTER = new WildcardFileFilter("*");
    private static final IOFileFilter ALL_DIR_FILTER = new WildcardFileFilter("*");

    public static void main(String[] args)
    {
        initDirectory();

        LOGGER.info("Folder: {}", PATHNAME);
        for (final File file : listFilesAndDirs(FILE_OR_DIRECTORY, ALL_FILE_FILTER, ALL_DIR_FILTER))
        {
            LOGGER.info(getFileInfo(file));
        }
    }

    private static void initDirectory()
    {
        if (!FILE_OR_DIRECTORY.exists())
        {
            LOGGER.info("Creating new directory: '{}'", FILE_OR_DIRECTORY.getAbsolutePath());
            final boolean isFileCreated = FILE_OR_DIRECTORY.mkdir();
            if (isFileCreated)
            {
                LOGGER.info("Directory '{}' successfully created.", FILE_OR_DIRECTORY.getAbsolutePath());
            }
            else
            {
                LOGGER.info("Directory '{}' already created.", FILE_OR_DIRECTORY.getAbsolutePath());
            }
        }
        else
        {
            if (FILE_OR_DIRECTORY.isDirectory())
            {
                LOGGER.info("Directory '{}' already exists.", FILE_OR_DIRECTORY.getAbsolutePath());
            }
            else
            {
                LOGGER.info("'{}' is not a directory.", FILE_OR_DIRECTORY.getAbsolutePath());
            }
        }
    }

    public static String getFileInfo(final File file)
    {
        final String type = file.isDirectory() ? "Directory" : "File";
        return String.format("%s: %s, size: %s", type, file.getAbsolutePath(), sizeOf(file));
    }
}
