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


### História

| Method | Url                            | Description                              |
| ------ | ------------------------------ | ---------------------------------------- |
| GET    | /stories                       | Lista todas histórias                    |
| POST   | /stories                       | Cria história                            |
| PUT    | /stories                       | Atualiza história                        |
| GET    | /stories/findStoryByCategory   | Busca a história por categoria           |
| GET    | /stories/findStoryByPeriod     | Busca a história por período             |
| GET    | /stories/findStoryByStatus     | Busca a história por status              |
| PATCH  | /stories/updateStoryStatus     | Atualiza o status da história            |
| DELETE | /stories/{id}                  | Deleta história                          |


### Categoria

| Method | Url                            | Description                              |
| ------ | ------------------------------ | ---------------------------------------- |
| GET    | /categories                    | Lista todas categorias                   |
| POST   | /categories                    | Cria categoria                           |
| PUT    | /categories                    | Atualiza categoria                       |
| GET    | /categories/{description}      | Busca categoria pela descrição           |
| DELETE | /categories/{id}               | Deleta categoria                         |


### Período

| Method | Url                            | Description                              |
| ------ | ------------------------------ | ---------------------------------------- |
| GET    | /periods                       | Lista todos períodos                     |
| POST   | /periods                       | Cria período                             |
| PUT    | /periods                       | Atualiza período                         |
| GET    | /periods/{periodOfStory}       | Busca périodo                            |
| DELETE | /periods/{id}                  | Deleta período                           |


### Upload S3

| Method | Url                            | Description                              |
| ------ | ------------------------------ | ---------------------------------------- |
| POST   | /uploads/{file}                | Realiza uploads de imagens para AWS      |


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

##### Atualiza usuário
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

##### Cria história
```json
{
  "audio": "https://audiosupload.com/audioestoriacultura.mp3",
  "categoryDto": {
    "description": "Cultura"
  },
  "description": "Ao contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico.",
  "image": "https://bucket.s3.us-east-2.amazonaws.com/image-241327509785422112079922248.jpg",
  "periodDto": {
    "periodOfStory": "1930-1960"
  },
  "userCpfDto": {
    "cpf": "78985245632"
  }
}
```

##### Atualiza história
```json
{
  "audio": "https://audiosupload.com/audioestoriacultura.mp3",
  "categoryDto": {
    "description": "Cultura"
  },
  "description": "Ao contrário do que se acredita, Lorem Ipsum não é simplesmente um texto randômico.",
  "id": 1,
  "image": "https://bucket.s3.us-east-2.amazonaws.com/image-241327509785422112079922248.jpg",
  "periodDto": {
    "periodOfStory": "1930-1960"
  }
}
```

##### Atualiza status da história
```json
{
  "id": 1,
  "status": "CREATED"
}
```

##### Cria categoria
```json
{
  "description": "Cultura"
}
```

##### Atualiza categoria
```json
{
  "id": 1,
  "description": "Cultura"
}
```

##### Cria período
```json
{
  "periodOfStory": "1930-1960"
}
```

##### Atualiza período
```json
{
  "id": 1,
  "periodOfStory": "1930-1960"
}
```


#####  :warning: A aplicação ainda está em progresso.

 
