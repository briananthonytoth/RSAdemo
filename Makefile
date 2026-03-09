JAVAC  = javac
JAVA   = java
JAR    = jar
OUTDIR = out

.PHONY: all package run clean

all:
	$(JAVAC) -d $(OUTDIR) --module-source-path src $(shell find src -name "*.java")

package: all
	$(JAR) --create --file Lab3.jar --main-class Lab3.Lab3 --module-version 1.0 -C $(OUTDIR)/Lab3 .

run: package
	$(JAVA) -jar Lab3.jar

clean:
	rm -rf $(OUTDIR) Lab3.jar
