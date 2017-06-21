package com.senacor.devcon;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;
import org.slf4j.Logger;

import java.lang.reflect.Method;

import static org.slf4j.LoggerFactory.getLogger;

public class MyExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final Logger logger = getLogger(MyExtension.class);

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        context.getStore().put(context.getTestMethod().get().getName(), System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        Method testMethod = context.getTestMethod().get();
        long start = context.getStore().remove(testMethod.getName(), long.class);
        long duration = System.currentTimeMillis() - start;

        logger.info("Test {} took {} ms", testMethod.getName(), duration);
    }

}
