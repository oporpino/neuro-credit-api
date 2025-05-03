# Neurotech Credit API

## Descrição
API para avaliação de crédito para financiamento de veículos, desenvolvida como parte do desafio técnico da Neurotech.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.2.3
- Maven
- JUnit 5
- Swagger UI (OpenAPI 3)

## Funcionalidades
- Cadastro de clientes
- Avaliação de crédito para diferentes modelos de veículos (Hatch e SUV)
- Diferentes tipos de crédito (Fixo, Variável e Consignado)
- Documentação da API com Swagger UI

## Requisitos
- Java 21 ou superior
- Maven 3.8 ou superior
- Docker e Docker Compose

## Executando a Aplicação

### Com Docker Compose

1. Clone o repositório:
```bash
git clone https://github.com/seu-usuario/neuro-credit-api.git
cd neuro-credit-api
```

2. Execute o comando para iniciar a aplicação:
```bash
docker compose up --build
```

A aplicação estará disponível em:
- API: http://localhost:4000
- Console H2: http://localhost:4000/h2-console

### Comandos Make

Para facilitar o desenvolvimento, foram criados alguns comandos Make:

```bash
make help        # Mostra todos os comandos disponíveis
make start       # Inicia a aplicação com Docker Compose
make stop        # Para a aplicação
make test        # Para rodar os testes da aplicação
make logs        # Mostra os logs da aplicação
```

## Como Executar sem docker
1. Clone o repositório
2. Execute o comando `./mvnw spring-boot:run`
3. Acesse a API em `http://localhost:4000`
4. Acesse a documentação Swagger em `http://localhost:4000/swagger`

## Endpoints

### Clientes
- `POST /api/client` - Cadastra um novo cliente
- `GET /api/client/{id}` - Consulta um cliente pelo ID

### Crédito
- `GET /api/credit/{clientId}/{vehicleModel}` - Verifica a elegibilidade de um cliente para um modelo de veículo
- `GET /api/credit/clients/hatch` - Lista os clientes elegíveis para veículos Hatch

## Tipos de Crédito

### Crédito Fixo
- Idade: 18 a 25 anos
- Renda: R$ 5.000,00 a R$ 15.000,00
- Taxa de juros: 2% ao ano

### Crédito Variável
- Idade: 21 a 65 anos
- Renda: R$ 5.000,00 a R$ 15.000,00

### Crédito Consignado
- Idade: 65 anos ou mais

## Regras de Elegibilidade por Modelo de Veículo

### Hatch
- Renda: R$ 5.000,00 a R$ 15.000,00

### SUV
- Renda: R$ 8.000,00 a R$ 15.000,00

## Testes
Execute os testes com o comando:
```bash
./mvnw test
```

## Documentação da API
A documentação completa da API está disponível através do Swagger UI em:
- Swagger UI: http://localhost:4000/swagger
- OpenAPI JSON: http://localhost:4000/v3/api-docs

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/
│   │   └── br/com/neurotech/neurocreditapi/
│   │       ├── controller/     # Controladores REST
│   │       ├── service/        # Serviços de negócio
│   │       ├── repository/     # Repositórios JPA
│   │       ├── entity/         # Entidades JPA
│   │       ├── dto/            # Objetos de transferência de dados
│   │       └── config/         # Configurações
│   └── resources/
│       └── application.yml     # Configurações da aplicação
└── test/                       # Testes
```

## Regras de Negócio

### Elegibilidade para Veículos

#### Hatch
- Idade: 18 anos ou mais
- Renda: R$ 5.000,00 ou mais

#### SUV
- Idade: 18 anos ou mais
- Renda: R$ 8.000,00 ou mais

## Contribuidores

- Gustavo Porpino - dev@oporpino.com
