
# Credit Card Transaction Authorizer

## Descrição

Este projeto implementa um sistema de autorização de transações de cartão de crédito. O sistema recebe transações e decide se as aprova ou rejeita com base em regras predefinidas, como o MCC (Merchant Category Code) e o saldo disponível em categorias específicas.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- MySQL
- Docker Compose
- JUnit

## Pré-requisitos

Certifique-se de ter os seguintes softwares instalados:

- Docker
- Docker Compose

## Passos para Executar o Projeto

1. **Clone o Repositório:**

   ```bash
   git clone https://github.com/gustavomolina/credit-card-authorizer.git
   cd credit-card-authorizer
   ```

2. **Construa e Execute o Projeto com Docker Compose:**

   No diretório raiz do projeto, execute o comando abaixo:

   ```bash
   docker-compose up --build
   ```

   Este comando irá:
   - Configurar o banco de dados MySQL.
   - Configurar e iniciar a aplicação Spring Boot.

3. **Verifique se a Aplicação está Rodando:**

   Acesse [http://localhost:8080/transactions](http://localhost:8080/transactions) para verificar se a aplicação está rodando.

## Como Consumir a API

### Endpoint: Autorizar Transação

- **URL:** `/transactions`
- **Método:** `POST`
- **Corpo da Requisição:**

  ```json
  {
    "account": {
        "accountId": "123"
    },
    "amount": 100.00,
    "mcc": "5811",
    "merchant": "PADARIA DO ZE SAO PAULO BR"
  }
  ```

- **Exemplo de Resposta:**

  ```json
  {
    "code": "00"
  }
  ```

- **Códigos de Resposta:**
  - `00` - Transação aprovada.
  - `51` - Transação rejeitada por saldo insuficiente.
  - `07` - Erro genérico ao processar a transação.


## Testes

Os testes unitários foram implementados usando JUnit. Para executá-los manualmente, você pode rodar o seguinte comando:

```bash
mvn test
```

## Contribuição

Pull requests são bem-vindos. Para mudanças maiores, abra uma issue primeiro para discutir o que você gostaria de mudar.

