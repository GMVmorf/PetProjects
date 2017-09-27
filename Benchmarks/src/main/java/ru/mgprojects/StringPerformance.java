package ru.mgprojects;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Date;
import java.util.UUID;

public class StringPerformance
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String STRING_TO_COMPARE = "String to compare";
    private static final int COMPARISONS_NUMBER = 1000000;

    private enum ComparisonType
    {
        SAME,
        RANDOM,
        UUID
    }

    public static void main(final String... args)
    {
        for (final ComparisonType comparisonType: ComparisonType.values())
        {
            apacheStringUtilsTest(comparisonType);
            standardStringUtilsTest(comparisonType);
        }
    }

    private static void standardStringUtilsTest(final ComparisonType comparisonType)
    {
        boolean result = false;
        final Date start = new Date();
        for (int i = 0; i < COMPARISONS_NUMBER; ++i)
        {
            final String toCompare = getStringToCompare(comparisonType);
            result = STRING_TO_COMPARE.equalsIgnoreCase(toCompare);
        }
        final Date end = new Date();
        logComparisonResult("Standard", comparisonType, result, end.getTime() - start.getTime());
    }

    private static void apacheStringUtilsTest(final ComparisonType comparisonType)
    {
        boolean result = false;
        final Date start = new Date();
        for (int i = 0; i < COMPARISONS_NUMBER; ++i)
        {
            final String toCompare = getStringToCompare(comparisonType);
            result = StringUtils.equalsIgnoreCase(STRING_TO_COMPARE, toCompare);
        }
        final Date end = new Date();
        logComparisonResult("Apache", comparisonType, result, end.getTime() - start.getTime());
    }

    private static void logComparisonResult(final String libraryName, final ComparisonType comparisonType, final boolean result, final long duration)
    {
        LOGGER.info("\n{}: \n  Comparison type: {}.\n  Result of comparison: {}.\n  Duration: {}",
            libraryName, comparisonType, result, duration);
    }

    private static String getStringToCompare(final ComparisonType comparisonType)
    {
        final String result;
        switch (comparisonType)
        {
            case SAME:
                result = STRING_TO_COMPARE;
                break;
            case RANDOM:
                result = RandomStringUtils.random(17);
                break;
            case UUID:
                result = UUID.randomUUID().toString();
                break;
            default:
                result = null;
        }
        return result;
    }
}
