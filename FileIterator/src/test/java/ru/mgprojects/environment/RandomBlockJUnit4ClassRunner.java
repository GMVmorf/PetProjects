package ru.mgprojects.environment;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import java.util.ArrayList;
import java.util.Collections;

public class RandomBlockJUnit4ClassRunner extends BlockJUnit4ClassRunner {

    public RandomBlockJUnit4ClassRunner(Class<?> klass)
            throws InitializationError {
        super(klass);
    }

    protected java.util.List<org.junit.runners.model.FrameworkMethod> computeTestMethods() {
        java.util.List<org.junit.runners.model.FrameworkMethod> methods = super.computeTestMethods();
        final ArrayList<org.junit.runners.model.FrameworkMethod> methods1 = new ArrayList<>(methods);
        Collections.shuffle(methods1);
        return methods1;
    }

}