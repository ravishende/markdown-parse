MarkdownParse.class: MarkdownParse.java
	javac -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:. MarkdownParse.java
MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:. MarkdownParseTest.java
test: MarkdownParseTest.class
	java -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MarkdownParseTest