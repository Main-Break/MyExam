# MyExam

Sistema de gestão de consultas médicas (Doctor, Nurse, Patient, Appointment), desenvolvido como trabalho final da disciplina Padrões de Projeto. O projeto aplica 6 padrões de projeto do catálogo GoF (2 Criacionais, 2 Estruturais e 2 Comportamentais) na arquitetura de um domínio de negócio real.

## Como executar

1. Clonar o repositório.
2. Compilar com `javac` ou importar como projeto Maven na sua IDE (IntelliJ, Eclipse, etc.).
3. Executar `Main.java`.
4. Navegar pelo menu interativo no terminal.

## Padrões de Projeto utilizados

| Padrão | Categoria | Problema de negócio resolvido |
|---|---|---|
| Factory Method | Criacional | Centraliza a criação de `Doctor`, `Nurse` e `Patient` em classes dedicadas (`DoctorFactory`, `NurseFactory`, `PatientFactory`). Os construtores dos modelos são `protected`, garantindo que nenhuma outra camada crie um usuário fora desse ponto controlado. |
| Builder | Criacional | Monta `UserRegistration` passo a passo, separando dados comuns dos campos específicos do médico e do paciente. O log do terminal indica cada perfil montado. |
| Command | Comportamental | Cada opção do menu (`Cadastrar Médico`, `Cadastrar Paciente`, etc.) é encapsulada como um objeto `MenuAction` (`CreateDoctorAction`, `CreatePatientAction`). O CLI não conhece a lógica de cada cadastro, apenas invoca `execute()`, permitindo adicionar novas opções sem alterar o laço principal do menu. |
| Decorator | Estrutural | Adiciona log de início, fim e erro na execução de uma `MenuAction` (`LoggingActionDecorator`) sem alterar `CreateDoctorAction`/`CreatePatientAction`. Novas ações ganham o log só envolvendo a ação original na hora de montar o menu. |
| Facade | Estrutural | `CadastroFacade` reúne o cadastro de médico e de paciente numa única interface para as `MenuAction`, que deixam de conhecer `DoctorController` e `PatientController` diretamente. |
| Chain of Responsibility | Comportamental | Encadeia validação dos campos comuns, dos campos específicos do perfil e da unicidade de e-mail, CPF e CRM. Cada elo registra no terminal quando é acionado. |

O cadastro é coordenado por `RegistrationService`, que recebe as factories, o `PasswordHasher`, os repositórios e a cadeia de validação por construtor. A senha é transformada em hash antes de finalizar o `UserRegistration`; somente o hash é encaminhado à Factory Method.

## Validação de cadastro e senha

`RegistrationValidationHandler` define a cadeia: `CommonRegistrationValidationHandler` verifica os campos comuns, `ProfileRegistrationValidationHandler` verifica data de nascimento ou dados profissionais, e `UniqueRegistrationValidationHandler` verifica e-mail, CPF e CRM nos repositórios. Os métodos de busca retornam `Optional`, e o cadastro só continua quando os valores estão disponíveis.

`PasswordHasher` é uma interface injetada no serviço. `Pbkdf2PasswordHasher` implementa PBKDF2 com HMAC-SHA-256, salt aleatório e 120.000 iterações. A aplicação usa somente APIs padrão do Java e não adiciona bibliotecas externas.

## Estrutura do projeto
```Text
src/main/java/com/mainbreak/
├── controller/     # Orquestra casos de uso, liga repository e factory; Facade (CadastroFacade)
├── interfaces/      # Contratos (iUser)
├── model/
│   ├── enums/        # UserType, AppointmentStatus
│   └── *Factory.java # Factory Method
├── repository/       # Armazenamento em memória (ArrayList) e buscas por perfil
├── security/         # Abstração e implementação de hash de senha
├── service/          # Cadastro com Builder, Factory Method e Chain of Responsibility
│   └── validation/   # Elos de validação de cadastro
├── view/
│   └── action/       # Command (MenuAction e implementações) e Decorator (LoggingActionDecorator)
└── Main.java          # Ponto de entrada, monta o menu interativo
```
## Equipe

- KAINAN HENRIQUE COUTO DOS SANTOS
- JOÃO HENRIQUE ZIEGLE DO ROSÁRIO
- STEPHANI APARECIDA JUNG DIAS
- GABRIEL DOMINGUES DE OLIVEIRA
