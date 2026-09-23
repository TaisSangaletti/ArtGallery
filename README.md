# ArtGallery 🎨

API REST para gerenciamento de uma galeria de arte.

A aplicação permite cadastrar, consultar, atualizar e excluir informações relacionadas às obras de arte da galeria.

O projeto foi desenvolvido para a **Avaliação Parcial 1 — Estruturação Inicial da API e Persistência**, envolvendo configuração do projeto, conexão com PostgreSQL, mapeamento objeto-relacional (ORM) e implementação de operações CRUD via HTTP.

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Maven
- Swagger / Springdoc OpenAPI

## Pré-requisitos

Antes de executar o projeto, é necessário ter instalado:

- Java 17 ou superior;
- PostgreSQL;
- Maven — opcional, pois o projeto possui Maven Wrapper;
- Uma IDE, como IntelliJ IDEA, Eclipse ou VS Code.

## Configuração do banco de dados

1. Crie o banco de dados no PostgreSQL:

```sql
CREATE DATABASE artgallery;
```

2. A conexão está configurada no arquivo:

```text
src/main/resources/application.properties
```

Configurações utilizadas:

- Host: `localhost`
- Porta: `5433`
- Banco de dados: `artgallery`
- Usuário: `postgres`

3. Por segurança, a senha do banco não deve ser enviada ao repositório. Crie o arquivo:

```text
src/main/resources/application-local.properties
```

Com o seguinte conteúdo:

```properties
spring.datasource.password=SUA_SENHA_DO_POSTGRES
```

4. Para que o Spring Boot carregue esse arquivo, adicione ao `application.properties`:

```properties
spring.profiles.active=local
```

5. Adicione o arquivo ao `.gitignore`, evitando o envio da senha ao GitHub:

```gitignore
src/main/resources/application-local.properties
```

6. Não é necessário criar as tabelas manualmente. O Hibernate cria ou atualiza as tabelas automaticamente ao iniciar a aplicação:

```properties
spring.jpa.hibernate.ddl-auto=update
```

## Como executar o projeto

Clone o repositório:

```bash
git clone https://github.com/TaisSangaletti/ArtGallery.git
```

Entre na pasta do projeto:

```bash
cd ArtGallery
```

Execute a aplicação com o Maven Wrapper.

### Windows

```bash
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

A aplicação será iniciada normalmente no endereço:

```text
http://localhost:8080
```

## Documentação da API

Com a aplicação em execução, a documentação interativa da API pode ser acessada pelo Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

## Funcionalidades

- [X] Cadastrar obra de arte;
- [X] Listar obras de arte;
- [X] Buscar obra por ID;
- [X] Atualizar dados de uma obra;
- [X] Excluir obra de arte;
- [X] Cadastrar artistas;
- [X] Listar artistas;
- [X] Alterar artistas;
- [X] Excluir artistas;
- [X] Relacionar artistas e obras.

## Estrutura do projeto

```text
src/main/java/com/example/artgallery
│
├── ArtGalleryApplication.java     # ponto de entrada da aplicação
│
├── controllers/                   # rotas HTTP e comunicação com a API
│
├── DTO/                           # objetos de entrada e saída da API (Request/Response)
│
├── entities/                      # classes mapeadas para as tabelas do banco de dados (ORM)
│
└── repository/                    # acesso ao banco de dados com Spring Data JPA
```


## Autora

Desenvolvido por **Taís Sangaletti**.













