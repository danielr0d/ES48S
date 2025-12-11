#!/bin/bash

# Script para configurar JasperReports no projeto ES48S
# Este script faz download das dependências necessárias

echo "========================================"
echo "Configurando JasperReports para ES48S"
echo "========================================"

# Criar diretório lib se não existir
mkdir -p lib

echo "Baixando dependências JasperReports..."

# URLs dos JARs (usando repositório Maven central)
declare -a JARS=(
    "https://repo1.maven.org/maven2/net/sf/jasperreports/jasperreports/6.21.2/jasperreports-6.21.2.jar"
    "https://repo1.maven.org/maven2/commons-beanutils/commons-beanutils/1.9.4/commons-beanutils-1.9.4.jar"
    "https://repo1.maven.org/maven2/commons-digester/commons-digester/2.1/commons-digester-2.1.jar"
    "https://repo1.maven.org/maven2/commons-logging/commons-logging/1.2/commons-logging-1.2.jar"
    "https://repo1.maven.org/maven2/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar"
    "https://repo1.maven.org/maven2/org/jfree/jfreechart/1.5.3/jfreechart-1.5.3.jar"
    "https://repo1.maven.org/maven2/org/jfree/jcommon/1.0.24/jcommon-1.0.24.jar"
    "https://repo1.maven.org/maven2/com/itextpdf/itextpdf/4.2.2/itextpdf-4.2.2.jar"
    "https://repo1.maven.org/maven2/org/codehaus/groovy/groovy/3.0.9/groovy-3.0.9.jar"
    "https://repo1.maven.org/maven2/jakarta/validation/jakarta.validation-api/2.0.2/jakarta.validation-api-2.0.2.jar"
    "https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar"
)

# Baixar cada JAR
for jar in "${JARS[@]}"; do
    filename=$(basename "$jar")
    if [ -f "lib/$filename" ]; then
        echo "✓ $filename já existe"
    else
        echo "Baixando $filename..."
        if command -v wget &> /dev/null; then
            wget -q -O "lib/$filename" "$jar"
        elif command -v curl &> /dev/null; then
            curl -s -o "lib/$filename" "$jar"
        else
            echo "✗ wget ou curl não encontrado. Faça o download manualmente."
            exit 1
        fi
        if [ -f "lib/$filename" ]; then
            echo "✓ $filename baixado com sucesso"
        else
            echo "✗ Erro ao baixar $filename"
            exit 1
        fi
    fi
done

echo ""
echo "========================================"
echo "Configuração concluída com sucesso!"
echo "========================================"
echo ""
echo "Para compilar e executar:"
echo "  javac -cp lib/*:src -d bin src/**/*.java"
echo "  java -cp lib/*:bin Main"
echo ""
echo "Ou use o build.xml com Ant:"
echo "  ant compile"
echo "  ant run"

