# MockSnifferTests

- This project is created to test the functionality of MockSniffer Project on different cases.
- Source code of MockSniffer can be found [here](https://github.com/henryhchchc/MockSniffer) alongside with the links to the original paper.
- Also, a summary of the MockSniffer project can be found [here](https://docs.google.com/document/d/1my1nfR7acrVHna_H-ZO6knYoweI6twsjOlN72oKugLU/edit?usp=sharing)

## Running MockSniffer
I have run the code through the instructions of the authors on different projects, and some results and challenges are mentioned here.
<br/> Before talking about the different projects and results, I want to point to some errors that you may run into while going through different steps of MockSniffer:

### Build Problems
Fortunately, there was no problem for me during the building of MockSniffer itself, but building other projects sometimes
is not this easy, as an example while building [pdfbox](https://github.com/apache/pdfbox) some tests failed (but I think
it was built successfully). You are on your own for struggling with old projects build files!

### Obtaining ML models
I was a little confused with <i> [obtaining the ML model](https://github.com/apache/pdfbox) </i> part of the MockSniffer
documentation at first, but it seems that you can easily download mock-sniffer.pmml file from [here](https://github.com/henryhchchc/MockSniffer/releases/tag/v1.1.0),
but I'm still not sure whether actions for training the model should be taken or not.
<br/> I just want to mention that for building MockSniffer, remember to use <b>JDK 11</b> or it will fail.


### Step 1: Extract dependency pairs from the target project
Command that I've run for pdfbox project:
```
java -jar mocksniffer.jar extract-dataset --repo ../../../pdfbox/pdfbox/ -rt /opt/jdk/jdk1.8.0_201/ -o ./dataset.csv -pp 1
```
I just wanted to mention some points that confused me and took me a little time during this step:
- You should run this command inside the target folder that is created by maven, otherwise you will get this error:
```
Error: Unable to access jarfile mocksniffer.jar
Merging results form sub-processes
Exception in thread "main" java.io.FileNotFoundException: /tmp/mocksniffer_15685210112966505684/partial_kotlin.random.XorWowRandom@29ca901e.csv (No such file or directory)
	at java.base/java.io.FileInputStream.open0(Native Method)
	at java.base/java.io.FileInputStream.open(FileInputStream.java:219)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:157)
	at net.henryhc.mocksniffer.prediction.dependencyresolving.ExtractTuples.run(ExtractTuples.kt:53)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:139)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:14)
	at com.github.ajalt.clikt.core.CliktCommand.parse(CliktCommand.kt:215)
	at com.github.ajalt.clikt.core.CliktCommand.parse$default(CliktCommand.kt:212)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:230)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:253)
	at net.henryhc.mocksniffer.MainKt.main(Main.kt:44)
```
- Be aware that you SHOULD use <b> JDK 8 </b>, or at least I know that JDK 11 will not work for you. If you run with JDK 11
- you will get the following error:
```
Exception in thread "main" java.lang.RuntimeException: None of the basic classes could be loaded! Check your Soot class path!
	at soot.Scene.loadBasicClasses(Scene.java:1718)
	at soot.Scene.loadNecessaryClasses(Scene.java:1807)
	at soot.Main.run(Main.java:241)
	at net.henryhc.mocksniffer.prediction.dependencyresolving.ExtractTuplesSingleProject.run(ExtractTuplesSingleProject.kt:31)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:139)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:14)
	at com.github.ajalt.clikt.core.CliktCommand.parse(CliktCommand.kt:215)
	at com.github.ajalt.clikt.core.CliktCommand.parse$default(CliktCommand.kt:212)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:230)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:253)
	at net.henryhc.mocksniffer.MainKt.main(Main.kt:44)
```

### Step 2: Extract features of the dependency pairs
Command that I've run for pdfbox project:
```
java -jar mocksniffer.jar extract-prediction-input --repo ../../../pdfbox/pdfbox/ --dataset ./dataset.csv -rt /opt/jdk/jdk1.8.0_201/ -o ./prediction_inp.csv -pp 1 -cgexp 1
```
- Notice that the command mentioned in the [documentation](https://github.com/henryhchchc/MockSniffer/tree/master/MockSniffer#step-2-extract-features-of-the-dependency-pairs)
  is not valid. I think it is for the previous versions or something. Command extract-prediction-input made sense and it worked for me.
- Also inputs of this command are not correct in the doc. there is an additional input <i>cgexp</i> that is not mentioned in the doc.

### Step 3: Run the prediction process
Command that I've run for pdfbox project:
```
java -jar mocksniffer.jar batch-predict --input ./prediction_inp.csv --model ../../mock-sniffer.pmml -o ./prediction.csv
```
- This command does not need --repo, which is mentioned as an input of this command in the [documentation](https://github.com/henryhchchc/MockSniffer/tree/master/MockSniffer#step-3-run-the-prediction-process)
- There is a little mistake in the naming of the input.csv columns. when you run the command first you will get this error:
```
Exception in thread "main" java.lang.IllegalArgumentException: java.lang.IllegalArgumentException: Mapping for ORD not found, expected one of [CUT, D, DORD, ABS, INT, JDK, ICB, DEP, TDEP, FIELD, UAPI, TUAPI, UINT, SYNC, CALLSITES, AFPR, RBFA, EXPCAT, CONDCALL]
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:490)
	at java.base/java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:600)
	at java.base/java.util.concurrent.ForkJoinTask.reportException(ForkJoinTask.java:678)
	at java.base/java.util.concurrent.ForkJoinTask.invoke(ForkJoinTask.java:737)
	at java.base/java.util.stream.ReduceOps$ReduceOp.evaluateParallel(ReduceOps.java:919)
	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:233)
	at java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)
	at net.henryhc.mocksniffer.prediction.PredictionCommand.run(PredictionCommand.kt:53)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:139)
	at com.github.ajalt.clikt.parsers.Parser.parse(Parser.kt:14)
	at com.github.ajalt.clikt.core.CliktCommand.parse(CliktCommand.kt:215)
	at com.github.ajalt.clikt.core.CliktCommand.parse$default(CliktCommand.kt:212)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:230)
	at com.github.ajalt.clikt.core.CliktCommand.main(CliktCommand.kt:253)
	at net.henryhc.mocksniffer.MainKt.main(Main.kt:44)

```
I have fixed this issue by changing the column name "DORD" to "ORD" in the prediction_inp.csv file. but also another column name
(DEP) was wrong and it was used for one of the features instead of dependencies. So I changed the code in order to use "D"
instead of "DEP"
