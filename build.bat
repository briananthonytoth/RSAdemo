@echo off
if not exist out mkdir out
javac -d out --module-source-path src src\Lab3\module-info.java src\Lab3\Lab3.java
if %errorlevel% neq 0 (
    echo Build failed.
    pause
    exit /b %errorlevel%
)
jar --create --file Lab3.jar --main-class Lab3.Lab3 --module-version 1.0 -C out\Lab3 .
if %errorlevel% neq 0 (
    echo JAR packaging failed.
    pause
    exit /b %errorlevel%
)
echo Build successful.
