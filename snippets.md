Hello JUnit 5
=============

Gradle
----------

Dependency (IntelliJ only):
```
testCompile "org.junit.jupiter:junit-jupiter-api:5.0.0-M4"
```


Gradle (Gradle run):
```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath 'org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4'
    }
}

apply plugin: 'org.junit.platform.gradle.plugin'


testRuntime("org.junit.jupiter:junit-jupiter-engine:5.0.0-M4")

```


Password Policy
---------------

https://stackoverflow.com/questions/3466850/complex-password-regular-expression

```
/* 
The password must then contain characters from at least 3 of the following 4 rules:
Upper case
Lower case
Numbers
Non-alpha numeric
*/
^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\d\W])|(?=.*\W)(?=.*\d))|(?=.*\W)(?=.*[A-Z])(?=.*\d)).{8,}$
```


JUnit Parameters
----------------
```
	testCompile('org.junit.jupiter:junit-jupiter-params:5.0.0-M4')
```



Tags
----
```
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies { classpath 'org.junit.platform:junit-platform-gradle-plugin:1.0.0-M4' }
}
apply plugin: 'org.junit.platform.gradle.plugin'

dependencies {
    testCompile "org.junit.jupiter:junit-jupiter-api:5.0.0-M4"
    testRuntime "org.junit.jupiter:junit-jupiter-engine:5.0.0-M4"
}

junitPlatform {
    filters {
        tags { exclude 'slow' }
    }
}
```

Timing Extension
----------------
```
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
```


