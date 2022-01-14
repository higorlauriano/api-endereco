# API Básica de Cadastro de Enderecos
A aplicação utiliza uma conexão com postgres, logo, será necessário que uma instância esteja rodando para o deploy do projeto.

Comando para criação do container: **docker run --name postgres -e POSTGRES_PASSWORD=12345 -p 5432:5432 -d postgres**

## Rotas

#### Criar

@POST
/endereco
{
	"streetName": "Amphitheatre Pkwy",
	"number": 1600,
	"zipCode": "94043",
	"state": "CA",
	"country": "USA",
	"city": "Mountain View",
	"neighbourhood": "Santa Clara County"
}

#### Atualizar

@PATCH
/endereco/{id}
{
	"streetName": "Amphitheatre Pkwy",
	"number": 1602,
	"zipCode": "94043",
	"state": "CA",
	"country": "USA",
	"city": "Mountain View",
	"neighbourhood": "Santa Clara County"
}

#### Obter unico

@GET
/endereco/{id}
{
	"id": 1,
	"streetName": "Avenida Toledo",
	"number": 432,
	"complement": null,
	"neighbourhood": "Centro",
	"city": "Cascavel",
	"state": "PR",
	"country": "BR",
	"zipCode": "85810-230",
	"latitude": -24.950498,
	"longitude": -53.477254
}

#### Listar todos

@GET
/endereco
[
	{
		"id": 1,
		"streetName": "Avenida Toledo",
		"number": 432,
		"complement": null,
		"neighbourhood": "Centro",
		"city": "Cascavel",
		"state": "PR",
		"country": "BR",
		"zipCode": "85810-230",
		"latitude": -24.950498,
		"longitude": -53.477254
	}
]

#### Remover

@DELETE
/endereco/{id}
retorna 200 OK ou 400 com descricao do erro
