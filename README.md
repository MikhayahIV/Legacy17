# Legacy17 - User Management Ecosystem

O **Legacy17** é uma API RESTful robusta desenvolvida com **Java 17** e **Spring Boot 3**, focada na gestão segura de usuários e integração de atividades. O projeto utiliza containers para garantir um ambiente de desenvolvimento padronizado e persistência resiliente.

## 🚀 Diferenciais Técnicos
* **Segurança:** Implementação de **Spring Security** com hashing de senhas via **BCrypt**.
* **Integridade:** Validações complexas com **Bean Validation (Jakarta)** e tratamento global de exceções.
* **Infraestrutura:** Orquestração completa via **Docker Compose** (PostgreSQL).
* **Arquitetura:** Uso de DTOs e Mappers para desacoplamento de camadas.

## 🛠️ Stack Tecnológica
* Java 17 | Spring Boot 3 | Spring Data JPA
* PostgreSQL | Docker & Docker Compose
* Spring Security (BCrypt) | Hibernate Validator

## 📂 Como Executar
1. Clone o repositório.
2. Certifique-se de ter o Docker instalado.
3. Execute: `docker-compose up -d`
4. Inicie a aplicação via IDE ou `mvn spring-boot:run`.