Legacy17 - Sistema de Gerenciamento de Usuários
O Legacy17 é uma API RESTful desenvolvida com Java 17 e Spring Boot 3, focada em um sistema de cadastro de usuários seguro, validado e escalável. O projeto utiliza containers para garantir um ambiente de desenvolvimento padronizado e persistência robusta.

🚀 Stack Tecnológica
Linguagem: Java 17

Framework: Spring Boot 3

Banco de Dados: PostgreSQL

Containers: Docker & Docker Compose

Persistência: Spring Data JPA / Hibernate

Segurança & Validação: Bean Validation (Hibernate Validator)

Ferramentas: Maven, Jackson (JSON/Date Parsing)

🛠️ Funcionalidades e Implementações Técnicas
Orquestração com Docker: Configuração de ambiente isolado para o banco de dados PostgreSQL via Docker Compose, facilitando o setup do projeto em qualquer máquina.

Arquitetura e Mapeamento: Uso de DTOs (Data Transfer Objects) e Mappers para garantir o desacoplamento entre a camada de persistência (Entities) e a camada de apresentação.

Validações Customizadas:

Uso de Regex para garantir nomes apenas com letras e senhas com caracteres especiais.

Validação de datas de nascimento (@Past) com formatação customizada via @JsonFormat.

Garantia de integridade de dados tanto no nível da aplicação quanto no banco de dados (Not-Null Constraints).

Tratamento de Exceções Global: Implementação de um ControllerAdvice para capturar erros de validação e retornar mensagens claras (ex: "A senha deve conter um caracter especial"), melhorando a integração com o front-end.

📂 Como executar o projeto
Clone o repositório:

Bash
git clone https://github.com/MikhayahIV/Legacy17.git

Configure as variáveis de ambiente:

Crie um arquivo .env na raiz do projeto com base no .env.example.

Suba os containers:

Bash
docker-compose up -d
Inicie a aplicação:
Execute via IntelliJ ou terminal:

Bash
mvn spring-boot:run