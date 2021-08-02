# Cine Api
*Esse projeto faz parte de um desafio, que consiste em cria um sistema do Cine Theatro Brasil Vallourec que permita que os visitantes conheça melhor os espaços do Cine Brasil e também  coletar estórias que viveram relacionadas ao cinema.*

## Tecnologias usadas

- String Framework
  - Spring Web
  - Spring Data Jpa
  - String security
  - Spring Cloud
  - MapStruct
  - Lombok
  - Swagger
  - MySQL
  - Maven
  - Java 

## Passos para configuração

**1. Clone a aplicação**

```bash
git clone https://github.com/LudmyllaArielly/cine-api.git
```
**2. Execute o aplicativo usando maven**

```bash
mvn cine-api:run
```
**3. Pré-requisitos**
```bash
mvn --version
```
Veja a indicação da versão do maven instalada, bem como a versão do JDK, entre outras. Esses requisitos são obrigátorios, portanto é necessário definir corretamento as variáveis de ambiente JAVA_HOME, M2_HOME.
O aplicativo começará a ser executado em: <http://localhost:8080>

**4. Compila**

```bash
mvn compile
```
Este comando compila o projeto e deposita os resultados no diretório de destino.

**5. Executando a Rest Api**

```bash
java -jar target/api.jar
or
mvn cine-api:run
```
Neste caminho
A Api foi geradada pelo pacote mvn package -P e esta sendo executada na porta 8080.

Exemplo de endereço: http://localhost:8080/cine-api

Exemplo de endereço com swagger: http://localhost:8080/swagger-ui.html#/

## Explorar Rest APIs

### Usuário

| Method | Url                            | Description                              |
| ------ | ------------------------------ | ---------------------------------------- |
| GET    | /users                         | Lista todos usuários                     |
| POST   | /users/signin                  | Cria usuário                             |
| PUT    | /users                         | Atualiza usuário                         |
| POST   | /users/signup                  | Autentica usuário                        |
| GET    | /users/findUserByCpf/{cpf}     | Busca o usuário pelo cpf                 |
| GET    | /users/findUserByEmail/{email} | Busca conta por tipo da conta e tipo do valor |



## Exemplo de corpo de solicitações JSON válidos

##### Cria usuário
```json
{
  "city": "Anápolis",
  "cpf": "78985245632",
  "email": "maria@gmail.com",
  "name": "Maria",
  "password": "ma447788",
  "phoneNumber": "99 999999999",
  "roleCreateDtos": [
    {
      "name": "ROLE_ADMIN"
    }
  ],
  "state": "GO"
}
```

##### Autentica usuário
```json
{
  "email": "maria@gmail.com",
  "password": "ma447788"
}
```

##### Atualiza Usuário
```json
{
  "city": "Anápolis",
  "cpf": "78985245632",
  "email": "maria@gmail.com",
  "id":1,
  "name": "Maria",
  "password": "ma447788",
  "phoneNumber": "99 999999999",
  "roleCreateDtos": [
    {
      "name": "ROLE_ADMIN"
    }
  ],
  "state": "GO"
}
```


#####  :warning: A aplicação ainda está em progresso.

 