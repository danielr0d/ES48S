# Aplicação Desktop ES48S - Gerenciamento com JDBC

## Descrição
Aplicação desktop desenvolvida em Java com interface gráfica (Swing) para gerenciamento de Usuários (Clientes), Produtos e Fornecedores com operações CRUD completas usando JDBC e padrão de projeto MVC com DAO.

## Arquitetura

### Padrões Implementados
- **MVC (Model-View-Controller)**: Separação clara entre dados, interface e lógica
- **DAO (Data Access Object)**: Abstração para acesso ao banco de dados
- **PreparedStatement**: Usado em todas as operações SQL para segurança

### Estrutura de Pacotes
```
src/
├── Main.java                 # Ponto de entrada da aplicação
├── Controller/               # Controladores MVC
│   ├── UserController.java
│   ├── ProductController.java
│   └── SupplierController.java
├── Model/                    # Modelos de dados
│   ├── UserModel.java
│   ├── ProductModel.java
│   └── SupplierModel.java
├── View/                     # Interfaces gráficas Swing
│   ├── UserView.java
│   ├── ProductView.java
│   └── SupplierView.java
├── DAO/                      # Data Access Objects
│   ├── UserDAO.java
│   ├── ProductDAO.java
│   └── SupplierDAO.java
└── Util/                     # Utilitários
    └── JDBCUtil.java         # Gerenciador de conexões JDBC
```

## Requisitos

### Software Necessário
- Java Development Kit (JDK) 8 ou superior
- MySQL Server 5.7 ou superior
- MySQL JDBC Driver (mysql-connector-java)

### Instalação do Driver JDBC
1. Baixe o MySQL Connector/J de: https://dev.mysql.com/downloads/connector/j/
2. Coloque o arquivo `.jar` no classpath do seu projeto
3. Ou adicione ao arquivo `.iml` do projeto IntelliJ

## Configuração do Banco de Dados

### Passo 1: Criar o banco de dados
Execute o script SQL `database.sql`:
```bash
mysql -u root -p < database.sql
```

Ou copie e execute o conteúdo do arquivo `database.sql` em um cliente MySQL (MySQL Workbench, DBeaver, etc)

### Passo 2: Configurar as credenciais (se necessário)
Edite o arquivo `src/Util/JDBCUtil.java` e ajuste:
```java
private static final String URL = "jdbc:mysql://localhost:3306/es48s_db";
private static final String USER = "root";
private static final String PASSWORD = "";
```

## Compilação e Execução

### Usando linha de comando
```bash
# Compilar
javac -d out src/**/*.java

# Executar
java -cp out:mysql-connector-java-x.x.x.jar Main
```

### Usando IntelliJ IDEA
1. Abra o projeto
2. Configure o SDK Java (File > Project Structure > Project)
3. Adicione o MySQL JDBC Driver (File > Project Structure > Libraries)
4. Execute a classe `Main.java`

## Funcionalidades

### Janela de Usuários (Clientes)
- **Incluir**: Adicionar novo usuário com nome e email
- **Alterar**: Modificar dados do usuário selecionado
- **Excluir**: Remover usuário do banco de dados
- **Consultar**: Visualizar detalhes do usuário selecionado

### Janela de Produtos
- **Incluir**: Adicionar novo produto com nome e preço
- **Alterar**: Modificar dados do produto selecionado
- **Excluir**: Remover produto do banco de dados
- **Consultar**: Visualizar detalhes do produto selecionado

### Janela de Fornecedores
- **Incluir**: Adicionar novo fornecedor com nome e telefone
- **Alterar**: Modificar dados do fornecedor selecionado
- **Excluir**: Remover fornecedor do banco de dados
- **Consultar**: Visualizar detalhes do fornecedor selecionado

## Operações CRUD

Todas as operações CRUD (Create, Read, Update, Delete) são implementadas usando:
- **PreparedStatement** para segurança contra SQL Injection
- **Try-with-resources** para gerenciamento automático de recursos
- **Tratamento de exceções** apropriado

### Exemplo de PreparedStatement
```java
String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
try (Connection conn = JDBCUtil.getConnection();
     PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, user.getUsername());
    pstmt.setString(2, user.getEmail());
    int rowsAffected = pstmt.executeUpdate();
}
```

## Dados de Teste

O arquivo `database.sql` já contém dados de teste inseridos automaticamente:

### Usuários (5 registros)
- João Silva (joao@example.com)
- Maria Santos (maria@example.com)
- Carlos Oliveira (carlos@example.com)
- Ana Costa (ana@example.com)
- Pedro Ferreira (pedro@example.com)

### Produtos (8 registros)
- Notebook Dell
- Mouse Logitech
- Teclado Mecânico
- Monitor LG 27"
- Webcam HD
- Headset Gamer
- SSD 256GB
- Memória RAM 8GB

### Fornecedores (5 registros)
- Fornecedora Eletrônica Ltda.
- Tech Solutions Brasil
- Distribuidora InfoTech
- Eletrônicos Premium
- Global Equipamentos

## Notas Importantes

1. **Segurança**: O código usa PreparedStatement em todas as operações para evitar SQL Injection
2. **Gerenciamento de Conexões**: JDBCUtil implementa try-with-resources para fechar conexões automaticamente
3. **Tratamento de Erros**: Todas as operações incluem tratamento de exceções e mensagens de feedback ao usuário
4. **Padrão MVC**: Separação clara entre camadas para facilitar manutenção e extensão

## Extensões Futuras

- Adicionar mais validações nos campos
- Implementar busca avançada
- Adicionar relatórios
- Implementar autenticação
- Adicionar histórico de alterações
- Melhorar interface visual com temas modernos

## Autor
Desenvolvido conforme especificação do curso ES48S

## Licença
Código fornecido para fins educacionais

