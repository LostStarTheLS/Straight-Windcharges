@echo off
setlocal
set "APP_HOME=%~dp0"
set "GRADLE_VERSION=9.8.2"
if not defined GRADLE_USER_HOME set "GRADLE_USER_HOME=%USERPROFILE%\.gradle"
set "DIST_ROOT=%GRADLE_USER_HOME%\wrapper\dists\gradle-%GRADLE_VERSION%-bin\straight-windcharges"
set "GRADLE_BIN=%DIST_ROOT%\gradle-%GRADLE_VERSION%\bin\gradle.bat"
set "ZIP=%DIST_ROOT%\gradle-%GRADLE_VERSION%-bin.zip"

if not exist "%GRADLE_BIN%" (
    if not exist "%DIST_ROOT%" mkdir "%DIST_ROOT%"
    echo Downloading Gradle %GRADLE_VERSION%...
    powershell -NoProfile -ExecutionPolicy Bypass -Command "$ProgressPreference = 'SilentlyContinue'; Invoke-WebRequest -Uri 'https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip' -OutFile '%ZIP%'; Expand-Archive -LiteralPath '%ZIP%' -DestinationPath '%DIST_ROOT%' -Force"
    if errorlevel 1 exit /b %errorlevel%
)

call "%GRADLE_BIN%" -p "%APP_HOME%" %*
exit /b %errorlevel%
