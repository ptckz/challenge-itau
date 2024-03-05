# Desafio Itau Seg

- JDK 17
- Spring Boot 3
- Lombok 
- JaCoCo
- JUnit 5
- Project Java Faker
- H2 

## Como executar

Build 

```bash
$ mvn clean install
```
Report do JaCoCo encontra-se em /target/site/jacoco/index.html

Executar os testes

```bash
$ mvn test
```

Executar o projeto

```bash
$ mvn spring-boot:run
```

## Informações do Projeto

O Design Pattern adotado foi o Mediator, uma escolha estratégica que se destaca em situações onde um conjunto de objetos apresenta forte acoplamento. Ao optar pelo Mediator, consegui reduzir as dependências entre esses objetos, resultando na diminuição da complexidade global do sistema. Abordagem está alinhada com o Princípio da Responsabilidade Única, uma vez que a responsabilidade pela coordenação e comunicação entre os objetos é atribuída a um único componente, promovendo uma melhor organização e manutenção do código. Dessa forma, cada objeto pode se concentrar em sua responsabilidade principal, sem a necessidade de lidar diretamente com a complexidade da interação entre os demais. A escolha do Mediator também está alinhada com o Princípio Aberto-Fechado. A capacidade de introduzir novos mediadores no sistema sem a necessidade de modificar partes existentes respeita a ideia de extensibilidade do código sem alterar o seu funcionamento já estabelecido. Isso proporciona uma arquitetura flexível e adaptável. Busquei não apenas reduzir a complexidade e o acoplamento entre objetos, mas também aderir aos princípios fundamentais da arquitetura de software, como Responsabilidade Única e Aberto-Fechado, promovendo assim um design mais modular, flexível e sustentável.

Utilizando o padrão Clean Architecture de Martin Fowler consegui manter o código-fonte independente de detalhes de implementação. Em forma de camadas, onde a camada mais interna contém as regras de negócios centrais, enquanto as camadas externas lidam com detalhes de frameworks enfatizando a importância da separação de responsabilidades, utilizando padrões como Inversão de Controle (IoC) e Injeção de Dependência para reduzir o acoplamento entre os componentes do sistema. Isso proporciona maior flexibilidade e facilita a substituição de partes específicas sem afetar o restante do código. O Clean Architecture de Martin Fowler visa criar sistemas modulares, testáveis e de fácil manutenção, promovendo a organização estruturada do código.
 
## Decisões tomadas 

Para atender às solicitações conforme proposto no desafio, optei por implementar dois endpoints específicos. No primeiro, é possível criar um novo produto e persisti-lo na base de dados(H2) utilizando o método POST. No segundo endpoint, utilizei o método PUT, passando o ID do produto previamente cadastrado como parâmetro para atualizar o produto. Dessa forma, busquei garantir a conformidade com as exigências de entrada e saída estipuladas.

- Criação de produtos, exemplo de request:
```bash
$ POST http://localhost:9000/api/v1/produto
```
```json
{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 120.0
}
```
Response:
```json
{
    "id": "d117795a-867f-43ef-9eac-e1c884d9e27e",
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 120.00,
    "preco_tarifado": 123.84
}
```

- Atualização de produtos, exemplo de request:
```bash
$ PUT http://localhost:9000/api/v1/produto/{id}
```
```json
{
    "nome": "Seguro de Vida Individual",
    "categoria": "VIDA",
    "preco_base": 320.0
}
```
Response:
```json
{
    "id": "d117795a-867f-43ef-9eac-e1c884d9e27e",
    "nome": "Seguro de VIDA Individual",
    "categoria": "VIDA",
    "preco_base": 320.00,
    "preco_tarifado": 330.24
}
```



