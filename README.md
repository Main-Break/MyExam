# MyExam

Aplicação de terminal em Java para organizar contas de pacientes, médicos e administradores e servir de base para funcionalidades de consultas médicas.

## Requisitos

- JDK 25, conforme `maven.compiler.source` e `maven.compiler.target` definidos em `pom.xml`.
- Maven instalado para compilar e executar os testes quando forem adicionados.

## Executar

O cadastro público cria pacientes. Para testar as funções administrativas, configure as quatro variáveis de ambiente abaixo **antes de iniciar a aplicação**. Elas criam uma conta admin apenas para a execução atual:

| Variável | Conteúdo |
|---|---|
| `MYEXAM_ADMIN_NAME` | Nome do administrador |
| `MYEXAM_ADMIN_CPF` | CPF com 11 dígitos |
| `MYEXAM_ADMIN_EMAIL` | E-mail usado no login |
| `MYEXAM_ADMIN_PASSWORD` | Senha com pelo menos 8 caracteres |

Exemplo no PowerShell:

```powershell
$env:MYEXAM_ADMIN_NAME = "Admin MyExam"
$env:MYEXAM_ADMIN_CPF = "12345678900"
$env:MYEXAM_ADMIN_EMAIL = "admin@example.com"
$env:MYEXAM_ADMIN_PASSWORD = "defina-uma-senha-segura"
mvn compile
mvn exec:java -Dexec.mainClass="com.mainbreak.Main"
```

O projeto ainda não declara o plugin `exec-maven-plugin`. Se o comando acima não estiver disponível, compile com Maven e execute `com.mainbreak.Main` pela configuração de execução Java da IDE.

No menu inicial, escolha entrar, criar conta de paciente ou sair. Depois do login, a aplicação direciona para o menu associado ao perfil:

- **Administrador:** cadastrar médicos e listar contas.
- **Médico:** menu reservado para futuras funcionalidades clínicas; por enquanto, permite encerrar a sessão.
- **Paciente:** menu reservado para futuras funcionalidades de consulta; por enquanto, permite encerrar a sessão.

### Persistência no estado atual

As contas são guardadas em memória pelo `InMemoryUserRepository`. Elas existem apenas enquanto o processo está em execução e são apagadas ao encerrar o programa. O admin configurado pelas variáveis de ambiente também precisa ser recriado a cada inicialização. O arquivo `src/main/resources/database.sql` descreve tabelas SQLite, mas a aplicação atual **ainda não está ligada ao SQLite**.

## Cadastro, login e permissões

- O cadastro público aceita nome, CPF, e-mail, senha e data de nascimento e sempre cria `PATIENT`.
- O e-mail é normalizado para minúsculas e espaços externos são removidos antes do uso.
- O CPF deve conter 11 dígitos; e-mail precisa ter formato básico válido; senha deve conter pelo menos 8 caracteres.
- CPF e e-mail não podem ser reutilizados. CRM também é verificado para novos médicos.
- O login procura a conta pelo e-mail e verifica a senha contra seu hash.
- Médicos só podem ser cadastrados por um administrador autenticado. Cada médico tem CRM, especialidade e a role `DOCTOR`.
- A role identifica `ADMIN`, `DOCTOR` ou `PATIENT`. CRM e especialidade descrevem o perfil profissional e não são permissões.
- A conta inicial `ADMIN` é criada exclusivamente a partir das variáveis de ambiente; não existe opção pública de cadastro administrativo.
- As ações administrativas verificam a role no serviço, não apenas no menu da aplicação.

## Senhas

`PasswordHasher` usa PBKDF2 com HMAC-SHA-256, salt aleatório de 16 bytes e 120.000 iterações. O valor armazenado inclui número de iterações, salt e hash, codificados em Base64. A senha original não é gravada no modelo nem no repositório.

## Arquitetura e responsabilidades

| Pacote | Responsabilidade |
|---|---|
| `com.mainbreak` | Inicializa dependências e executa a aplicação. |
| `com.mainbreak.view` | Exibe prompts, lê entradas e mostra mensagens. |
| `com.mainbreak.controller` | Coordena menus e chamadas aos serviços. |
| `com.mainbreak.service` | Aplica regras de cadastro, autenticação e autorização. |
| `com.mainbreak.service.validation` | Encadeia as validações de cadastro usando Chain of Responsibility. |
| `com.mainbreak.repository` | Define o acesso às contas e oferece armazenamento em memória. |
| `com.mainbreak.model` | Representa usuários, perfis e roles. |
| `com.mainbreak.factory` | Monta dados de cadastro e cria os modelos concretos. |
| `com.mainbreak.security` | Gera e valida hashes de senha. |
| `com.mainbreak.exception` | Contém exceções de validação apresentadas pela aplicação. |

### Fluxo de autenticação

1. `Main` cria o repositório, os serviços, controllers e menu.
2. Se todas as variáveis `MYEXAM_ADMIN_*` estiverem configuradas, cria o admin inicial; se estiverem parcialmente configuradas, a inicialização falha com mensagem explícita.
3. O menu público permite login, cadastro de paciente ou encerramento.
4. `AuthService` valida os dados e delega a criação ao creator do perfil.
5. Após o login, `ApplicationController` seleciona o menu de acordo com `UserRole`.
6. Operações administrativas passam por `AdminService`, que exige a role `ADMIN`.

## Padrões de projeto

Três padrões estão aplicados ao código atual. Os outros três permanecem planejados para as funcionalidades de consulta e atendimento:

| Padrão | Onde se aplica | Situação |
|---|---|---|
| **Factory Method** | Criação de `Patient`, `Doctor` e `Admin` a partir de `UserRegistration`. | Aplicado nos creators do pacote `factory`. |
| **Builder** | Montagem de `UserRegistration` com campos comuns e campos específicos para cada perfil. | Aplicado em `UserRegistration.Builder`; `buildPatient`, `buildDoctor` e `buildAdmin` selecionam a forma de cadastro. |
| **Facade** | Simplificação do agendamento, coordenando disponibilidade do médico e validação do paciente. | Previsto; o fluxo de agendamento ainda não foi implementado. |
| **Decorator** | Adição de extras a uma consulta, como exame anexado, sem criar subclasses para cada variação. | Previsto; extras de consulta ainda não foram implementados. |
| **Template Method** | Definição do roteiro comum de atendimento, com etapas ajustáveis por especialidade. | Previsto; o atendimento clínico ainda não foi implementado. |
| **Chain of Responsibility** | Validação sequencial dos campos comuns, dos campos por perfil e da unicidade de e-mail, CPF e CRM. | Aplicado pelos validadores em `com.mainbreak.service.validation`. |

O cadastro usa os dois padrões aplicados recentemente em conjunto: `AuthService` monta um `UserRegistration` com o Builder e passa o resultado pela cadeia de validação antes de chamar o Factory Method que cria o modelo de usuário.

## Estrutura principal

```text
src/main/java/com/mainbreak/
├── controller/
├── exception/
├── factory/
├── model/
├── repository/
├── security/
├── service/
├── view/
└── Main.java
src/main/resources/
└── database.sql
```

## Banco de dados planejado

`src/main/resources/database.sql` contém um esquema SQLite para usuários, médicos, pacientes e consultas. Neste momento, ele é apenas referência: não há driver JDBC, configuração de conexão nem repositório SQLite integrado. A coluna `type` do esquema representa a role e deve receber um dos perfis `ADMIN`, `DOCTOR` ou `PATIENT` quando a persistência for implementada. Recomenda-se também tornar o e-mail único no banco.

## Estado e próximos passos

- Implementados: menu de terminal, cadastro de pacientes, login, hash de senha, roles, bootstrap de admin, cadastro administrativo de médicos e listagem de contas.
- Ainda não implementados: persistência durável, recuperação de senha, agenda/consultas e menus clínicos de médico e paciente.
- A compilação e os testes devem ser executados com o JDK 25 configurado. No ambiente em que este README foi escrito, Maven e JDK 25 não estavam disponíveis; a validação de compilação não pôde ser concluída.
