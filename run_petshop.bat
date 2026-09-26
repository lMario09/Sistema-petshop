@echo off
:: Script para rodar o Sistema Petshop com todas as dependências corretas
cd C:\Users\luism\codes\Java\Java Maven\sistema-petshop

:: Verificar se o JAR existe
if not exist target\sistema-petshop-1.0-SNAPSHOT.jar (
    echo.
    echo "Gerando o JAR com Maven..."
    call mvnw.bat clean package -q 2>nul
)

:: Verificar se o Maven Wrapper existe
if not exist apache-maven-3.9.6 (
    echo.
    echo "Baixando Maven Wrapper..."
    powershell -Command "iwr https://repo1.maven.org/maven2/org/apache/maven/apache-maven/3.9.6/apache-maven-3.9.6-bin.zip -OutFile \"C:\temp\maven.zip\""
    powershell -Command "Expand-Archive -Path \"C:\temp\maven.zip\" -DestinationPath ."
)

:: Rodar o sistema com classpath correto
echo.
echo "=== INICIANDO SISTEMA PETSHOP ==="
echo.
java -cp "target\classes;src\main\resources" br.edu.ifpi.Principal.Main
echo.
echo "=== SISTEMA ENCERRADO ==="