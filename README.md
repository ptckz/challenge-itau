# Desafio Itau Seg

- Java JDK 17
- Spring Boot 3
- JoCoCo
- JUnit 5
- H2

## Como executar

Build 

```bash
$ mvn clean install
```
Relatório do JoCoCo encontra-se em /target/site/jacoco/index.html

Executar os testes

```bash
$ mvn test
```

Executar o projeto

```bash
$ spring-boot:run
```

## Informações do Projeto

O Design Pattern adotado foi o Mediator, uma escolha estratégica que se destaca em situações onde um conjunto de objetos apresenta forte acoplamento. Ao optar pelo Mediator, consegui reduzir as dependências entre esses objetos, resultando na diminuição da complexidade global do sistema. Abordagem está alinhada com o Princípio da Responsabilidade Única, uma vez que a responsabilidade pela coordenação e comunicação entre os objetos é atribuída a um único componente, promovendo uma melhor organização e manutenção do código. Dessa forma, cada objeto pode se concentrar em sua responsabilidade principal, sem a necessidade de lidar diretamente com a complexidade da interação entre os demais. A escolha do Mediator também está alinhada com o Princípio Aberto-Fechado. A capacidade de introduzir novos mediadores no sistema sem a necessidade de modificar partes existentes respeita a ideia de extensibilidade do código sem alterar o seu funcionamento já estabelecido. Isso proporciona uma arquitetura flexível e adaptável. Busquei não apenas reduzir a complexidade e o acoplamento entre objetos, mas também aderir aos princípios fundamentais da arquitetura de software, como Responsabilidade Única e Aberto-Fechado, promovendo assim um design mais modular, flexível e sustentável.

Utilizando o padrão Clean Architecture de Martin Fowler consegui manter o código-fonte independente de detalhes de implementação. Em forma de camadas, onde a camada mais interna contém as regras de negócios centrais, enquanto as camadas externas lidam com detalhes de frameworks enfatizando a importância da separação de responsabilidades, utilizando padrões como Inversão de Controle (IoC) e Injeção de Dependência para reduzir o acoplamento entre os componentes do sistema. Isso proporciona maior flexibilidade e facilita a substituição de partes específicas sem afetar o restante do código. O Clean Architecture de Martin Fowler visa criar sistemas modulares, testáveis e de fácil manutenção, promovendo a organização estruturada do código.





