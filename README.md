# Desafio Itau Seg

- Java Version 17
- Spring Boot 3
- JUnit 5
- H2

## Como executar

Build 

```bash
$ mvn clean install
```
Relatório do JoCoCo encontra-se em /target/site/jacoco/index.html

Rodar os testes

```bash
$ mvn test
```

Executar

```bash
$ spring-boot:run
```

## Informações do Projeto

O padrão utilizado foi o Mediator pois quando temos que lidar com um conjunto de objetos fortemente acoplados e difíceis de manter, podemos reduzir as dependências entre objetos e diminuir a complexidade geral e extraimos a lógica de comunicação para um dependente único, portanto seguimos o Principio de Resposabilidade Única. Além disso, podemos introduzir novos mediadores sem a necessidade de alterar partes do sistema. Portanto, seguimos com o Principio Aberto-Fechado.

Organização de pacotes:

```bash
main\app
    |    \
    |    core
    |    |    \
    |    |     expection
    |    |     gateway
    |    |     tarifas
    |    |     usecase
    |    |     data
    |    data
    |        \
    |         entities
    |         repositories
    |   presenter
    |        \
    |        config
    |        rest
    |        usecase
    |   AppApplication.java
    resources           
test\app
    |  \
    |   core
    |   data
    |   usecase
```

