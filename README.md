# Introduction

Example project for a bug report to https://github.com/serenity-bdd/serenity-core/ that produces lots of errors during parallel execution of cucumber scenarios.

# Build

```bash
mvn clean install
```

# Errors

**java.lang.NullPointerException: No BaseStepListener has been registered**
```log
java.lang.NullPointerException: No BaseStepListener has been registered
	at com.google.common.base.Preconditions.checkNotNull(Preconditions.java:787)
	at net.thucydides.core.steps.StepEventBus.getBaseStepListener(StepEventBus.java:131)
	at net.thucydides.core.steps.StepEventBus.addDescriptionToCurrentTest(StepEventBus.java:600)
	at cucumber.runtime.formatter.SerenityReporter.startScenario(SerenityReporter.java:556)
	at cucumber.runtime.formatter.SerenityReporter.startOfScenarioLifeCycle(SerenityReporter.java:547)
	at cucumber.runtime.formatter.SerenityReporter.handleTestCaseStarted(SerenityReporter.java:219)
	at cucumber.runtime.formatter.SerenityReporter.lambda$new$1(SerenityReporter.java:112)
	at cucumber.runner.EventBus.send(EventBus.java:28)
	at cucumber.api.TestCase.run(TestCase.java:55)
	at cucumber.runner.Runner.runPickle(Runner.java:80)
	at cucumber.runtime.junit.PickleRunners$NoStepDescriptions.run(PickleRunners.java:140)
	at cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:68)
	at cucumber.runtime.junit.FeatureRunner.runChild(FeatureRunner.java:23)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at cucumber.runtime.junit.FeatureRunner.run(FeatureRunner.java:73)
	at cucumber.api.junit.Cucumber.runChild(Cucumber.java:118)
	at cucumber.api.junit.Cucumber.runChild(Cucumber.java:56)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.apache.maven.surefire.junitcore.pc.Scheduler$1.run(Scheduler.java:410)
	at org.apache.maven.surefire.junitcore.pc.InvokerStrategy.schedule(InvokerStrategy.java:54)
	at org.apache.maven.surefire.junitcore.pc.Scheduler.schedule(Scheduler.java:367)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at cucumber.api.junit.Cucumber$1.evaluate(Cucumber.java:127)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runners.Suite.runChild(Suite.java:128)
	at org.junit.runners.Suite.runChild(Suite.java:27)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.apache.maven.surefire.junitcore.pc.Scheduler$1.run(Scheduler.java:410)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
	at java.lang.Thread.run(Thread.java:748)
```

**java.util.ConcurrentModificationException**
```log
java.util.ConcurrentModificationException
```

# Workaround

Change
```xml
<parallelScheme>SCENARIO</parallelScheme>
```
to
```xml
<parallelScheme>FEATURE</parallelScheme>
```
to execute only features in parallel.
