package ru.mgprojects;

public class ClassWithStaticField
{
    private volatile static Integer staticField;

    public static int getStaticField(final ClassToMock objectToMock)
    {
//        if(staticField == null)
//        {
//            synchronized (ClassWithStaticField.class)
//            {
                if(staticField == null)
                {
                    staticField = someStaticMethod(objectToMock);
                }
//            }
//        }
        return staticField;
    }

    private static Integer someStaticMethod(final ClassToMock objectToMock)
    {
        return objectToMock.verifyThisMethod();
    }
}
