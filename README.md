# Sistema Petshop

Sistema de gerenciamento de petshop com persistência em MySQL usando JPA/Hibernate.

## Tecnologias

- Java 17+
- Maven
- Jakarta Persistence API 3.1.0
- Hibernate ORM 6.4.4.Final
- MySQL 8

## Estrutura do Projeto

```
src/main/java/br/edu/ifpi/
├── Principal/
│   ├── Main.java            # Menu principal
│   └── MainCliente.java     # Menu do cliente
├── Model/
│   ├── Pessoa.java          # Entidade base (abstract)
│   ├── Cliente.java         # Cliente do petshop
│   ├── Funcionario.java     # Funcionário
│   ├── Animal.java          # Animal (abstract)
│   ├── Cachorro.java        # Cachorro
│   ├── Gato.java            # Gato
│   ├── Servico.java         # Serviço (abstract)
│   ├── Banho.java           # Serviço de banho
│   ├── Tosa.java            # Serviço de tosa
│   └── Agendamento.java     # Agendamento
├── DAO/
│   ├── ClienteDAO.java      # CRUD de cliente
│   └── FuncionarioDAO.java  # CRUD de funcionário
└── JPAUtil.java             # Factory do EntityManager

src/main/resources/META-INF/
└── persistence.xml          # Configuração JPA/MySQL
```

## Funcionalidades

### Menu Principal
1. **Cadastrar Cliente** — nome, CPF, telefone, senha, endereço
2. **Cadastrar Funcionário** — nome, CPF, telefone, cargo, salário
3. **Pesquisar Cliente por CPF**
4. **Pesquisar Funcionário por CPF**
5. **Área do Cliente** — acesso via CPF + senha

### Área do Cliente (com login)
1. **Cadastrar animal** — cachorro ou gato (nome, idade, raça)
2. **Meus animais** — listar animais cadastrados
3. **Agendar serviço** — escolher animal → serviço (banho/tosa) → data
4. **Meus dados** — exibir dados do cliente logado

## Modelo de Dados

```
Pessoa (abstract)
├── Cliente (endereco, animais)
└── Funcionario (cargo, salario)

Animal (abstract)
├── Cachorro
└── Gato

Servico (abstract)
├── Banho (R$ 50,00)
└── Tosa (R$ 75,00)

Agendamento (cliente, animal, servico, data)
```

