javac $(find .* | grep .java) -d out/
java -classpath "out" com.velozient.Main