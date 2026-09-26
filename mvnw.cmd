@echo off
setlocal
set "MAVEN_BIN=%USERPROFILE%\.m2\wrapper\dists\apache-maven-3.9.6\apache-maven-3.9.6\bin\mvn.cmd"
if exist "%MAVEN_BIN%" (
    call "%MAVEN_BIN%" %*
) else (
    mvn %*
)
endlocal
