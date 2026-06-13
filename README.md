# Cat Center

Sistema web para gerenciamento de um abrigo de gatos, desenvolvido com Spring Boot, Thymeleaf e PostgreSQL.

## Sobre o projeto

O Cat Center é uma aplicação voltada para a **administração de um abrigo felino**. Esta versão funciona como um painel administrativo completo, onde a equipe do abrigo pode gerenciar gatos, adotantes e adoções — cadastrando, editando, removendo e acompanhando o status de cada processo.

A aplicação está disponível em: **https://cat-center.onrender.com/**

## Banco de dados

A aplicação utiliza **3 tabelas** no PostgreSQL, hospedado no próprio Render:

| Tabela | Descrição |
|---|---|
| `gatos` | Cadastro de todos os gatos do abrigo, com nome, sexo, faixa etária, cor, pelagem, castração, disponibilidade e foto |
| `adotantes` | Cadastro das pessoas que adotaram ou demonstraram interesse em adotar, com nome, CPF, CEP e telefone |
| `adocoes` | Registro das adoções, relacionando um gato a um adotante, com data e status (PENDENTE, APROVADA ou CANCELADA) |

## Funcionalidades

### Gatos
- Listar todos os gatos com ID único, nome, sexo, faixa etária, cor, status de castração e disponibilidade
- Cadastrar novo gato com upload de foto (armazenada no Cloudinary)
- Editar dados e foto de um gato existente
- Remover gato do cadastro
- Disponibilidade atualizada automaticamente conforme adoções são criadas, canceladas ou removidas

### Adotantes
- Listar todos os adotantes com ID único e dados de contato
- Cadastrar, editar e remover adotantes
- Ao remover um adotante, suas adoções são removidas automaticamente e os gatos vinculados voltam a ficar disponíveis

### Adoções
- Listar todas as adoções com nome do gato, adotante, data e status
- Registrar nova adoção selecionando gato (`#ID — Nome`) e adotante — data preenchida automaticamente com o dia atual, status inicia como PENDENTE
- Alterar o status de uma adoção (PENDENTE, APROVADA ou CANCELADA)
- Ao cancelar ou deletar uma adoção, o gato volta automaticamente a ficar disponível

## Tecnologias

- **Java 21** + **Spring Boot 4**
- **Spring MVC** + **Thymeleaf**
- **Spring JDBC** (JdbcTemplate, sem ORM)
- **PostgreSQL** — banco hospedado no Render
- **Cloudinary** — armazenamento das fotos dos gatos
- **Render** — deploy e hospedagem da aplicação

## Como rodar localmente

1. Tenha o PostgreSQL rodando com um banco chamado `catshelter`
2. Configure as variáveis de ambiente ou ajuste o `application.yaml`:
   ```
   SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/catshelter
   SPRING_DATASOURCE_USERNAME=seu_usuario
   SPRING_DATASOURCE_PASSWORD=sua_senha
   ```
3. Execute:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Acesse `http://localhost:8080`

O schema das tabelas é criado automaticamente na primeira execução via `schema-postgresql.sql`.
