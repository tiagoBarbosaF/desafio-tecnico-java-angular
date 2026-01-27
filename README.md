# 📌 Desafio Técnico – Backend (com Frontend Angular)

Este projeto consiste em uma aplicação fullstack composta por uma API REST desenvolvida em Java + Spring Boot e um frontend em Angular, responsável por consultar créditos fiscais a partir de dados previamente armazenados em banco de dados relacional.

A aplicação foi construída seguindo boas práticas de arquitetura, testes automatizados, versionamento de banco com Flyway e documentação via Swagger/OpenAPI.

Além da API, o projeto conta com um frontend desenvolvido em Angular, responsável por consumir os endpoints expostos e permitir a consulta de créditos fiscais de forma visual e interativa.


### 🛠️ Tecnologias Utilizadas

#### Backend
- Java 25
- Spring Boot 4
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway (versionamento de banco)
- Swagger / OpenAPI (springdoc-openapi)
- JUnit 5 + Mockito
- Gradle (multi-módulos)

#### Frontend
- Angular
- TypeScript
- HTML5 / CSS3
- Signals (estado reativo)
- Docker (build e execução)

#### Infraestrutura
- Docker
- Docker Compose


### 📐 Arquitetura

O projeto segue uma arquitetura em camadas bem definida:
- Controller → Exposição dos endpoints REST
- Service → Regras de negócio
- Repository → Acesso a dados (JPA)
- DTOs → Objetos de transferência
- Exceptions customizadas
- Testes unitários e de controller

Além disso:
- Migrations de banco são gerenciadas via Flyway
- Configurações externas via variáveis de ambiente
- Containers isolados por responsabilidade (backend / banco)


### 🖥️ Frontend

O frontend se comunica com o backend via requisições HTTP REST, utilizando os endpoints documentados no Swagger.

O frontend foi desenvolvido em Angular e tem como objetivo consumir a API de créditos fiscais, permitindo:

- Consulta por número da NFS-e
- Consulta por número do crédito
- Visualização dos resultados em tabela
- Tratamento de cenários sem resultados

A aplicação utiliza recursos modernos do Angular, como Signals, garantindo renderização reativa e previsível.

### 🗄️ Banco de Dados
- Banco: PostgreSQL
- Criação de tabelas e carga inicial de dados realizadas via Flyway
- Scripts localizados em:

```
backend/src/main/resources/db/migration
```

Exemplo:
- `V1__create_credito_table.sql`
- `V2__insert_initial_data.sql`


### ▶️ Como Executar o Projeto (1 comando)

Pré-requisitos
- Docker
- Docker Compose

Não é necessário ter Java ou PostgreSQL instalados localmente.


### 🚀 Subir toda a aplicação

Na raiz do projeto, execute:
```
docker-compose up --build
```

Esse comando irá:
- Subir o PostgreSQL
- Buildar e iniciar o backend
- Buildar e iniciar o frontend Angular
- Aplicar as migrations do Flyway

Após a inicialização:

- Frontend: http://localhost:4200
- Backend (API): http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html


### 🌐 Endpoints disponíveis

- Swagger UI

http://localhost:8080/swagger-ui.html

#### 🔗 Exemplos de Consumo da API (cURL)

- Consultar créditos por número da NFS-e
```bash
curl --request GET \
  --url http://localhost:8080/api/creditos/7891011
```

- Consultar crédito por número do crédito
```bash
curl --request GET \
  --url http://localhost:8080/api/creditos/credito/123456
```


### 📄 Documentação da API (Swagger)

A API é documentada utilizando springdoc-openapi.
- Interface Swagger:

`/swagger-ui.html`


- Especificação OpenAPI:

`/v3/api-docs`

A documentação foi configurada de forma isolada, evitando poluir os controllers.


### 🧪 Testes

O projeto possui testes automatizados para:
- Service
- Controller
- Cenários de sucesso
- Cenários de erro
- Exceções customizadas

Para rodar os testes localmente (fora do Docker):
```
./gradlew clean test
```

### ⚠️ Observações Importantes
- A aplicação utiliza variáveis de ambiente para configuração do datasource
- Em ambiente Docker, o backend se conecta ao banco usando o hostname do serviço (postgres)
- O Flyway roda automaticamente na inicialização da aplicação
- O projeto está preparado para execução local, Docker e CI/CD


### ✅ Checklist de Requisitos Atendidos
- ✔ API REST funcional
- ✔ Consulta por número de crédito
- ✔ Consulta por número de NFS-e
- ✔ Tratamento de exceções
- ✔ Testes automatizados
- ✔ Versionamento de banco com Flyway
- ✔ Documentação Swagger
- ✔ Execução com único comando
- ✔ Docker Compose configurado corretamente
- ✔ Frontend Angular integrado à API
- ✔ Aplicação fullstack executável com um único comando


### 👤 Autor

Tiago Barbosa  
Desenvolvedor Java | Backend & Fullstack
