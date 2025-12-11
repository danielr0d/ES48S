#!/bin/bash

# Script de teste e validação do projeto ES48S

echo "=========================================="
echo "Validação do Projeto ES48S"
echo "=========================================="
echo ""

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Função para verificar arquivo
check_file() {
    if [ -f "$1" ]; then
        echo -e "${GREEN}✓${NC} Arquivo encontrado: $1"
        return 0
    else
        echo -e "${RED}✗${NC} Arquivo NÃO encontrado: $1"
        return 1
    fi
}

# Função para verificar diretório
check_dir() {
    if [ -d "$1" ]; then
        echo -e "${GREEN}✓${NC} Diretório encontrado: $1"
        return 0
    else
        echo -e "${RED}✗${NC} Diretório NÃO encontrado: $1"
        return 1
    fi
}

echo ""
echo "=== Verificando Estrutura de Arquivos ==="
echo ""

# Verificar arquivos Java principais
echo "Arquivos Java (Main e Pacotes):"
check_file "src/Main.java"

echo ""
echo "Pacote Controller:"
check_file "src/Controller/MainController.java"
check_file "src/Controller/UserController.java"
check_file "src/Controller/ProductController.java"
check_file "src/Controller/SupplierController.java"
check_file "src/Controller/UserReportController.java"
check_file "src/Controller/ProductReportController.java"
check_file "src/Controller/SupplierReportController.java"

echo ""
echo "Pacote View:"
check_file "src/View/MainView.java"
check_file "src/View/UserView.java"
check_file "src/View/ProductView.java"
check_file "src/View/SupplierView.java"
check_file "src/View/UserReportView.java"
check_file "src/View/ProductReportView.java"
check_file "src/View/SupplierReportView.java"

echo ""
echo "Pacote Model:"
check_file "src/Model/UserModel.java"
check_file "src/Model/ProductModel.java"
check_file "src/Model/SupplierModel.java"

echo ""
echo "Pacote DAO:"
check_file "src/DAO/UserDAO.java"
check_file "src/DAO/ProductDAO.java"
check_file "src/DAO/SupplierDAO.java"

echo ""
echo "Pacote Report e Util:"
check_file "src/Report/ReportGenerator.java"
check_file "src/Util/JDBCUtil.java"

echo ""
echo "=== Verificando Arquivos de Relatório (JRXML) ==="
echo ""
check_file "reports/UserReport.jrxml"
check_file "reports/ProductReport.jrxml"
check_file "reports/SupplierReport.jrxml"

echo ""
echo "=== Verificando Arquivos de Configuração ==="
echo ""
check_file "database.sql"
check_file "config.properties"
check_file "pom.xml"
check_file "build.xml"
check_file "setup-jasperreports.sh"

echo ""
echo "=== Verificando Arquivos de Documentação ==="
echo ""
check_file "IMPLEMENTACAO_COMPLETA.md"
check_file "JASPERREPORTS_README.md"
check_file "SUMARIO_IMPLEMENTACAO.md"
check_file "README_NOVO.md"

echo ""
echo "=== Verificando Diretórios ==="
echo ""
check_dir "src"
check_dir "src/Controller"
check_dir "src/View"
check_dir "src/Model"
check_dir "src/DAO"
check_dir "src/Report"
check_dir "src/Util"
check_dir "reports"

echo ""
echo "=========================================="
echo "Contagem de Arquivos"
echo "=========================================="
echo ""

JAVA_FILES=$(find src -name "*.java" | wc -l)
JRXML_FILES=$(find reports -name "*.jrxml" 2>/dev/null | wc -l)

echo "Arquivos Java (.java): $JAVA_FILES"
echo "Arquivos de Relatório (.jrxml): $JRXML_FILES"

echo ""
echo "=========================================="
echo "Verificação de Sintaxe (Preview)"
echo "=========================================="
echo ""

if command -v javac &> /dev/null; then
    echo -e "${YELLOW}Nota: Compilação completa requer dependências JasperReports${NC}"
    echo "Execute './setup-jasperreports.sh' para instalar dependências"
    echo ""
    echo "Depois compile com:"
    echo "  javac -cp \"lib/*:src\" -d bin src/**/*.java"
    echo "  ou"
    echo "  ant compile"
else
    echo -e "${YELLOW}Java compiler (javac) não encontrado${NC}"
fi

echo ""
echo "=========================================="
echo "Resumo"
echo "=========================================="
echo ""
echo "Arquivos Java: $JAVA_FILES"
echo "Relatórios JRXML: $JRXML_FILES"
echo ""
echo "Próximos passos:"
echo "1. Execute: ./setup-jasperreports.sh"
echo "2. Execute: mysql -u root -p < database.sql"
echo "3. Compile: ant compile"
echo "4. Execute: ant run"
echo ""
echo "Ou use Maven:"
echo "1. mvn clean install"
echo "2. mvn exec:java@run"
echo ""

