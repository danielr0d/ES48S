#!/bin/bash

# Script de instalação e configuração da aplicação ES48S
# Este script ajuda na configuração do banco de dados e compilação do projeto

echo "=================================="
echo "ES48S - Aplicação de Gerenciamento"
echo "Script de Instalação"
echo "=================================="
echo ""

# Verificar se o MySQL está instalado
if ! command -v mysql &> /dev/null; then
    echo "ERRO: MySQL não está instalado ou não está no PATH"
    echo "Por favor, instale o MySQL Server antes de prosseguir"
    exit 1
fi

echo "1. Configurando o banco de dados..."
echo ""

# Solicitar credenciais do MySQL
read -p "Usuário MySQL (padrão: root): " DB_USER
DB_USER=${DB_USER:-root}

read -sp "Senha do MySQL: " DB_PASSWORD
echo ""

# Criar o banco de dados
echo "Criando banco de dados e tabelas..."
mysql -u "$DB_USER" -p"$DB_PASSWORD" < database.sql

if [ $? -eq 0 ]; then
    echo "✓ Banco de dados criado com sucesso!"
else
    echo "✗ Erro ao criar o banco de dados"
    echo "Verifique suas credenciais do MySQL"
    exit 1
fi

echo ""
echo "2. Compilando o projeto..."
echo ""

# Criar diretório de saída se não existir
mkdir -p out

# Compilar o projeto
javac -d out src/**/*.java src/*.java 2>&1 | head -20

if [ $? -eq 0 ]; then
    echo "✓ Compilação realizada com sucesso!"
else
    echo "✗ Erro durante a compilação"
    echo "Verifique se o JDK está instalado e configurado corretamente"
    exit 1
fi

echo ""
echo "3. Configuração concluída!"
echo ""
echo "Para executar a aplicação, use:"
echo "  java -cp out:mysql-connector-java-x.x.x.jar Main"
echo ""
echo "Certifique-se de que o arquivo mysql-connector-java-x.x.x.jar está no mesmo diretório"
echo ""

