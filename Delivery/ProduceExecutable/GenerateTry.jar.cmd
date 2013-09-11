del *.class
del *.jar
rmdir TouchMoreLowLevel /s /q
rmdir TouchMoreHighLevelTry /s /q
7za x -y ..\Package\TouchMoreLowLevel.7z -oTouchMoreLowLevel
7za x -y ..\Package\TouchMoreHighLevelTry.7z -oTouchMoreHighLevelTry
javac -sourcepath TouchMoreLowLevel\src\wrappers -d . TouchMoreLowLevel\src\wrappers\*.java
javac -sourcepath TouchMoreLowLevel\src\wrappers -d . TouchMoreHighLevelTry\src\*.java -cp .
jar cvfm Try.jar Manifest.txt *.class
