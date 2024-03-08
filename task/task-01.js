-- Criando DB

> use edumatch
switched to db edumatch

-- Criando Collection

edumatch> db.createCollection("enderecos")
{ ok: 1 }

-- Inserindo dados com insertOne

edumatch> db.enderecos.insertOne(
    {
        "logradouro": "Rua das Flores",
        "numero": 123,
        "complemento": "Bloco A",
        "tipoDeEndereco": "RESIDENCIAL",
        "cep": "12345678",
        "cidade": "São Paulo",
        "estado": "SP",
        "pais": "Brasil",
        "idUsuario": 1
    }
)
{
    acknowledged: true,
        insertedId: ObjectId('65d4a269e00e908f79bebb2e')
}

-- Inserindo dados com insertMany

edumatch> db.enderecos.insertMany(
    [
        {
            "logradouro": "Avenida Brasil",
            "numero": 456,
            "complemento": "Andar 10",
            "tipoDeEndereco": "COMERCIAL",
            "cep": "54321987",
            "cidade": "Rio de Janeiro",
            "estado": "RJ", "pais": "Brasil",
            "idUsuario": 2
        },
        {
            "logradouro": "Rua das Palmeiras",
            "numero": 789,
            "complemento": "Casa 2",
            "tipoDeEndereco": "RESIDENCIAL",
            "cep": "76543210",
            "cidade": "Belo Horizonte",
            "estado": "MG",
            "pais": "Brasil",
            "idUsuario": 3
        }
    ]
)
{
    acknowledged: true,
        insertedIds: {
    '0': ObjectId('65d4a42de00e908f79bebb2f'),
        '1': ObjectId('65d4a42de00e908f79bebb30')
}
}

-- Buscando dados com find

edumatch> db.enderecos.find({"tipoDeEndereco": "RESIDENCIAL"})
[
    {
        _id: ObjectId('65d4a269e00e908f79bebb2e'),
        logradouro: 'Rua das Flores',
        numero: 123,
        complemento: 'Bloco A',
        tipoDeEndereco: 'RESIDENCIAL',
        cep: '12345678',
        cidade: 'São Paulo',
        estado: 'SP',
        pais: 'Brasil',
        idUsuario: 1
    },
    {
        _id: ObjectId('65d4a42de00e908f79bebb30'),
        logradouro: 'Rua das Palmeiras',
        numero: 789,
        complemento: 'Casa 2',
        tipoDeEndereco: 'RESIDENCIAL',
        cep: '76543210',
        cidade: 'Belo Horizonte',
        estado: 'MG',
        pais: 'Brasil',
        idUsuario: 3
    }
]

-- Buscando dados com find usando expressões regulares (regex)

edumatch> db.enderecos.find({"logradouro": /Flores/})
[
    {
        _id: ObjectId('65d4a269e00e908f79bebb2e'),
        logradouro: 'Rua das Flores',
        numero: 123,
        complemento: 'Bloco A',
        tipoDeEndereco: 'RESIDENCIAL',
        cep: '12345678',
        cidade: 'São Paulo',
        estado: 'SP',
        pais: 'Brasil',
        idUsuario: 1
    }
]