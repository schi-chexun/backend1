@echo off
cd /d "E:\转移备用\backend1\backend"
set JAVA_HOME=D:\Users\jdk-17
set PATH=%JAVA_HOME%\bin;%PATH%
set MAVEN_HOME=D:\Users\apache-maven-3.9.12
set PATH=%MAVEN_HOME%\bin;%PATH%
echo 启动EasyActivity校园活动管理系统...
echo 访问地址: http://localhost:8080/api
echo.
mvn spring-boot:run -DskipTests
pause
