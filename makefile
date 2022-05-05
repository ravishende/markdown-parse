MarkdownParse.class: MarkdownParse.java
	javac MarkdownParse.java
MarkdownParseTest.class: MarkdownParseTest.java
	javac -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:. MarkdownParseTest.java
test: MarkdownParseTest.class MarkdownParse.class
	java -cp libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar:. org.junit.runner.JUnitCore MarkdownParseTest