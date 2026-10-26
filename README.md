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
| Command | Comportamental | Cada opção do menu (`Cadastrar Médico`, `Cadastrar Paciente`, etc.) é encapsulada como um objeto `MenuAction` (`CreateDoctorAction`, `CreatePatientAction`). O CLI não conhece a lógica de cada cadastro, apenas invoca `execute()`, permitindo adicionar novas opções sem alterar o laço principal do menu. |
| _A definir_ | Criacional | _Pendente: segundo padrão criacional (ex.: Builder para montar `Appointment` com dados opcionais, Singleton para um serviço compartilhado, etc.)._ |
| _A definir_ | Estrutural | _Pendente: primeiro padrão estrutural (ex.: Adapter, Decorator, Facade)._ |
| _A definir_ | Estrutural | _Pendente: segundo padrão estrutural._ |
| _A definir_ | Comportamental | _Pendente: segundo padrão comportamental (ex.: Observer para notificar mudança de status de consulta, State para o ciclo de vida do `Appointment`)._ |

## Estrutura do projeto

src/main/java/com/mainbreak/
├── controller/     # Orquestra casos de uso, liga repository e factory
├── interfaces/      # Contratos (iUser)
├── model/
│   ├── enums/        # UserType, AppointmentStatus
│   └── *Factory.java # Factory Method
├── repository/       # Armazenamento em memória (ArrayList)
├── view/
│   └── action/       # Command (MenuAction e implementações)
└── Main.java          # Ponto de entrada, monta o menu interativo

## Equipe

- KAINAN HENRIQUE COUTO DOS SANTOS
- JOÃO HENRIQUE ZIEGLE DO ROSÁRIO
- STEPHANI APARECIDA JUNG DIAS
- GABRIEL DOMINGUES DE OLIVEIRA