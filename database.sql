-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS es48s_db;
USE es48s_db;

-- Tabela de Usuários (Clientes)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de Produtos
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Tabela de Fornecedores
CREATE TABLE IF NOT EXISTS suppliers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Inserção de dados de teste para Usuários
INSERT INTO users (username, email) VALUES
('joao_silva', 'joao@example.com'),
('maria_santos', 'maria@example.com'),
('carlos_oliveira', 'carlos@example.com'),
('ana_costa', 'ana@example.com'),
('pedro_ferrreira', 'pedro@example.com');

-- Inserção de dados de teste para Produtos
INSERT INTO products (name, price) VALUES
('Notebook Dell', 3500.00),
('Mouse Logitech', 85.00),
('Teclado Mecânico', 450.00),
('Monitor LG 27"', 1200.00),
('Webcam HD', 150.00),
('Headset Gamer', 250.00),
('SSD 256GB', 200.00),
('Memória RAM 8GB', 120.00);

-- Inserção de dados de teste para Fornecedores
INSERT INTO suppliers (name, phone) VALUES
('Fornecedora Eletrônica Ltda.', '(11) 3456-7890'),
('Tech Solutions Brasil', '(21) 3234-5678'),
('Distribuidora InfoTech', '(31) 2456-7890'),
('Eletrônicos Premium', '(41) 3678-9012'),
('Global Equipamentos', '(51) 2345-6789');

-- Exibição dos dados inseridos
SELECT 'Usuários:' as 'Dados de Teste';
SELECT * FROM users;

SELECT 'Produtos:' as 'Dados de Teste';
SELECT * FROM products;

SELECT 'Fornecedores:' as 'Dados de Teste';
SELECT * FROM suppliers;

