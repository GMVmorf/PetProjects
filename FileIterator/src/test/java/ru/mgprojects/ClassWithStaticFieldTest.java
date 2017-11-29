package ru.mgprojects;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClassWithStaticFieldTest
{
    private ClassWithStaticField classWithStaticField = new ClassWithStaticField();

    @Mock
    private ClassToMock objectToMock;

    @After
    public void tearDown()
    {
        verifyNoMoreInteractions(objectToMock);
    }

    @Test
    public void test1()
    {
        final ClassToMock realObject = new ClassToMock();

        final Integer actual = classWithStaticField.getStaticField(realObject);

        assertEquals(13, actual.intValue());

    }

    @Test
    public void test2()
    {
        when(objectToMock.verifyThisMethod()).thenReturn(13);

        final Integer actual = classWithStaticField.getStaticField(objectToMock);

        assertEquals(13, actual.intValue());
        verify(objectToMock).verifyThisMethod();
    }
}