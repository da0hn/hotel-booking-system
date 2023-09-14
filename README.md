# Hotel Booking System

## Sumário

* [1. Introdução](#1-introdução)
  * [1.1. Tecnologias](#11-tecnologias)
  * [1.2. Aplicações de suporte (infraestrutura)](#12-aplicações-de-suporte-infraestrutura)
  * [1.3. Tópicos e Filas](#13-tópicos-e-filas)
  * [1.4. Microsserviços](#14-microsserviços)
    * [1.4.1. Hotel Service](#141-hotel-service)
    * [1.4.2. Booking Service](#142-booking-service)
    * [1.4.3. Payment Service](#143-payment-service)
* [2. Setup](#2-setup)
  * [2.1. Pré Requisitos](#21-pré-requisitos)

# 1. Introdução

Este repositório possui um conjunto de microsserviços com o objetivo de representar o fluxo simplificado de um sistema de reserva de
quartos de hotel como, por exemplo, o `booking.com`.

Para isso a aplicação, foi dividida em 4 microsserviços e um módulo compartilhado entre esses serviços afim de evitar duplicação de
código.

----------------------------------------------------------------

## 1.1. Tecnologias

| Tecnologia  | Versão                |
|-------------|-----------------------|
| Docker      | 24.0.5, build ced0996 |
| Java        | OpenJDK 20.0.2        |
| Maven       | 3.9.2                 |
| Spring Boot | 3.1.3                 |
| MySQL       | 8.0.33                |
| RabbitMQ    | 3-management          |

## 1.2. Aplicações de suporte (infraestrutura)

As credenciais de acesso e portas podem ser alteradas através das variáveis de ambiente definidas no arquivo `.env` e nas variáveis do arquivo
`common.yml` e `services.yml`.

|   Tipo   |       Porta        |          Serviço           | Usuário |  Senha   | 
|:--------:|:------------------:|:--------------------------:|:-------:|:--------:|
|  MySQL   |        3311        |          hotel-db          |  user   | password |
|  MySQL   |        3312        |         booking-db         |  user   | password |
|  MySQL   |        3313        |        customer-db         |  user   | password |
| RabbitMQ | 5672, 25676, 15672 | hotel-booking-system-queue |  root   |   root   |

## 1.3. Tópicos e filas

|         Exchange          |               Routing Key               |             Fila              |
|:-------------------------:|:---------------------------------------:|:-----------------------------:|
|   exchange.booking-room   |   routing-key.booking-room-requested    |   `booking-room-requested`    |
|   exchange.booking-room   |  routing-key.booking-room-confirmation  |  `booking-room-confirmation`  |
|   exchange.booking-room   | routing-key.booking-room-status-changed | `booking-room-status-changed` |
|     exchange.payment      |       routing-key.payment-request       |       `payment-request`       |
|     exchange.payment      |    routing-key.payment-confirmation     |    `payment-confirmation`     |
| exchange.customer-booking |   routing-key.customer-booking-update   |   `customer-booking-update`   |

## 1.4. Microsserviços

|  Microsserviço   | Porta Padrão |            API URL            |
|:----------------:|:------------:|:-----------------------------:|
|  Hotel Service   |     8001     |  `${baseUrl}/hotel-service`   |
| Booking Service  |     8002     | `${baseUrl}/booking-service`  |
| Payment Service  |     8003     |               -               |
| Customer Service |     8004     | `${baseUrl}/customer-service` |

### 1.4.1. Hotel Service

Este serviço possui os endpoints para executar ações como a reserva de um quarto de hotel, criação de um hotel e
consulta dos quartos de hotel disponíveis. Ainda, se comunica com os outros três serviços através das filas do `RabbitMQ`.

----------------------------------------------------------------

### 1.4.2. Booking Service

Este serviço recebe mensagens vindas do `Hotel` e tem como responsabilidade gerenciar as reservas feitas pelo cliente armazenando as datas
da reserva relacionadas ao quarto de hotel.

----------------------------------------------------------------

### 1.4.3. Payment Service

Este serviço atua como um sistema de pagamento fake simulando a tentativa de pagamento e verificação de saldo notificando o serviço de `Hotel`
sobre o sucesso ou falha do pagamento através do `RabbitMQ`.

----------------------------------------------------------------

### 1.4.4. Customer Service

Este serviço contém informações sobre os agendamentos do cliente (`customer`) sendo atualizado a cada alteração no fluxo da reserva do quarto e
expondo endpoints para consultar a situação atual do agendamento do cliente.

# 2. Setup

Certifique-se de executar os passos abaixo na ordem correta e ter as ferramentas apresentadas instaladas em seu sistema antes de prosseguir com a
execução a da aplicação.

## 2.1. Pré Requisitos

* [Docker](https://docs.docker.com/desktop/)
  * Para verificar se o docker foi instalado corretamente execute o comando `docker --version`
* [Java 20](https://jdk.java.net/20/)
  * Configure corretamente a variável de ambiente `JAVA_HOME` apontando para a pasta raiz do Java. Ex: `JAVA_HOME=/opt/java/jdk20`
  * Para verificar se o Java foi instalado corretamente na versão exigida execute o comando `java --version`
  * Utilizando o powershell é possível configurar a variável de ambiente `JAVA_HOME` temporariamente utilizando o comando
    `$env:JAVA_HOME="D:\Programs\JDK\jdk20.0.2_10"`.
  * Em ambientes linux é possível instalar a ferramenta [sdkman](https://sdkman.io/install) para gerenciar as versões do Java de forma simplificada

## 2.2. Instalação

[//]: # (* Após realizar a instalação de todas as ferramentas necessárias e execute o comando abaixo na raiz do projeto para gerar as imagens dos )

[//]: # (  microsserviços.)

* Após realizar a instalação de todas as ferramentas necessárias é possível criar as imagens dos serviços de duas formas diferentes.
  * A primeira consiste em utilizar o plugin `spring-boot-maven-plugin` para criar a imagem do serviço através do comando abaixo:

```sh 
./mvnw clean install
```

* É possível também montar os artefatos jar em conjunto com o comando build do `docker compose`.

```sh 
./mvnw clean package
```

* Após a execução do comando deve ser possível identificar 4 imagens correspondentes aos microsserviços utilizando o comando:

```sh
docker image ls
```

* Com as imagens criadas, construa os volumes correspondentes para armazenamento de dados dos bancos de dados utilizando o comando:

```sh
docker volume create --name=hotel-db-volume --driver local --opt type=none --opt device=D:/opt/hotel-booking-system/hotel-db/mysql --opt o=bind
docker volume create --name=booking-db-volume --driver local --opt type=none --opt device=D:/opt/hotel-booking-system/booking-db/mysql --opt o=bind
docker volume create --name=customer-db-volume --driver local --opt type=none --opt device=D:/opt/hotel-booking-system/customer-db/mysql --opt o=bind
```

* Após isso é possível iniciar todos os serviços e aplicações auxiliares como banco de dados e filas executando o comando abaixo dentro da pasta
  `docker`.

```sh

docker-compose -f common.yml -f services.yml up -d
```

* Caso tenha optado por criar as imagens com o `docker compose` utilize o comando abaixo dentro da pasta docker:

```sh
docker-compose -f common.yml -f services.yml up -d --build
```


