[INFO] Scanning for projects...
[INFO] 
[INFO] ----------------------< com.demo:RestAssuredDemo >----------------------
[INFO] Building RestAssuredDemo 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- resources:3.4.0:resources (default-resources) @ RestAssuredDemo ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /Users/sarthakbansal/RestAssured/src/main/resources
[INFO] 
[INFO] --- compiler:3.15.0:compile (default-compile) @ RestAssuredDemo ---
[INFO] Nothing to compile - all classes are up to date.
[INFO] 
[INFO] --- resources:3.4.0:testResources (default-testResources) @ RestAssuredDemo ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] Copying 1 resource from src/test/resources to target/test-classes
[INFO] 
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ RestAssuredDemo ---
[INFO] Recompiling the module because of added or removed source files.
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 3 source files with javac [debug target 1.8] to target/test-classes
[WARNING] bootstrap class path is not set in conjunction with -source 8
  not setting the bootstrap class path may lead to class files that cannot run on JDK 8
    --release 8 is recommended instead of -source 8 -target 1.8 because it sets the bootstrap class path automatically
[WARNING] source value 8 is obsolete and will be removed in a future release
[WARNING] target value 8 is obsolete and will be removed in a future release
[WARNING] To suppress warnings about obsolete options, use -Xlint:-options.
[INFO] 
[INFO] --- surefire:3.5.4:test (default-test) @ RestAssuredDemo ---
[INFO] Using auto detected provider org.apache.maven.surefire.testng.TestNGProvider
[INFO] 
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.demo.b_DifferentWaysToPostJSON
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[ERROR] Tests run: 5, Failures: 1, Errors: 0, Skipped: 0, Time elapsed: 13.08 s <<< FAILURE! -- in com.demo.b_DifferentWaysToPostJSON
[ERROR] com.demo.b_DifferentWaysToPostJSON.postUsingPOJO -- Time elapsed: 5.815 s <<< FAILURE!
java.lang.AssertionError: 
1 expectation failed.
JSON path data.'CPU model' doesn't match.
Expected: M5 Extreme
  Actual: null

        at java.base/jdk.internal.reflect.DirectConstructorHandleAccessor.newInstance(DirectConstructorHandleAccessor.java:62)
        at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499)
        at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:483)
        at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73)
        at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108)
        at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57)
        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263)
        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277)
        at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512)
        at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source)
        at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696)
        at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43)
        at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198)
        at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62)
        at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185)
        at io.restassured.internal.ResponseSpecificationImpl.body(ResponseSpecificationImpl.groovy:270)
        at io.restassured.specification.ResponseSpecification$body$0.callCurrent(Unknown Source)
        at io.restassured.internal.ResponseSpecificationImpl.body(ResponseSpecificationImpl.groovy:117)
        at io.restassured.internal.ValidatableResponseOptionsImpl.body(ValidatableResponseOptionsImpl.java:244)
        at com.demo.b_DifferentWaysToPostJSON.postUsingPOJO(b_DifferentWaysToPostJSON.java:137)
        at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
        at java.base/java.lang.reflect.Method.invoke(Method.java:565)
        at org.testng.internal.invokers.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:141)
        at org.testng.internal.invokers.TestInvoker.invokeMethod(TestInvoker.java:686)
        at org.testng.internal.invokers.TestInvoker.invokeTestMethod(TestInvoker.java:230)
        at org.testng.internal.invokers.MethodRunner.runInSequence(MethodRunner.java:63)
        at org.testng.internal.invokers.TestInvoker$MethodInvocationAgent.invoke(TestInvoker.java:992)
        at org.testng.internal.invokers.TestInvoker.invokeTestMethods(TestInvoker.java:203)
        at org.testng.internal.invokers.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:154)
        at org.testng.internal.invokers.TestMethodWorker.run(TestMethodWorker.java:134)
        at java.base/java.util.ArrayList.forEach(ArrayList.java:1612)
        at org.testng.TestRunner.privateRun(TestRunner.java:739)
        at org.testng.TestRunner.run(TestRunner.java:614)
        at org.testng.SuiteRunner.runTest(SuiteRunner.java:421)
        at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:413)
        at org.testng.SuiteRunner.privateRun(SuiteRunner.java:373)
        at org.testng.SuiteRunner.run(SuiteRunner.java:312)
        at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
        at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:95)
        at org.testng.TestNG.runSuitesSequentially(TestNG.java:1274)
        at org.testng.TestNG.runSuitesLocally(TestNG.java:1208)
        at org.testng.TestNG.runSuites(TestNG.java:1112)
        at org.testng.TestNG.run(TestNG.java:1079)
        at org.apache.maven.surefire.testng.TestNGExecutor.run(TestNGExecutor.java:155)
        at org.apache.maven.surefire.testng.TestNGDirectoryTestSuite.executeSingleClass(TestNGDirectoryTestSuite.java:102)
        at org.apache.maven.surefire.testng.TestNGDirectoryTestSuite.execute(TestNGDirectoryTestSuite.java:91)
        at org.apache.maven.surefire.testng.TestNGProvider.invoke(TestNGProvider.java:137)
        at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:385)
        at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:162)
        at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:507)
        at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:495)

[INFO] 
[INFO] Results:
[INFO] 
[ERROR] Failures: 
[ERROR]   b_DifferentWaysToPostJSON.postUsingPOJO:137 1 expectation failed.
JSON path data.'CPU model' doesn't match.
Expected: M5 Extreme
  Actual: null

[INFO] 
[ERROR] Tests run: 5, Failures: 1, Errors: 0, Skipped: 0
[INFO] 
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  13.957 s
[INFO] Finished at: 2026-06-10T10:45:43+05:30
[INFO] ------------------------------------------------------------------------
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.5.4:test (default-test) on project RestAssuredDemo: There are test failures.
[ERROR] 
[ERROR] See /Users/sarthakbansal/RestAssured/target/surefire-reports for the individual test results.
[ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
sarthakbansal@PWHNJJ47NN26 RestAssured % 