# Sistema de Gerenciamento de Contas Bancárias em Java

### Descrição
Sistema de gerenciamento bancário desenvolvido em Java com interface via JOptionPane. Permite cadastrar contas correntes e poupanças, realizar depósitos, saques, consultar saldos, exibir extratos e histórico de transações, além de gerar relatórios gerais do banco.


## Tecnologias Ultilizadas

- Java 21 
- Eclipse IDE
- JOptionPane
- Programação Orientada a Objetos (POO)


## Como Executar

### Pré-requisitos

- Java 21 instalado
- IDE instalada

### Passo a passo

1. Abra uma IDE e crie um novo projeto em Java
2. De o nome de "SistemaBancario"
3. Dentro da pasta `src`, crie os seguintes pacotes:
   - `banco.interfaces`
   - `banco.model`
   - `banco.service`
   - `banco.app`
4. Copie cada arquivo `.java` para seu respectivo pacote
5. Com o projeto aberto, clique em executar



### Passo a passo via terminal
 
```bash
# Compile todos os arquivos
javac -d bin src/banco/interfaces/Operavel.java \
            src/banco/model/Cliente.java \
            src/banco/model/ContaBancaria.java \
            src/banco/model/ContaCorrente.java \
            src/banco/model/ContaPoupanca.java \
            src/banco/service/BancoService.java \
            src/banco/app/SistemaBanco.java
 
# Execute
java -cp bin banco.app.SistemaBanco
```
 


## Descrição de Pacotes e Classes

src/  
└── banco/  
├── model/     
│ ├── Cliente.java  
│ ├── ContaBancaria.java      
│ ├── ContaCorrente.java   
│ └── ContaPoupanca.java   
├── interfaces/        
│ └── Operavel.java (interface)  
├── service/    
│ └── BancoService.java   
└── app/     
└── SistemaBanco.java   

## Funcionalidades do sistema
 
| Opção | Funcionalidade |  
 
| 1 | Cadastrar Conta Corrente |  
| 2 | Cadastrar Conta Poupança |  
| 3 | Depositar |  
| 4 | Sacar |  
| 5 | Consultar Saldo |  
| 6 | Exibir Extrato da Conta |  
| 7 | Exibir Histórico de Transações |  
| 8 | Listar Todas as Contas |  
| 9 | Relatório Geral do Banco |  
| 0 | Encerrar Sistema |  
 



## Diagrama da hierarquia de classes
 
```
«interface»
Operavel
    |
    | implements
    |
ContaBancaria (abstract)
    |
    |─────────────────────┐
    |                     |
ContaCorrente        ContaPoupanca
 
 
Cliente          BancoService          SistemaBanco
```

## Estrutura de pacotes e classes
 
**banco.interfaces**


- `Operavel` — interface com os métodos `depositar()`, `sacar()` e `exibirSaldo()`. Toda conta deve implementá-la.


**banco.model**
- `Cliente` — armazena nome, CPF e telefone do titular.
- `ContaBancaria` — classe abstrata base. Gerencia saldo, histórico com timestamp, depósito, saque e exibição de saldo.
- `ContaCorrente` — estende `ContaBancaria`. Adiciona cheque especial.
- `ContaPoupanca` — estende `ContaBancaria`. Adiciona taxa de rendimento mensal.


**banco.service**
- `BancoService` — gerencia as listas de contas, cadastro com validação de número único, busca, listagem e relatório geral.


**banco.app**
- `SistemaBanco` — ponto de entrada (`main`). Controla o menu e delega as operações.



 
## Autor

Assucena Costa B. dos Reis

Informatica 2° Período  
*Projeto desenvolvido para fins acadêmicos*