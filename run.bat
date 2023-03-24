@echo off
setlocal enabledelayedexpansion
set "classpath=out"

rem compile Java files
for /f "delims=" %%f in ('dir /b /s *.java') do (
  javac "%%f" -d "out/"
  set "classpath=!classpath!;out/"
)

rem run Main class with arguments
java -classpath "!classpath!" com.velozient.dronedelivery.Main %*
