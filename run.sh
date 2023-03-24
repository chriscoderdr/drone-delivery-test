find src/main/java -name '*.java' -print | xargs javac -d out/
java -classpath "out" com.velozient.dronedelivery.Main "$@"