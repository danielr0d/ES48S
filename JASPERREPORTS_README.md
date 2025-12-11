# Configuração de JasperReports para o Projeto

## Dependências Necessárias

Para compilar e executar este projeto com suporte a relatórios, você precisa adicionar as seguintes bibliotecas JasperReports ao classpath:

### Bibliotecas Principais:
1. jasperreports-6.21.2.jar
2. commons-beanutils-1.9.4.jar
3. commons-digester-2.1.jar
4. commons-logging-1.2.jar
5. commons-collections-3.2.2.jar
6. jfreechart-1.5.3.jar
7. jcommon-1.0.24.jar
8. itext-4.2.2.jar
9. itext-pdfa-4.2.2.jar
10. itext-rtf-4.2.2.jar
11. groovy-3.0.9.jar
12. jakarta.validation-api-2.0.2.jar

### MySQL Driver:
- mysql-connector-java-8.0.33.jar (ou versão compatível)

## Estrutura de Diretórios

```
ES48S/
├── src/
│   ├── Main.java
│   ├── Controller/
│   │   ├── MainController.java
│   │   ├── ProductController.java
│   │   ├── ProductReportController.java
│   │   ├── SupplierController.java
│   │   ├── SupplierReportController.java
│   │   ├── UserController.java
│   │   └── UserReportController.java
│   ├── DAO/
│   │   ├── ProductDAO.java
│   │   ├── SupplierDAO.java
│   │   └── UserDAO.java
│   ├── Model/
│   │   ├── ProductModel.java
│   │   ├── SupplierModel.java
│   │   └── UserModel.java
│   ├── Report/
│   │   └── ReportGenerator.java
│   ├── Util/
│   │   └── JDBCUtil.java
│   └── View/
│       ├── MainView.java
│       ├── ProductReportView.java
│       ├── ProductView.java
│       ├── SupplierReportView.java
│       ├── SupplierView.java
│       ├── UserReportView.java
│       └── UserView.java
├── reports/
│   ├── ProductReport.jrxml
│   ├── SupplierReport.jrxml
│   └── UserReport.jrxml
├── database.sql
└── config.properties
```

## Compilação

### Com Maven:
Se usar Maven, adicione ao pom.xml:

```xml
<dependency>
    <groupId>net.sf.jasperreports</groupId>
    <artifactId>jasperreports</artifactId>
    <version>6.21.2</version>
</dependency>

<dependency>
    <groupId>commons-beanutils</groupId>
    <artifactId>commons-beanutils</artifactId>
    <version>1.9.4</version>
</dependency>

<dependency>
    <groupId>commons-digester</groupId>
    <artifactId>commons-digester</artifactId>
    <version>2.1</version>
</dependency>

<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>

<dependency>
    <groupId>commons-collections</groupId>
    <artifactId>commons-collections</artifactId>
    <version>3.2.2</version>
</dependency>

<dependency>
    <groupId>org.jfree</groupId>
    <artifactId>jfreechart</artifactId>
    <version>1.5.3</version>
</dependency>

<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itextpdf</artifactId>
    <version>4.2.2</version>
</dependency>

<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy</artifactId>
    <version>3.0.9</version>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

### Manualmente (sem Maven):

1. Faça download dos JARs da biblioteca JasperReports do site oficial: https://sourceforge.net/projects/jasperreports/
2. Coloque todos os JARs em uma pasta `lib/` na raiz do projeto
3. Compile com:
```bash
javac -cp lib/*:src -d bin src/**/*.java
```

4. Execute com:
```bash
java -cp lib/*:bin Main
```

## Configuração do Banco de Dados

Execute o script `database.sql` no seu servidor MySQL para criar as tabelas e dados de teste:

```bash
mysql -u root < database.sql
```

Ou no MySQL CLI:
```
mysql> source database.sql;
```

## Executando a Aplicação

### Com Java IDE (IntelliJ, Eclipse, etc.):
1. Adicione as bibliotecas JasperReports às dependências do projeto
2. Certifique-se que o diretório `reports/` está no classpath
3. Execute a classe `Main.java`

### Via Linha de Comando:
```bash
cd ES48S
javac -cp lib/*:src -d bin src/**/*.java
java -cp lib/*:bin Main
```

## Descrição dos Relatórios

### 1. Relatório de Usuários (UserReport)
- **Tipo**: Relatório com parâmetro de filtro
- **Funcionalidade**: Lista todos os usuários, com opção de filtrar por nome
- **Campo Parametrizado**: Nome do usuário (LIKE)
- **Campos Exibidos**: ID, Nome de Usuário, E-mail

### 2. Relatório de Produtos (ProductReport)
- **Tipo**: Relatório com agrupamento e variáveis de relatório
- **Funcionalidade**: Agrupa produtos por faixa de preço
- **Faixas de Preço**:
  - Baixo: < R$ 100
  - Médio: R$ 100 - R$ 500
  - Alto: R$ 500 - R$ 1.500
  - Premium: > R$ 1.500
- **Variáveis de Relatório**:
  - `totalByRange`: Soma dos preços por faixa
  - `countByRange`: Quantidade de produtos por faixa
  - `totalAll`: Soma total de todos os produtos
- **Campos Exibidos**: ID, Nome, Preço, Subtotal por faixa

### 3. Relatório de Fornecedores (SupplierReport)
- **Tipo**: Relatório com parâmetro de filtro
- **Funcionalidade**: Lista todos os fornecedores, com opção de filtrar por ID
- **Campo Parametrizado**: ID do fornecedor
- **Campos Exibidos**: ID, Nome, Telefone

## Notas Importantes

1. O diretório `reports/` deve estar no diretório de trabalho atual quando a aplicação é executada
2. As conexões com o banco de dados são gerenciadas através do `JDBCUtil`
3. Os relatórios são compilados em tempo de execução
4. A exibição dos relatórios usa o `JasperViewer` (não-modal para permitir múltiplas janelas)

