# FastHistory
Controle de winrate do LOL

## Endpoints
- Campeöes 
    - [Listar Todos](#listar-todos)
    - [Cadastrar](#cadastrar)
    - [Apagar](#apagar-campeao)
    - [Editar](#editar-campeao)


### Listar Todos
`GET` /campeao

Retorna um array com todos os campeões cadastrados

**Exemplo de resposta**
```js
[   
    {
        "id": 1,
        "nome": "Jinx",
        "funcao": "Atirador"
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | dados retornados com sucesso |
| 404    | ID do campeão não encontrado |


### Cadastrar

`POST` /campeao

Insere um novo campeão

**Corpo da requisição**

|campo|tipo|obrigatório|descrição
|-----|----|-----------:|---------
|nome |string|✅| nome do campeão
|funcao|string|✅|função do campeão

```js

    {
        "nome": "Jinx",
        "funcao": "Atirador"
    }

```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 201    | Campeão criado com sucesso |
| 400    | Erro de validação - verifique o corpo da requisição |


### Apagar Campeao

`DELETE` /campeao/{id}

Apaga o campeão com o `id` informado.

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 204    | Campeão apagado com sucesso |
| 404    |  ID do campeão não encontrado |


### Editar Campeao

`PUT` /campeao/{id}

atualiza os dados do campeão com o `id` informado.

**Corpo da requisição**

```js

    {
        "nome": "Jinx",
        "funcao": "Atirador"
    }

```

**Exemplo de resposta**
```js
[   
    {
        "id": 1,
        "nome": "Jinx",
        "funcao": "Atirador"
    }
]
```

**Códigos de Status**

| código | descrição |
|--------:|-----------|
| 200    | Campeão atualizado com sucesso |
| 400    |  A validação falhou - verifique o corpo da requisição |
| 404    |  ID do campeão não encontrado |