@echo off

rem ɾ���Ѿ����ɵ�����
pushd gen
if exist cn rd /s /q cn
if not errorlevel 0 goto err
if exist mapper_xml rd /s /q mapper_xml
if not errorlevel 0 goto err
popd

rem PACKAGE
call mvn package
if not errorlevel 0 goto err

rem ����
call mvn exec:exec
if not errorlevel 0 goto err


rem ���Ƶ�commons
                             
copy ..\hugedata-spark-codegen\gen\cn\com\hugedata\spark\commons\storage\mapper\*Mapper.java  ..\hugedata-spark-commons\src\main\java\cn\com\hugedata\spark\commons\storage\mapper
if not errorlevel 0 goto err
copy ..\hugedata-spark-codegen\gen\cn\com\hugedata\spark\commons\storage\entity\*.*           ..\hugedata-spark-commons\src\main\java\cn\com\hugedata\spark\commons\storage\entity
if not errorlevel 0 goto err
copy ..\hugedata-spark-codegen\gen\mapper_xml\*.*                                             ..\hugedata-spark-commons\src\main\resources\mapper_xml\
if not errorlevel 0 goto err

pause
goto end


rem ����
:err
echo Failed to generate mappers

rem ����
:end

rem ɾ���Ѿ����ɵ�����
pushd gen
if exist cn rd /s /q cn
if not errorlevel 0 goto err
if exist mapper_xml rd /s /q mapper_xml
if not errorlevel 0 goto err
popd
