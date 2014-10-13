REM You can check whether listed params took effect starting c:\Program Files (x86)\Java\jdk1.7.0_07\bin\jvisualvm.exe at tab JVM argumnets
REM use any jar with entry point set up 
java -Xms3m -Xmx12m -Xmn1m -XX:PermSize=20m -XX:MaxPermSize=20m -XX:+UseParallelGC -jar lib.jar
pause