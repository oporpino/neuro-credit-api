# Neurotech Credit API

## Descrição
API para avaliação de crédito para financiamento de veículos, desenvolvida como parte do desafio técnico da Neurotech.

## Tecnologias Utilizadas
- Java 17
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
- Java 17 ou superior
- Maven 3.8 ou superior

## Como Executar
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
- Taxa de juros: 3% ao ano
- Fator de risco: 1,5% para valores acima de R$ 10.000,00

### Crédito Consignado
- Idade: 65 anos ou mais
- Renda: R$ 5.000,00 a R$ 15.000,00
- Taxa de juros: 3% ao ano

## Regras de Elegibilidade por Modelo de Veículo

### Hatch
- Idade: 18 a 65 anos
- Renda: R$ 5.000,00 a R$ 15.000,00

### SUV
- Idade: 21 a 65 anos
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
