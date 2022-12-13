# ANTLR4-Calculator
Java program using ANTLR Visitor class for Build AST(Abstract Syntax Tree)

## Prerequisite

```
$ sudo apt update
$ sudo apt upgrade
$ sudo apt install default-jre
$ sudo apt install default-jdk
$ sudo apt install curl

$ cd /usr/local/lib
$ sudo curl -0 https://www.antlr.org/download/antlr-4.9.2-complete.jar -o antlr-4.9.2-complete.jar
$ sudo ln -s antlr-4.9.2-complete.jar antlr-complete.jar

$ vi ~/.bashrc
export CLASSPATH='.:/usr/local/lib/antlr-complete.jar:$CLASSPATH'
alias antlr4='java -jar /usr/local/lib/antlr-complete.jar'
alias grun='java org.antlr.v4.gui.TestRig'

$ source ~/.bashrc
```

## Brief Explanation
- Expr.g4: Grammar file, define tokens and BNF rules
- AstNodes.java: Define classes to represent AST nodes
- BuildAstVisitor.java: Define class that builds AST
- AstCall.java: Methods to print the AST nodes
- Evaluate.java:Methods to calculate the input-expression

## How to Run
```
$ javac *.java
$ java program
a=3; a+pow(3,2); a/2 + (2*3+5-2*1);
```
