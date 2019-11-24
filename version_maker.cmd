:: Script for automatic creation of error versions

::Configuration ========================================

::Target directory for generated files
set outDir=.\release_archive\generated_versions
::Nave of directory with example seeds.
set seedDir=.\version_seeds
::Name of output filename of maven compilation process
set mavenOutFileName=UIS-1.6.0.war

:: =====================================================
:: Generating versions

::Backup current seed.xml
del .\src\main\resources\seed.bck
move .\src\main\resources\seed.xml .\src\main\resources\seed.bck

::Create output directory for error versions
mkdir %outDir%

::Creating loop
for /f %%f in ('dir /b %seedDir%') do (
 :: for every seed process build
 echo %%f
 copy .\%seedDir%\%%f .\src\main\resources\seed.xml
 mvn clean
 mvn package
 move /Y .\target\%mavenOutFileName% %outDir%\%%~nf.war
)

::Clean maven project after building
mvn clean

::Delete tmp seed file and return backup file
del .\src\main\resources\seed.xml
move .\src\main\resources\seed.bck .\src\main\resources\seed.xml
