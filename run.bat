@echo off
if not exist Lab3.jar (
    echo Lab3.jar not found. Run build.bat first.
    pause
    exit /b 1
)
java -jar Lab3.jar
pause
