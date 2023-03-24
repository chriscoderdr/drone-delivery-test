:: compile.bat
@echo off
set SRC=src\main\java
set OUT=out

:: Compile all .java files in SRC and its subdirectories
for /r %SRC% %%f in (*.java) do (
  javac -d %OUT% "%%f"
)

:: Run the Main class
java -classpath "%OUT%" com.velozient.dronedelivery.Main %*
