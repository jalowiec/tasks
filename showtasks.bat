call runcrud.bat
echo Uruchamianie runcrud.bat
if "%ERRORLEVEL%" == "0" goto runbrowser
echo.
echo RUNCRUD has errors - breaking work
goto fail

:runbrowser
echo RUNFIREFOX
start chrome http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto openpage
echo.
echo RUNFIREFOX has errors - breaking work
goto fail


:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.